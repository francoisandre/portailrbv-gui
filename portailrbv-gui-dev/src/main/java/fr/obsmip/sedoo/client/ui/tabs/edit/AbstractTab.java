package fr.obsmip.sedoo.client.ui.tabs.edit;

import fr.obsmip.sedoo.client.domain.metadata.MetadataDTO;
import fr.obsmip.sedoo.client.ui.misc.EditDisplayComposite;

public abstract class AbstractTab extends EditDisplayComposite
{
	public abstract void reset();
	public abstract void edit(MetadataDTO metadata);
	public abstract void display(MetadataDTO metadata);
	public abstract MetadataDTO flush(MetadataDTO metadataDTO);
	
	

}
