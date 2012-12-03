package fr.obsmip.sedoo.client.ui.tabs.edit;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import fr.obsmip.sedoo.client.CellTableResources;
import fr.obsmip.sedoo.client.domain.AbstractDTO;
import fr.obsmip.sedoo.client.domain.IdentifiedString;
import fr.obsmip.sedoo.client.domain.MetadataContactDTO;
import fr.obsmip.sedoo.client.domain.metadata.MetadataDTO;
import fr.obsmip.sedoo.client.ui.table.MetadataContactTable;
import fr.obsmip.sedoo.client.ui.table.StringTable;

public class IdentificationTab extends AbstractTab  {

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
	StringTable resourceURLTable;
	
	@UiField 
	StringTable resourceIdentifiantTable;
	
	@UiField 
	MetadataContactTable resourceContactTable;
	
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

	public MetadataDTO flush(MetadataDTO metadataDTO) {
		metadataDTO.getIdentificationPart().setResourceAbstract(resourceAbstract.getText());
		metadataDTO.getIdentificationPart().setResourceTitle(resourceTitle.getText());
		metadataDTO.getIdentificationPart().setResourceURL((List<IdentifiedString>) resourceURLTable.getModel());
		metadataDTO.getIdentificationPart().setResourceIdentifiers((List<IdentifiedString>) resourceIdentifiantTable.getModel());
		metadataDTO.getIdentificationPart().setResourceContacts((List<MetadataContactDTO>) resourceContactTable.getModel());
		return metadataDTO;
	}
	
	
}
