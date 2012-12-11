package fr.obsmip.sedoo.server.service.dtotool;


import fr.obsmip.sedoo.client.domain.AbstractDTO;
import fr.obsmip.sedoo.client.domain.SiteDTO;
import fr.obsmip.sedoo.core.domain.Site;

public class SiteDTOTools {
	
	public static SiteDTO toSiteDTO(Site site) 
	{
		SiteDTO dto = new SiteDTO();
		dto.setName(site.getLabel());
		if (site.getAltitude() != null)
		{
			dto.setAltitude(""+site.getAltitude());
		}
		
		if (site.getLatitude() != null)
		{
			dto.setLatitude(""+site.getLatitude());
		}
		
		if (site.getLongitude() != null)
		{
			dto.setLongitude(""+site.getLongitude());
		}
		return dto;
	}

	public static Site fromDTO(SiteDTO dto) 
	{
		Site aux = new Site();
		aux.setLabel(dto.getName());
		if (!AbstractDTO.isEmpty(dto.getLongitude()))
		{
			aux.setLongitude(new Double(dto.getLongitude()));
		}
		if (!AbstractDTO.isEmpty(dto.getLatitude()))
		{
			aux.setLatitude(new Double(dto.getLatitude()));
		}
		if (!AbstractDTO.isEmpty(dto.getAltitude()))
		{
			aux.setAltitude(new Double(dto.getAltitude()));
		}
		return aux;
	}

}
