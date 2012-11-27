package fr.obsmip.sedoo.client.ui.tabs.edit;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import fr.obsmip.sedoo.client.CellTableResources;
import fr.obsmip.sedoo.client.ui.table.StringTable;
import fr.obsmip.sedoo.client.ui.table.URLTable;

public class IdentificationTab extends Composite {

	private static IdentificationTabUiBinder uiBinder = GWT
			.create(IdentificationTabUiBinder.class);

	interface IdentificationTabUiBinder extends
			UiBinder<Widget, IdentificationTab> {
	}
	
	@UiField
	TextBox resourceTitle;
	
	@UiField 
	TextArea resourceAbstract;
	
	@UiField 
	URLTable resourceURLTable;
	
	@UiField 
	StringTable resourceIdentifiantTable;
	
	public IdentificationTab() {
		initWidget(uiBinder.createAndBindUi(this));
		CellTableResources.INSTANCE.cellTableStyle().ensureInjected();
		reset();
	}

	public void reset() {
		resourceTitle.setText("");
		resourceAbstract.setText("");
		resourceURLTable.reset();
		resourceIdentifiantTable.reset();
	}
	
	
}
