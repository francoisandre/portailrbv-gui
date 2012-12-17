package fr.obsmip.sedoo.client.ui.tabs.edit;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

import fr.obsmip.sedoo.client.CellTableResources;
import fr.obsmip.sedoo.client.domain.AbstractDTO;
import fr.obsmip.sedoo.client.domain.metadata.MetadataDTO;

public class ConstraintTab extends AbstractTab{

	private static ConstraintTabUiBinder uiBinder = GWT
			.create(ConstraintTabUiBinder.class);

	interface ConstraintTabUiBinder extends
			UiBinder<Widget, ConstraintTab> {
	}
	
	@UiField
	TextArea useConditions;
	
	@UiField 
	TextArea publicAccessLimitations;
	
	
	@UiField
	Label useConditionsDisplay;
	
	@UiField 
	Label publicAccessLimitationsDisplay;
	
	public ConstraintTab() {
		initWidget(uiBinder.createAndBindUi(this));
		CellTableResources.INSTANCE.cellTableStyle().ensureInjected();
		editWidgets.add(useConditions);
		editWidgets.add(publicAccessLimitations);
		displayWidgets.add(publicAccessLimitationsDisplay);
		displayWidgets.add(useConditionsDisplay);
		reset();
	}

	public void reset() {
		useConditions.setText("");
		publicAccessLimitations.setText("");
		useConditionsDisplay.setText("");
		publicAccessLimitationsDisplay.setText("");
	}

	@Override
	public void edit(MetadataDTO metadata) 
	{
		enableEditMode();
		reset();
		publicAccessLimitations.setText(AbstractDTO.protectNullString(metadata.getConstraintPart().getPublicAccessLimitations()));
		useConditions.setText(AbstractDTO.protectNullString(metadata.getConstraintPart().getUseConditions()));
	}

	@Override
	public MetadataDTO flush(MetadataDTO metadataDTO) 
	{
		metadataDTO.getConstraintPart().setPublicAccessLimitations(publicAccessLimitations.getText());
		metadataDTO.getConstraintPart().setUseConditions(useConditions.getText());
		return metadataDTO;
	}

	@Override
	public void display(MetadataDTO metadata) {
		enableDisplayMode();
		reset();
		publicAccessLimitationsDisplay.setText(AbstractDTO.protectNullString(metadata.getConstraintPart().getPublicAccessLimitations()));
		useConditionsDisplay.setText(AbstractDTO.protectNullString(metadata.getConstraintPart().getUseConditions()));
	}
	
	
}
