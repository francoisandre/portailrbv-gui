package fr.obsmip.sedoo.client.ui.table;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.cellview.client.TextColumn;

import fr.obsmip.sedoo.client.domain.ExtendedSummaryDTO;
import fr.obsmip.sedoo.client.domain.HasId;
import fr.obsmip.sedoo.client.message.Message;


public class ExtendedSummaryDTOTable extends AbstractTable {

	private ExtendedSummaryDTO extendedSummaryDTO;

	@Override
	public void addItem() {
	}

	@Override
	public String getAddItemText() {
		return Message.INSTANCE.addMetadataEntry();
	}

	@Override
	public void presenterDelete(Long id) {
	}

	@Override
	public void presenterEdit(Long id) {
		//presenter.editObservatoryContact(id);
		
	}
	
protected void initColumns() {
		
		TextColumn<HasId> titleColumn = new TextColumn<HasId>() {
			@Override
			public String getValue(HasId aux) {
				return ((ExtendedSummaryDTO) aux).getResourceTitle();
			}
		};
		itemTable.addColumn(titleColumn, Message.INSTANCE.personPersonName());
		itemTable.setColumnWidth(titleColumn, 100.0, Unit.PX);
		
		super.initColumns();
	}


}
