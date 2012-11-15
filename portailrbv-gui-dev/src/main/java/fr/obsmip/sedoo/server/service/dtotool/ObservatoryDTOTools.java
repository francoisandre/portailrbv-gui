package fr.obsmip.sedoo.server.service.dtotool;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.obsmip.sedoo.client.domain.DrainageBasinDTO;
import fr.obsmip.sedoo.client.domain.ObservatoryDTO;
import fr.obsmip.sedoo.client.domain.UserDTO;
import fr.obsmip.sedoo.core.domain.DrainageBasin;
import fr.obsmip.sedoo.core.domain.Observatory;
import fr.obsmip.sedoo.core.domain.User;

public class ObservatoryDTOTools {
	
	public static List<ObservatoryDTO> toObservatoryDTOList(
			List<Observatory> observatories) 
	{
		List<ObservatoryDTO> aux = new ArrayList<ObservatoryDTO>();
		Iterator<Observatory> iterator = observatories.iterator();
		while (iterator.hasNext()) {
			Observatory observatory = iterator.next();
			aux.add(toObservatoryDTO(observatory, false));
		}
		return aux;
	}
	
	public static Observatory fromObservatoryDTO(Observatory observatory, ObservatoryDTO dto) 
	{
		observatory.setDescription(dto.getDescription());
		observatory.setShortLabel(dto.getShortLabel());
		observatory.setLongLabel(dto.getLongLabel());
		return observatory;
	}
	
	
	public static ObservatoryDTO toObservatoryDTO(Observatory observatory, boolean full) 
	{
		ObservatoryDTO observatoryDTO = new ObservatoryDTO();
		observatoryDTO.setId(observatory.getId());
		observatoryDTO.setShortLabel(observatory.getShortLabel());
		observatoryDTO.setLongLabel(observatory.getLongLabel());
		observatoryDTO.setDescription(observatory.getDescription());
		
		
		
		List<DrainageBasinDTO> drainageBasinDTOs = new ArrayList<DrainageBasinDTO>();
		if (full == true)
		{
			Iterator<DrainageBasin> iterator = observatory.getDrainageBasins().iterator();
			while (iterator.hasNext()) {
				DrainageBasin drainageBasin = (DrainageBasin) iterator.next();
				drainageBasinDTOs.add(DrainageBasinDTOTools.toDrainageBasinDTO(drainageBasin, full));
			}		
			
		}
		observatoryDTO.setDrainageBasinDTOs(drainageBasinDTOs);
		
		return observatoryDTO;
	}


}
