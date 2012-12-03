package fr.obsmip.sedoo.client.ui.tabs.edit;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

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
		mapSelector.setGeographicBoundingBoxDTO(metadata.getGeographicalLocationPart().getBox());
	}

	@Override
	public MetadataDTO flush(MetadataDTO metadataDTO) {
//		metadataDTO.getGeographicalLocationPart().setBox(mapSelector.)
		return null;
	}
	


}
