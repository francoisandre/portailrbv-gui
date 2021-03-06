package fr.obsmip.sedoo.client.ui.tabs.edit;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sun.corba.se.impl.orbutil.closure.Constant;

import fr.obsmip.sedoo.client.domain.metadata.MetadataDTO;
import fr.obsmip.sedoo.client.ui.misc.MapSelector;

public class GeographicalLocationTab extends AbstractTab {

	private static GeographicalLocationTabUiBinder uiBinder = GWT
			.create(GeographicalLocationTabUiBinder.class);

	interface GeographicalLocationTabUiBinder extends
			UiBinder<Widget, GeographicalLocationTab> {
	}
	
	@UiField
	MapSelector mapSelector;
	
	public GeographicalLocationTab() {
		initWidget(uiBinder.createAndBindUi(this));
		reset();
	}

	@Override
	public void reset() {
		mapSelector.reset();
		
	}

	@Override
	public void edit(MetadataDTO metadata) {
		enableEditMode();
		mapSelector.reset();
		mapSelector.setGeographicBoundingBoxDTO(metadata.getGeographicalLocationPart().getBox());
	}

	@Override
	public MetadataDTO flush(MetadataDTO metadataDTO) {
//		metadataDTO.getGeographicalLocationPart().setBox(mapSelector.)
		return null;
	}
	
	@Override
	public void display(MetadataDTO metadata) {
		
		enableDisplayMode();
		mapSelector.reset();
		mapSelector.setGeographicBoundingBoxDTO(metadata.getGeographicalLocationPart().getBox());
		
	}

	@Override
	protected void enableEditMode() {
		super.enableEditMode();
		mapSelector.enableEditMode();
	}

	@Override
	protected void enableDisplayMode() {
		super.enableDisplayMode();
		mapSelector.enableDisplayMode();
	}
	

	

}
