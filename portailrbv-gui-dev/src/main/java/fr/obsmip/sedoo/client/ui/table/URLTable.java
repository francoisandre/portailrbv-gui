package fr.obsmip.sedoo.client.ui.table;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.cellview.client.Column;

import fr.obsmip.sedoo.client.domain.HasId;
import fr.obsmip.sedoo.client.domain.URL;
import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.ui.misc.WaterMarkCell;


public class URLTable extends AbstractTable {

	private Column<HasId, String> urlColumn;

	@Override
	public void addItem() {
		//presenter.createDrainageBasin(observatoryDTO);
		addEmptyRow();
	}
	
	public void addEmptyRow()
	{
		Long maxId = 0L;
		List<URL> newValues = new ArrayList<URL>();
		Iterator<? extends HasId> iterator = model.iterator();
		while (iterator.hasNext()) 
		{
			URL aux = (URL) iterator.next();
			newValues.add(aux);
			if (aux.getId()> maxId)
			{
				maxId = aux.getId();
			}
		}
		newValues.add(getDefaultURL(maxId+1));
		init(newValues);
	}

	@Override
	public String getAddItemText() {
		return Message.INSTANCE.metadataEditingURLTableAddItemText();
	}

	@Override
	public void presenterDelete(Long id) {
		removeRow(id);	
	}

	@Override
	public void presenterEdit(Long id) {
	}

	@Override
	protected void initColumns() {
		
		urlColumn = new Column<HasId, String>(
				new WaterMarkCell(URL.DEFAULT_VALUE,"120","80")) {
			@Override
			public String getValue(HasId url) {
				return ((URL) url).getValue();
			}
		};
		
		urlColumn.setFieldUpdater(new FieldUpdater<HasId, String>() {
			@Override
			public void update(int index, HasId url, String value) {
				((URL) url).setValue(value);
			}
		});
		
		urlColumn.setCellStyleNames("url");
		itemTable.addColumn(urlColumn);
		itemTable.setColumnWidth(urlColumn, 30.0, Unit.PX);
		itemTable.addColumn(deleteColumn);
		itemTable.setColumnWidth(deleteColumn, 30.0, Unit.PX);
		setAddButtonEnabled(true);
	}

	public void reset() 
	{
		List<URL> aux = new ArrayList<URL>();
		aux.add(getDefaultURL(1L));
		init(aux);
	}

	protected URL getDefaultURL(Long id) {

		URL url = new URL();
		url.setValue(URL.DEFAULT_VALUE);
		url.setId(id);
		return url;
	}
	
}
