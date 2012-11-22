package fr.obsmip.sedoo.client.ui.table;

import fr.obsmip.sedoo.client.domain.ObservatoryDTO;
import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.ui.ObservatoryEditingView.Presenter;


public class ObservatoryContactTable extends PersonTable {

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

}
