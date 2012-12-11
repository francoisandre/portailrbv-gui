package fr.obsmip.sedoo.client.ui.tabs.edit;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Widget;

import fr.obsmip.sedoo.client.domain.MetadataContactDTO;
import fr.obsmip.sedoo.client.ui.MetadataEditingView.Presenter;
import fr.obsmip.sedoo.client.ui.misc.ConfirmCallBack;
import fr.obsmip.sedoo.client.ui.misc.DialogBoxContent;
import fr.obsmip.sedoo.client.ui.table.MetadataContactSelectionTable;

public class ContactListDialogBoxContent extends Composite implements DialogBoxContent {

	private static ContactDialogBoxContentUiBinder uiBinder = GWT
			.create(ContactDialogBoxContentUiBinder.class);

	interface ContactDialogBoxContentUiBinder extends
			UiBinder<Widget, ContactListDialogBoxContent> {
	}

	@UiField
	MetadataContactSelectionTable contactTable;
	
	@UiField
	Button ok;
	
	@UiField
	Button cancel;
	
	private DialogBox dialog;
	
	private List<MetadataContactDTO> resultList;
	
	private ConfirmCallBack confirmCallback;
	
	
	public ContactListDialogBoxContent(ConfirmCallBack confirmCallback, Presenter presenter) {
		initWidget(uiBinder.createAndBindUi(this));
		this.confirmCallback = confirmCallback;
		contactTable.setPresenter(presenter);
		resultList = new ArrayList<MetadataContactDTO>();
		contactTable.refresh();
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
		 if (dialog != null)
		  {
			 setResultList(contactTable.getSelectedItems());
			  dialog.hide();
			  confirmCallback.confirm(true);
		  }
	  }

	
	public void setDialogBox(DialogBox dialog) {
		this.dialog = dialog;
	}

	public List<MetadataContactDTO> getResultList() {
		return resultList;
	}

	public void setResultList(List<MetadataContactDTO> resultList) {
		this.resultList = resultList;
	}

}
