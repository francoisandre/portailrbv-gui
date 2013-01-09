package fr.obsmip.sedoo.client.ui.tabs.edit;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import fr.obsmip.sedoo.client.CellTableResources;
import fr.obsmip.sedoo.client.Constants;
import fr.obsmip.sedoo.client.domain.AbstractDTO;
import fr.obsmip.sedoo.client.domain.IdentifiedDescribedString;
import fr.obsmip.sedoo.client.domain.IdentifiedString;
import fr.obsmip.sedoo.client.domain.MetadataContactDTO;
import fr.obsmip.sedoo.client.domain.metadata.MetadataDTO;
import fr.obsmip.sedoo.client.ui.MetadataEditingView.Presenter;
import fr.obsmip.sedoo.client.ui.table.DescribedStringTable;
import fr.obsmip.sedoo.client.ui.table.MetadataContactTable;
import fr.obsmip.sedoo.client.ui.table.StringTable;

public class IdentificationTab extends AbstractTab  {

	private static IdentificationTabUiBinder uiBinder = GWT
			.create(IdentificationTabUiBinder.class);

	interface IdentificationTabUiBinder extends
			UiBinder<Widget, IdentificationTab> {
	}
	
	private Presenter presenter;
	
	@UiField
	TextBox resourceTitle;
	
	@UiField 
	TextArea resourceAbstract;
	
	@UiField 
	DescribedStringTable resourceURLTable;
	
	@UiField 
	StringTable resourceIdentifiantTable;
	
	@UiField
	Label resourceTitleDisplay;
	
	@UiField 
	Label resourceAbstractDisplay;
	
	@UiField 
	MetadataContactTable resourceContactTable;
	
	@UiConstructor
	public IdentificationTab(String mode) {
		initWidget(uiBinder.createAndBindUi(this));
		CellTableResources.INSTANCE.cellTableStyle().ensureInjected();
		displayWidgets.add(resourceTitleDisplay);
		displayWidgets.add(resourceAbstractDisplay);
		editWidgets.add(resourceTitle);
		editWidgets.add(resourceAbstract);
		reset();
		if (mode.compareToIgnoreCase(Constants.EDIT)==0)
		{
			enableEditMode();
			resourceURLTable.enableEditMode();
		}
		else
		{
			enableDisplayMode();
			resourceURLTable.enableDisplayMode();
		}
	}
	
	@Override
	protected void enableDisplayMode() {
		super.enableDisplayMode();
		
	}

	public void reset() {
		resourceTitle.setText("");
		resourceAbstract.setText("");
		resourceURLTable.reset();
		resourceIdentifiantTable.reset();
		resourceContactTable.reset();
	}

	public void edit(MetadataDTO metadata) {
		
		reset();
		resourceTitle.setText(AbstractDTO.protectNullString(metadata.getIdentificationPart().getResourceTitle()));
		resourceAbstract.setText(AbstractDTO.protectNullString(metadata.getIdentificationPart().getResourceAbstract()));
		resourceContactTable.init(metadata.getIdentificationPart().getResourceContacts());
		resourceURLTable.init(metadata.getIdentificationPart().getResourceURL());
		resourceIdentifiantTable.init(metadata.getIdentificationPart().getResourceIdentifiers());
	}
	
	public void display(MetadataDTO metadata) 
	{
		reset();
		resourceTitleDisplay.setText(AbstractDTO.protectNullString(metadata.getIdentificationPart().getResourceTitle()));
		resourceAbstractDisplay.setText(AbstractDTO.protectNullString(metadata.getIdentificationPart().getResourceAbstract()));
		resourceURLTable.init(metadata.getIdentificationPart().getResourceURL());
	}

	public MetadataDTO flush(MetadataDTO metadataDTO) {
		metadataDTO.getIdentificationPart().setResourceAbstract(resourceAbstract.getText());
		metadataDTO.getIdentificationPart().setResourceTitle(resourceTitle.getText());
		metadataDTO.getIdentificationPart().setResourceURL((List<IdentifiedDescribedString>) resourceURLTable.getModel());
		metadataDTO.getIdentificationPart().setResourceIdentifiers((List<IdentifiedString>) resourceIdentifiantTable.getModel());
		metadataDTO.getIdentificationPart().setResourceContacts((List<MetadataContactDTO>) resourceContactTable.getModel());
		return metadataDTO;
	}

	public Presenter getPresenter() {
		return presenter;
	}

	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
		resourceContactTable.setPresenter(getPresenter());
	}
	
	
}
