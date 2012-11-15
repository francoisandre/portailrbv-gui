package fr.obsmip.sedoo.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import fr.obsmip.sedoo.client.CellTableResources;
import fr.obsmip.sedoo.client.PortailRBV;
import fr.obsmip.sedoo.client.domain.ObservatoryDTO;
import fr.obsmip.sedoo.client.domain.UserDTO;
import fr.obsmip.sedoo.client.event.UserLoginEvent;
import fr.obsmip.sedoo.client.ui.table.DrainageBasinTable;

public class ObservatoryEditingViewImpl extends AbstractSection implements ObservatoryEditingView {



	private static ObservatoryEditingViewImplUiBinder uiBinder = GWT
			.create(ObservatoryEditingViewImplUiBinder.class);

	interface ObservatoryEditingViewImplUiBinder extends UiBinder<Widget, ObservatoryEditingViewImpl> {
	}

	ObservatoryDTO observatory;
	private Presenter presenter;


	@UiField
	TextBox shortLabel;
	
	@UiField
	TextBox longLabel;
	
	@UiField
	TextArea description;
	
	@UiField
	DrainageBasinTable drainageBasinTable;
	
	@UiField
	Button saveButton;

	public ObservatoryEditingViewImpl() {
		super();
		initWidget(uiBinder.createAndBindUi(this));
		applyCommonStyle();
		init();
		CellTableResources.INSTANCE.cellTableStyle().ensureInjected();
	}


	@Override
	public void edit(ObservatoryDTO observatory) {
		this.observatory = observatory;
		reset();
		longLabel.setText(observatory.getLongLabel());
		shortLabel.setText(observatory.getShortLabel());
		description.setText(observatory.getDescription());
		drainageBasinTable.setPresenter(presenter);
		drainageBasinTable.setObservatoryDTO(observatory);
		drainageBasinTable.init(observatory.getDrainageBasinDTOs());
	}

	private void init()
	{
		
	}
	
	private void reset() {
		longLabel.setText("");
		shortLabel.setText("");
		description.setText("");
	}


	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}


	@Override
	public ObservatoryDTO flush() 
	{
		ObservatoryDTO observatoryDTO = new ObservatoryDTO(); 
		observatoryDTO.setLongLabel(longLabel.getText().trim());
		observatoryDTO.setShortLabel(shortLabel.getText().trim());
		observatoryDTO.setDescription(description.getText().trim());
		return observatoryDTO;
	}
	
	 @UiHandler("saveButton")
	 void onSaveButtonClicked(ClickEvent event) 
	 {
		presenter.save(flush());
	 }
	



}
