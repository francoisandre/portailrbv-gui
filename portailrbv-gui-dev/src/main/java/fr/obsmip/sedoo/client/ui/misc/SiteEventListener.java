package fr.obsmip.sedoo.client.ui.misc;

import fr.obsmip.sedoo.client.domain.SiteDTO;

public interface SiteEventListener {
	
	void siteAdded(SiteDTO site);
	void siteRemoved(Long id);

}
