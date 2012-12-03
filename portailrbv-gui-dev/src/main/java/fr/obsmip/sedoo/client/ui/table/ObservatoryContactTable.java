package fr.obsmip.sedoo.client.ui.table;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.cellview.client.TextColumn;

import fr.obsmip.sedoo.client.domain.HasId;
import fr.obsmip.sedoo.client.domain.ObservatoryContactDTO;
import fr.obsmip.sedoo.client.domain.ObservatoryDTO;
import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.ui.ObservatoryEditingView.Presenter;


public class ObservatoryContactTable extends AbstractTable {

	private Presenter presenter;
	private ObservatoryDTO observatoryDTO;

	@Override
	public void addItem() {
		presenter.createObservatoryContact(observatoryDTO);
	}

	@Override
	public String getAddItemText() {
		return Message.INSTANCE.observatoryContactTableAddItemText();
	}

	@Override
	public void presenterDelete(Long id) {
		presenter.deletePerson(id);	
	}

	@Override
	public void presenterEdit(Long id) {
		presenter.editObservatoryContact(id);
		
	}

	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	public ObservatoryDTO getObservatoryDTO() {
		return observatoryDTO;
	}

	public void setObservatoryDTO(ObservatoryDTO observatoryDTO) {
		this.observatoryDTO = observatoryDTO;
	}
	
protected void initColumns() {
		
		TextColumn<HasId> nameColumn = new TextColumn<HasId>() {
			@Override
			public String getValue(HasId aux) {
				return ((ObservatoryContactDTO) aux).getPersonName();
			}
		};
		itemTable.addColumn(nameColumn, Message.INSTANCE.personPersonName());
		itemTable.setColumnWidth(nameColumn, 100.0, Unit.PX);
		
		TextColumn<HasId> emailColumn = new TextColumn<HasId>() {
			@Override
			public String getValue(HasId aux) {
				return ((ObservatoryContactDTO) aux).getEmail();
			}
		};
		itemTable.addColumn(emailColumn, Message.INSTANCE.personEmail());
		itemTable.setColumnWidth(emailColumn, 100.0, Unit.PX);
		
		TextColumn<HasId> organisationColumn = new TextColumn<HasId>() {
			@Override
			public String getValue(HasId aux) {
				return ((ObservatoryContactDTO) aux).getOrganisationName();
			}
		};
		itemTable.addColumn(organisationColumn, Message.INSTANCE.personOrganisationName());
		itemTable.setColumnWidth(organisationColumn, 100.0, Unit.PX);
		
		MultiLineTextColumn<HasId> roleColumn = new MultiLineTextColumn<HasId>(RoleRenderer.getInstance()) {
			@Override
			public String getValue(HasId aux) {
				return ((ObservatoryContactDTO) aux).getRoles();
			}
		};
		itemTable.addColumn(roleColumn, Message.INSTANCE.personRoles());
		itemTable.setColumnWidth(roleColumn, 100.0, Unit.PX);
		
		super.initColumns();
	}


}
