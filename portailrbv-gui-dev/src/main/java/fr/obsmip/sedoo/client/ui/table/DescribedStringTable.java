package fr.obsmip.sedoo.client.ui.table;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.cellview.client.Column;

import fr.obsmip.sedoo.client.domain.HasId;
import fr.obsmip.sedoo.client.domain.IdentifiedDescribedString;
import fr.obsmip.sedoo.client.ui.misc.AnchorCell;
import fr.obsmip.sedoo.client.ui.misc.WaterMarkCell;

public class DescribedStringTable extends StringTable {

	private String descriptionWatermark="";
	private String valueColumnHeader="";
	private String descriptionColumnHeader="";
	
	@UiConstructor
	public DescribedStringTable(String waterMark, String descriptionWatermark, String addItemText, String valueColumnHeader, String descriptionColumnHeader) 
	{
		super(waterMark, addItemText);
		this.setDescriptionWatermark(descriptionWatermark);
		if (valueColumnHeader != null)
		{
			this.setValueColumnHeader(valueColumnHeader);
		}
		
		if (descriptionColumnHeader != null)
		{
			this.setDescriptionColumnHeader(descriptionColumnHeader);
		}
		
	}
	
	public void addEmptyRow()
	{
		Long maxId = 0L;
		List<IdentifiedDescribedString> newValues = new ArrayList<IdentifiedDescribedString>();
		Iterator<? extends HasId> iterator = model.iterator();
		while (iterator.hasNext()) 
		{
			IdentifiedDescribedString aux = (IdentifiedDescribedString) iterator.next();
			newValues.add(aux);
			if (aux.getId()> maxId)
			{
				maxId = aux.getId();
			}
		}
		newValues.add(getDefaultIdentifiedString(maxId+1));
		init(newValues);
	}


	protected void localInitColumns() {
	
	}
		
	public void enableEditMode()
	{
		toolBarPanel.setVisible(true);
		initEditColumns();
	}
	
	public void enableDisplayMode()
	{
		toolBarPanel.setVisible(false);
		initDisplayColumns();
	}
	
	
	protected void initEditColumns() {
		
		valueColumn = new Column<HasId, String>(
				new WaterMarkCell(getWatermark(),"40","120")) {
			@Override
			public String getValue(HasId identifiedString) {
				return ((IdentifiedDescribedString) identifiedString).getValue();
			}
		};
		
		valueColumn.setFieldUpdater(new FieldUpdater<HasId, String>() {
			@Override
			public void update(int index, HasId identifiedString, String value) {
				((IdentifiedDescribedString) identifiedString).setValue(value);
			}
		});
		
		Column<HasId, String> descriptionColumn = new Column<HasId, String>(
				new WaterMarkCell(getDescriptionWatermark(),"40","120")) {
			@Override
			public String getValue(HasId identifiedString) {
				return ((IdentifiedDescribedString) identifiedString).getDescription();
			}
		};
		
		descriptionColumn.setFieldUpdater(new FieldUpdater<HasId, String>() {
			@Override
			public void update(int index, HasId identifiedString, String description) {
				((IdentifiedDescribedString) identifiedString).setDescription(description);
			}
		});
		
//		valueColumn.setCellStyleNames("url");
		itemTable.addColumn(valueColumn, getValueColumnHeader());
		itemTable.setColumnWidth(valueColumn, 30.0, Unit.PX);
		itemTable.addColumn(descriptionColumn, getDescriptionColumnHeader());
		itemTable.setColumnWidth(descriptionColumn, 30.0, Unit.PX);
		itemTable.addColumn(deleteColumn);
		itemTable.setColumnWidth(deleteColumn, 30.0, Unit.PX);
		setAddButtonEnabled(true);
	}
	
	protected void initDisplayColumns() {
		
		Column<HasId, IdentifiedDescribedString> linkColumn = new Column<HasId, IdentifiedDescribedString>(
				new AnchorCell()) {
			@Override
			public IdentifiedDescribedString getValue(HasId identifiedDescribedString) {
				return (IdentifiedDescribedString) identifiedDescribedString;
			}
		};
		
		itemTable.addColumn(linkColumn);
		itemTable.setColumnWidth(linkColumn, 80.0, Unit.PX);
	}

	public void reset() 
	{
		List<IdentifiedDescribedString> aux = new ArrayList<IdentifiedDescribedString>();
		init(aux);
	}

	protected IdentifiedDescribedString getDefaultIdentifiedString(Long id) {

		IdentifiedDescribedString string = new IdentifiedDescribedString();
		string.setValue("");
		string.setId(id);
		return string;
	}

	public String getDescriptionWatermark() {
		return descriptionWatermark;
	}

	public void setDescriptionWatermark(String descriptionWatermark) {
		this.descriptionWatermark = descriptionWatermark;
	}

	

	public String getDescriptionColumnHeader() {
		return descriptionColumnHeader;
	}

	public void setDescriptionColumnHeader(String descriptionColumnHeader) {
		this.descriptionColumnHeader = descriptionColumnHeader;
	}

	public String getValueColumnHeader() {
		return valueColumnHeader;
	}

	public void setValueColumnHeader(String valueColumnHeader) {
		this.valueColumnHeader = valueColumnHeader;
	}
	
}
