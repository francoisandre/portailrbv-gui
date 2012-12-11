package fr.obsmip.sedoo.server.service.dtotool;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.obsmip.sedoo.client.domain.DrainageBasinDTO;
import fr.obsmip.sedoo.client.domain.GeographicBoundingBoxDTO;
import fr.obsmip.sedoo.client.domain.SiteDTO;
import fr.obsmip.sedoo.core.RBVApplication;
import fr.obsmip.sedoo.core.dao.SiteDAO;
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
			//L'identifiant des sites transmis n'est pas le vrai identifiant mais un identifiant fictif - Lors d'une sauvegarde les sites existants sont systèmatiquement
			// supprimés et recrées
			Long id = 1L;
			Iterator<Site> iterator = drainageBasin.getSites().iterator();
			while (iterator.hasNext()) {
				Site site = iterator.next();
				SiteDTO aux = SiteDTOTools.toSiteDTO(site);
				aux.setId(id);
				id++;
				siteDTOs.add(aux);
			}
			
			GeographicBoundingBoxDTO boxDTO = new GeographicBoundingBoxDTO();
			boxDTO.setEastBoundLongitude(protectNullValue(drainageBasin.getEastBoundLongitude()));
			boxDTO.setWestBoundLongitude(protectNullValue(drainageBasin.getWestBoundLongitude()));
			boxDTO.setNorthBoundLatitude(protectNullValue(drainageBasin.getNorthBoundLatitude()));
			boxDTO.setSouthBoundLatitude(protectNullValue(drainageBasin.getSouthBoundLatitude()));
			dto.setGeographicBoundingBoxDTO(boxDTO);
			
		}
		dto.setSiteDTOs(siteDTOs);
		
		dto.setObservatoryId(drainageBasin.getObservatory().getId());
		dto.setObservatoryShortLabel(drainageBasin.getObservatory().getShortLabel());
		
		//TODO climate lytho
		
		return dto;
	}

	private static String protectNullValue(Double value) {
		if (value == null)
		{
			return "";
		}
		else
		{
			return ""+value;
		}
	}

	public static DrainageBasin fromDTO(DrainageBasinDTO dto) 
	{	
		return fromDTO(dto, null);
	}
	
	public static DrainageBasin fromDTO(DrainageBasinDTO dto, DrainageBasin drainageBasin) 
	{	
		DrainageBasin aux;
		if (drainageBasin == null)
		{
			aux = new DrainageBasin();
		}
		else
		{
			aux = drainageBasin;
		}
		aux.setName(dto.getLabel());
		aux.setNorthBoundLatitude(new Double(dto.getGeographicBoundingBoxDTO().getNorthBoundLatitude()));
		aux.setSouthBoundLatitude(new Double(dto.getGeographicBoundingBoxDTO().getSouthBoundLatitude()));
		aux.setEastBoundLongitude(new Double(dto.getGeographicBoundingBoxDTO().getEastBoundLongitude()));
		aux.setWestBoundLongitude(new Double(dto.getGeographicBoundingBoxDTO().getWestBoundLongitude()));
		
		//TODO Supprimer réellemnt en base
		
		SiteDAO dao = RBVApplication.getInstance().getSiteDAO();
		if (drainageBasin != null)
		{
			//On est en train de modifier un basin. Il faut supprimer les sites déjà présents en base
			//dao.deleteFromDrainageBasinId(drainageBasin.getId());
			aux.getSites().clear();
		}
		
		Iterator<SiteDTO> iterator = dto.getSiteDTOs().iterator();
		while (iterator.hasNext()) {
			
			aux.addSite(SiteDTOTools.fromDTO(iterator.next()));
		}
		
		
		//TODO climate lytho
		//TODO sites
		
		return aux;
	}

}
