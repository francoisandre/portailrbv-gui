package fr.obsmip.sedoo.client.ui.tabs.edit;

import com.google.gwt.user.client.ui.Composite;

import fr.obsmip.sedoo.client.domain.metadata.MetadataDTO;

public abstract class AbstractTab extends Composite
{
	public abstract void reset();
	public abstract void edit(MetadataDTO metadata);
	public abstract MetadataDTO flush(MetadataDTO metadataDTO);
}
