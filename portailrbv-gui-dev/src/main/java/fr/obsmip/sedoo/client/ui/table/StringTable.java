package fr.obsmip.sedoo.client.ui.table;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.cellview.client.Column;
import fr.obsmip.sedoo.client.domain.HasId;
import fr.obsmip.sedoo.client.domain.IdentifiedString;
import fr.obsmip.sedoo.client.ui.misc.WaterMarkCell;

public class StringTable extends AbstractTable {

	private String addItemText ="";
	private String watermark="";
	
	private Column<HasId, String> valueColumn;

	@Override
	public void addItem() {
		addEmptyRow();
	}
	
	public void addEmptyRow()
	{
		Long maxId = 0L;
		List<IdentifiedString> newValues = new ArrayList<IdentifiedString>();
		Iterator<? extends HasId> iterator = model.iterator();
		while (iterator.hasNext()) 
		{
			IdentifiedString aux = (IdentifiedString) iterator.next();
			newValues.add(aux);
			if (aux.getId()> maxId)
			{
				maxId = aux.getId();
			}
		}
		newValues.add(getDefaultIdentifiedString(maxId+1));
		init(newValues);
	}

	@Override
	public String getAddItemText() {
		return addItemText;
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
		
		valueColumn = new Column<HasId, String>(
				new WaterMarkCell(getWatermark(),"120","80")) {
			@Override
			public String getValue(HasId identifiedString) {
				return ((IdentifiedString) identifiedString).getValue();
			}
		};
		
		valueColumn.setFieldUpdater(new FieldUpdater<HasId, String>() {
			@Override
			public void update(int index, HasId identifiedString, String value) {
				((IdentifiedString) identifiedString).setValue(value);
			}
		});
		
//		valueColumn.setCellStyleNames("url");
		itemTable.addColumn(valueColumn);
		itemTable.setColumnWidth(valueColumn, 30.0, Unit.PX);
		itemTable.addColumn(deleteColumn);
		itemTable.setColumnWidth(deleteColumn, 30.0, Unit.PX);
		setAddButtonEnabled(true);
	}

	public void reset() 
	{
		List<IdentifiedString> aux = new ArrayList<IdentifiedString>();
		aux.add(getDefaultIdentifiedString(1L));
		init(aux);
	}

	protected IdentifiedString getDefaultIdentifiedString(Long id) {

		IdentifiedString string = new IdentifiedString();
		string.setValue(getWatermark());
		string.setId(id);
		return string;
	}

	public void setAddItemText(String addItemText) {
		this.addItemText = addItemText;
		if (addItemLabel != null)
		{
			addItemLabel.setText(addItemText);
		}
	}

	public String getWatermark() {
		return watermark;
	}

	public void setWatermark(String watermark) {
		this.watermark = watermark;
	}
	
}
