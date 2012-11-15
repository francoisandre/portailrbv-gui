package fr.obsmip.sedoo.client.ui.table;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.cellview.client.TextColumn;

import fr.obsmip.sedoo.client.domain.DrainageBasinDTO;
import fr.obsmip.sedoo.client.domain.HasId;
import fr.obsmip.sedoo.client.domain.ObservatoryDTO;
import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.ui.ObservatoryEditingView.Presenter;


public class DrainageBasinTable extends AbstractTable {

	private Presenter presenter;
	private ObservatoryDTO observatoryDTO;

	@Override
	public void addItem() {
		presenter.createDrainageBasin(observatoryDTO);
	}

	@Override
	public String getAddItemText() {
		return Message.INSTANCE.DrainagaBasinTableAddItemText();
	}

	@Override
	public void presenterDelete(Long id) {
		presenter.deleteDrainageBasin(id);		
	}

	@Override
	public void presenterEdit(Long id) {
		presenter.editDrainageBasin(id);
	}

	@Override
	public String getDeleteItemConfirmationText() {
		return Message.INSTANCE.drainageBasinDeletionConfirmationMessage();
	}
	
	@Override
	protected void initColumns() {
		
		TextColumn<HasId> nameColumn = new TextColumn<HasId>() {
			@Override
			public String getValue(HasId aux) {
				return ((DrainageBasinDTO) aux).getLabel();
			}
		};
		itemTable.addColumn(nameColumn, Message.INSTANCE.name());
		itemTable.setColumnWidth(nameColumn, 100.0, Unit.PX);
		super.initColumns();
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
	
}
