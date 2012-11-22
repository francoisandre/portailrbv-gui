package fr.obsmip.sedoo.client.ui.table;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.cellview.client.TextColumn;

import fr.obsmip.sedoo.client.domain.HasId;
import fr.obsmip.sedoo.client.domain.PersonDTO;
import fr.obsmip.sedoo.client.message.Message;


public abstract class PersonTable extends AbstractTable {


	@Override
	protected void initColumns() {
		
		TextColumn<HasId> nameColumn = new TextColumn<HasId>() {
			@Override
			public String getValue(HasId aux) {
				return ((PersonDTO) aux).getPersonName();
			}
		};
		itemTable.addColumn(nameColumn, Message.INSTANCE.name());
		itemTable.setColumnWidth(nameColumn, 100.0, Unit.PX);
		super.initColumns();
	}

}
