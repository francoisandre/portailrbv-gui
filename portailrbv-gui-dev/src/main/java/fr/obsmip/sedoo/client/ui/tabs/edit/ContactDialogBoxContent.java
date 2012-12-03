package fr.obsmip.sedoo.client.ui.tabs.edit;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import fr.obsmip.sedoo.client.PortailRBV;
import fr.obsmip.sedoo.client.domain.AbstractDTO;
import fr.obsmip.sedoo.client.domain.MetadataContactDTO;
import fr.obsmip.sedoo.client.domain.ValidationAlert;
import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.ui.misc.ConfirmCallBack;
import fr.obsmip.sedoo.client.ui.misc.DialogBoxContent;
import fr.obsmip.sedoo.client.ui.misc.DialogBoxTools;

public class ContactDialogBoxContent extends Composite implements DialogBoxContent {

	private static ContactDialogBoxContentUiBinder uiBinder = GWT
			.create(ContactDialogBoxContentUiBinder.class);

	interface ContactDialogBoxContentUiBinder extends
			UiBinder<Widget, ContactDialogBoxContent> {
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
	Button ok;
	
	@UiField
	Button cancel;
	
	private DialogBox dialog;
	
	private MetadataContactDTO resultValue;
	
	private ConfirmCallBack confirmCallback;
	
	public ContactDialogBoxContent(ConfirmCallBack confirmCallback) {
		initWidget(uiBinder.createAndBindUi(this));
		this.confirmCallback = confirmCallback;
	}
	
	 @UiHandler("cancel")
	  void onCancelClicked(ClickEvent event) {
		  if (dialog != null)
		  {
			  dialog.hide();
		  }
	  }
	 
	 
	 @UiHandler("ok")
	  void onOkClicked(ClickEvent event) {
		  MetadataContactDTO aux = flush();
		  List<ValidationAlert> result = aux.validate();
		  if (result.isEmpty())
		  {
			  if (dialog != null)
			  {
				  dialog.hide();
				  resultValue = aux;
				  confirmCallback.confirm(true);
			  }
		  }
		  else
		  {
			  DialogBoxTools.popUp(Message.INSTANCE.error(), ValidationAlert.toHTML(result), DialogBoxTools.HTML_MODE);
		  }
	  }

	private MetadataContactDTO flush() 
	{
		MetadataContactDTO aux = new MetadataContactDTO();
		aux.setEmail(email.getText().trim());
		aux.setOrganisationName(organisationName.getText().trim());
		aux.setPersonName(personName.getText().trim());
		aux.setRoles(rolePanel.flush());
		return aux;
	}

	public void setDialogBox(DialogBox dialog) {
		this.dialog = dialog;
	}

	public MetadataContactDTO getResultValue() {
		return resultValue;
	}

	public void setResultValue(MetadataContactDTO resultValue) {
		this.resultValue = resultValue;
	}

	public void edit(MetadataContactDTO item) 
	{
		if (item != null)
		{
			email.setText(AbstractDTO.protectNullString(item.getEmail()));
			organisationName.setText(AbstractDTO.protectNullString(item.getOrganisationName()));
			personName.setText(AbstractDTO.protectNullString(item.getPersonName()));
			rolePanel.edit(AbstractDTO.protectNullString(item.getRoles()));
		}
	}

}
