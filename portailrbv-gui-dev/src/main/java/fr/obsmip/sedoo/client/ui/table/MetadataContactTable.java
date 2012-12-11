package fr.obsmip.sedoo.client.ui.table;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import fr.obsmip.sedoo.client.GlobalBundle;
import fr.obsmip.sedoo.client.domain.HasId;
import fr.obsmip.sedoo.client.domain.IdentifiedString;
import fr.obsmip.sedoo.client.domain.MetadataContactDTO;
import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.ui.MetadataEditingView.Presenter;
import fr.obsmip.sedoo.client.ui.misc.ConfirmCallBack;
import fr.obsmip.sedoo.client.ui.misc.DialogBoxTools;
import fr.obsmip.sedoo.client.ui.tabs.edit.ContactDialogBoxContent;
import fr.obsmip.sedoo.client.ui.tabs.edit.ContactListDialogBoxContent;

public class MetadataContactTable extends AbstractTable {

	private ContactDialogBoxContent content = null;
	private ContactListDialogBoxContent listContent = null;
	
	private Image addFromDirectoryImage;
	protected Label addFromDirectoryLabel;
	
	private Presenter presenter;
	
	public MetadataContactTable()
	{
		super();
		setAddButtonEnabled(true);
		addDirectoryButton();
	}
	
	private void addDirectoryButton() {
		addFromDirectoryImage = new Image(GlobalBundle.INSTANCE.add());
		addFromDirectoryImage.setStyleName("clickableImage");
		toolBarPanel.add(addFromDirectoryImage);
		addFromDirectoryLabel = new Label(Message.INSTANCE.metadataEditingAddContactFromDirectory());
		addFromDirectoryLabel.setStyleName("clickableImage");
		toolBarPanel.add(addFromDirectoryLabel);	
		addFromDirectoryImage.addClickHandler(this);
		addFromDirectoryLabel.addClickHandler(this);
		
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
	
	
	public void addItemFromDirectory() {
		listContent = new ContactListDialogBoxContent(new ConfirmCallBack() {;
		
			@Override
			public void confirm(boolean choice) {
				if (choice == true)
				{
					List<MetadataContactDTO> resultList = getListContent().getResultList();
					if (resultList.isEmpty()==false)
					{
						Long maxId = 0L;
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
						Long index = maxId+1;
						Iterator<MetadataContactDTO> resultIterator = resultList.iterator();
						while (resultIterator.hasNext()) 
						{
							MetadataContactDTO metadataContactDTO = (MetadataContactDTO) resultIterator.next();
							metadataContactDTO.setId(index);
							index++;
							newValues.add(metadataContactDTO);
						}
						init(newValues);
					}
				}
			}
		},getPresenter());
		
		
		
		
		DialogBoxTools.popUp(Message.INSTANCE.metadataEditingSelectContactFromList(), listContent,"800px","300px");
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
		
		MultiLineTextColumn<HasId> roleColumn = new MultiLineTextColumn<HasId>(RoleRenderer.getInstance()) {
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
	
	public ContactListDialogBoxContent getListContent() {
		return listContent;
	}

	public void reset() {
			List<IdentifiedString> aux = new ArrayList<IdentifiedString>();
			init(aux);
	}
	
	public void onClick(ClickEvent event)
	{
		super.onClick(event);
		if ((event.getSource() == addFromDirectoryImage) || (event.getSource() == addFromDirectoryLabel))
		{
			addItemFromDirectory();
		}
	}

	public Presenter getPresenter() {
		return presenter;
	}

	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
	

}
