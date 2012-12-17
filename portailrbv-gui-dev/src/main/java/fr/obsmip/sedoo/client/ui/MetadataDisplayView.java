package fr.obsmip.sedoo.client.ui;

import com.google.gwt.user.client.ui.IsWidget;

import fr.obsmip.sedoo.client.domain.metadata.MetadataDTO;

public interface MetadataDisplayView extends IsWidget
{
	void display(MetadataDTO metadataDTO);
	void reset();
}
