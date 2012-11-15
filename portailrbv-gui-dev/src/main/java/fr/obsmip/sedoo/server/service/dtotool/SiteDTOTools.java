package fr.obsmip.sedoo.server.service.dtotool;


import fr.obsmip.sedoo.client.domain.SiteDTO;
import fr.obsmip.sedoo.core.domain.Site;

public class SiteDTOTools {
	
	public static SiteDTO toSiteDTO(Site site) 
	{
		SiteDTO dto = new SiteDTO();
		dto.setName(site.getLabel());
		dto.setId(site.getId());
		dto.setAltitude(site.getAltitude());
		dto.setLatitude(site.getLatitude());
		dto.setLongitude(site.getLongitude());
		return dto;
	}

}
