package fr.obsmip.sedoo.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import fr.obsmip.sedoo.client.domain.AbstractDTO;
import fr.obsmip.sedoo.client.domain.ObservatoryContactDTO;
import fr.obsmip.sedoo.client.ui.tabs.edit.RolePanel;

public class ObservatoryContactEditingViewImpl extends AbstractDTOEditingView implements ObservatoryContactEditingView {

	
	private static ObservatoryContactEditingViewImplUiBinder uiBinder = GWT.create(  ObservatoryContactEditingViewImplUiBinder.class);

	interface ObservatoryContactEditingViewImplUiBinder extends UiBinder<Widget, ObservatoryContactEditingViewImpl> {
	}
	
	@UiField
	TextBox personName;

	@UiField
	TextBox email;
	
	@UiField
	TextBox organisationName;
	
	@UiField
	RolePanel rolePanel;
	
	@UiField
	Button saveButton;
	
	@UiField
	Button backButton;
	
	private Presenter presenter;
	
	
	public ObservatoryContactEditingViewImpl() {
		super();
		initWidget(uiBinder.createAndBindUi(this));
		applyCommonStyle();
		init();
	}

	private void init() {
		
	}
	
	@Override
	public void edit(AbstractDTO dto) {
		reset();
		ObservatoryContactDTO observatoryContactDTO = (ObservatoryContactDTO) dto;
		personName.setText(observatoryContactDTO.getPersonName());
		organisationName.setText(observatoryContactDTO.getOrganisationName());
		email.setText(observatoryContactDTO.getEmail());
		rolePanel.edit(observatoryContactDTO.getRoles());
	}
	
	public void reset()
	{
		personName.setText("");
		organisationName.setText("");
		email.setText("");
	}

	@Override
	public void setPresenter(Presenter presenter) 
	{
		this.presenter = presenter;
	}
	
	 @UiHandler("saveButton")
	 void onSaveButtonClicked(ClickEvent event) 
	 {
		 ObservatoryContactDTO dto = flush();
		 presenter.save(dto);
	 }
	 
	 @UiHandler("backButton")
	 void onBackButtonClicked(ClickEvent event) 
	 {
		 presenter.back();
	 }
	 
	 @Override
	 public ObservatoryContactDTO flush() 
	 {
		 ObservatoryContactDTO observatoryContactDTO = new ObservatoryContactDTO(); 
		 observatoryContactDTO.setEmail(email.getText().trim());
		 observatoryContactDTO.setPersonName(personName.getText().trim());
		 observatoryContactDTO.setOrganisationName(organisationName.getText().trim());
		 observatoryContactDTO.setRoles(rolePanel.flush());
		 return observatoryContactDTO;
	 }

	 public void setMode(String mode) {
			super.setMode(mode);
		} 

}
