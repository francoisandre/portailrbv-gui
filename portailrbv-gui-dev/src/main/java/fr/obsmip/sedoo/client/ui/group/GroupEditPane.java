package fr.obsmip.sedoo.client.ui.group;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import fr.obsmip.sedoo.client.domain.ObservatoryDTO;

public class GroupEditPane extends Composite {

	private static GroupEditPaneUiBinder uiBinder = GWT
			.create(GroupEditPaneUiBinder.class);

	interface GroupEditPaneUiBinder extends UiBinder<Widget, GroupEditPane> {
	}

	@UiField
	Button cancelButton;
	
	@UiField
	Button saveButton;
	
	@UiField
	TextBox name;
	
	@UiField
	TextArea observation;
	
	GroupEditDialog dialog;
	
	public final static String OK="OK";
	
	public String returnCode;
	
	public GroupEditPane() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public void setObservatoryDTO(ObservatoryDTO observatoryDTO)
	{
		returnCode="";
		name.setText(observatoryDTO.getShortLabel());
		observation.setText(observatoryDTO.getDescription());
	}

	public String getReturnCode() {
		return returnCode;
	}
	
	@UiHandler("saveButton")
	void onSaveButtonClicked(ClickEvent event) {
		returnCode = OK;
		//hide();
	}
	
	@UiHandler("cancelButton")
	void onCancelButtonClicked(ClickEvent event) {
		//hide();

	}
}
