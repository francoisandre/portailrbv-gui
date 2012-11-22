package fr.obsmip.sedoo.client.ui.tabs.edit;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

import fr.obsmip.sedoo.client.CellTableResources;

public class ConstraintTab extends Composite {

	private static ConstraintTabUiBinder uiBinder = GWT
			.create(ConstraintTabUiBinder.class);

	interface ConstraintTabUiBinder extends
			UiBinder<Widget, ConstraintTab> {
	}
	
	@UiField
	TextArea useConditions;
	
	@UiField 
	TextArea publicAccessLimitations;
	
	public ConstraintTab() {
		initWidget(uiBinder.createAndBindUi(this));
		CellTableResources.INSTANCE.cellTableStyle().ensureInjected();
		reset();
	}

	public void reset() {
		useConditions.setText("");
		publicAccessLimitations.setText("");
	}
	
	
}
