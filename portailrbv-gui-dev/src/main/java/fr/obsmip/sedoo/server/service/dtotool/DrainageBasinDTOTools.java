package fr.obsmip.sedoo.server.service.dtotool;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.obsmip.sedoo.client.domain.DrainageBasinDTO;
import fr.obsmip.sedoo.client.domain.GeographicBoundingBoxDTO;
import fr.obsmip.sedoo.client.domain.SiteDTO;
import fr.obsmip.sedoo.core.domain.DrainageBasin;
import fr.obsmip.sedoo.core.domain.Site;

public class DrainageBasinDTOTools {
	
	public static DrainageBasinDTO toDrainageBasinDTO(
			DrainageBasin drainageBasin, boolean full) 
	{
		DrainageBasinDTO dto = new DrainageBasinDTO();
		dto.setLabel(drainageBasin.getName());
		dto.setId(drainageBasin.getId());
		List<SiteDTO> siteDTOs = new ArrayList<SiteDTO>();
		if (full)
		{
			Iterator<Site> iterator = drainageBasin.getSites().iterator();
			while (iterator.hasNext()) {
				Site site = iterator.next();
				siteDTOs.add(SiteDTOTools.toSiteDTO(site));
			}
			
			GeographicBoundingBoxDTO boxDTO = new GeographicBoundingBoxDTO();
			boxDTO.setEastBoundLongitude(""+drainageBasin.getEastBoundLongitude());
			boxDTO.setWestBoundLongitude(""+drainageBasin.getWestBoundLongitude());
			boxDTO.setNorthBoundLatitude(""+drainageBasin.getNorthBoundLatitude());
			boxDTO.setSouthBoundLatitude(""+drainageBasin.getSouthBoundLatitude());
			dto.setGeographicBoundingBoxDTO(boxDTO);
			
		}
		dto.setSiteDTOs(siteDTOs);
		return dto;
	}

	public static DrainageBasin fromDrainageBasinDTO(DrainageBasin drainageBasin, DrainageBasinDTO dto) 
	{
		drainageBasin.setName(dto.getLabel());
		drainageBasin.setNorthBoundLatitude(new Double(dto.getGeographicBoundingBoxDTO().getNorthBoundLatitude()));
		drainageBasin.setSouthBoundLatitude(new Double(dto.getGeographicBoundingBoxDTO().getSouthBoundLatitude()));
		drainageBasin.setEastBoundLongitude(new Double(dto.getGeographicBoundingBoxDTO().getEastBoundLongitude()));
		drainageBasin.setWestBoundLongitude(new Double(dto.getGeographicBoundingBoxDTO().getWestBoundLongitude()));
		return drainageBasin;
	}

}
