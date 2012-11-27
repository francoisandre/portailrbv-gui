package fr.obsmip.sedoo.client.ui.table;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.tools.ant.types.CommandlineJava.SysProperties;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.cellview.client.TextColumn;

import fr.obsmip.sedoo.client.domain.HasId;
import fr.obsmip.sedoo.client.domain.IdentifiedString;
import fr.obsmip.sedoo.client.domain.MetadataContactDTO;
import fr.obsmip.sedoo.client.domain.PersonDTO;
import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.ui.misc.ConfirmCallBack;
import fr.obsmip.sedoo.client.ui.misc.DialogBoxTools;
import fr.obsmip.sedoo.client.ui.tabs.edit.ContactDialogBoxContent;

public class MetadataContactTable extends AbstractTable {

	private ContactDialogBoxContent content = null;
	
	public MetadataContactTable()
	{
		super();
		setAddButtonEnabled(true);
	}
	
	@Override
	public void addItem() {
		content = new ContactDialogBoxContent(new ConfirmCallBack() {;
		
			@Override
			public void confirm(boolean choice) {
				if (choice == true)
				{
					Long maxId = 0L;
					MetadataContactDTO resultValue = getContent().getResultValue();
//					model.add(resultValue);
					List<MetadataContactDTO> newValues = new ArrayList<MetadataContactDTO>();
					Iterator<? extends HasId> iterator = model.iterator();
					while (iterator.hasNext()) 
					{
						MetadataContactDTO aux = (MetadataContactDTO) iterator.next();
						newValues.add(aux);
						if (aux.getId()> maxId)
						{
							maxId = aux.getId();
						}
					}
					resultValue.setId(maxId+1);
					newValues.add(resultValue);
					init(newValues);
				}
			}
		});
		DialogBoxTools.popUp(Message.INSTANCE.metadataContactTableAddItemText(), content);
	}

	@Override
	public String getAddItemText() {
		return Message.INSTANCE.metadataContactTableAddItemText();
	}

	@Override
	public void presenterDelete(Long id) {
		removeRow(id);
	}

	@Override
	public void presenterEdit(final Long id) {
		content = new ContactDialogBoxContent(new ConfirmCallBack() {;
		
		@Override
		public void confirm(boolean choice) {
			if (choice == true)
			{
				Long maxId = 0L;
				MetadataContactDTO resultValue = getContent().getResultValue();
				resultValue.setId(id);
//				model.add(resultValue);
				List<MetadataContactDTO> newValues = new ArrayList<MetadataContactDTO>();
				Iterator<? extends HasId> iterator = model.listIterator();
				while (iterator.hasNext()) 
				{
					MetadataContactDTO aux = (MetadataContactDTO) iterator.next();
					if (aux.getId()==id)
					{
						newValues.add(resultValue);
					}
					else
					{
						newValues.add(aux);
					}
				}
				init(newValues);
			}
		}
	});
	content.edit((MetadataContactDTO)getItemById(id));
	DialogBoxTools.popUp(Message.INSTANCE.metadataContactTableAddItemText(), content);		
	}
	
	@Override
	protected void initColumns() {
		
		TextColumn<HasId> nameColumn = new TextColumn<HasId>() {
			@Override
			public String getValue(HasId aux) {
				return ((MetadataContactDTO) aux).getPersonName();
			}
		};
		itemTable.addColumn(nameColumn, Message.INSTANCE.personPersonName());
		itemTable.setColumnWidth(nameColumn, 100.0, Unit.PX);
		
		TextColumn<HasId> emailColumn = new TextColumn<HasId>() {
			@Override
			public String getValue(HasId aux) {
				return ((MetadataContactDTO) aux).getEmail();
			}
		};
		itemTable.addColumn(emailColumn, Message.INSTANCE.personEmail());
		itemTable.setColumnWidth(emailColumn, 100.0, Unit.PX);
		
		TextColumn<HasId> organisationColumn = new TextColumn<HasId>() {
			@Override
			public String getValue(HasId aux) {
				return ((MetadataContactDTO) aux).getOrganisationName();
			}
		};
		itemTable.addColumn(organisationColumn, Message.INSTANCE.personOrganisationName());
		itemTable.setColumnWidth(organisationColumn, 100.0, Unit.PX);
		
		MultiLineTextColumn<HasId> roleColumn = new MultiLineTextColumn<HasId>() {
			@Override
			public String getValue(HasId aux) {
				return ((MetadataContactDTO) aux).getRoles();
			}
		};
		itemTable.addColumn(roleColumn, Message.INSTANCE.personRoles());
		itemTable.setColumnWidth(roleColumn, 100.0, Unit.PX);
		
		super.initColumns();
	}

	public ContactDialogBoxContent getContent() {
		return content;
	}
	

}
