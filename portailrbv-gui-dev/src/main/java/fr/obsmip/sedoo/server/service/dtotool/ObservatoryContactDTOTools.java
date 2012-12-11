package fr.obsmip.sedoo.server.service.dtotool;

import fr.obsmip.sedoo.client.domain.ObservatoryContactDTO;
import fr.obsmip.sedoo.core.domain.ObservatoryContact;

public class ObservatoryContactDTOTools {

	public static ObservatoryContactDTO toObservatoryContactDTO(ObservatoryContact observatoryContact)
	{
		ObservatoryContactDTO aux = (ObservatoryContactDTO) PersonDTOTools.toPersonDTO(observatoryContact, new ObservatoryContactDTO());
		aux.setObservatoryId(observatoryContact.getObservatory().getId());
		aux.setObservatoryShortLabel(observatoryContact.getObservatory().getShortLabel());
		return aux;
	}

	public static ObservatoryContact fromDTO(ObservatoryContactDTO dto) 
	{
		return (ObservatoryContact) PersonDTOTools.fromDTO(dto, new ObservatoryContact());
	}
	
	public static ObservatoryContact fromDTO(ObservatoryContactDTO dto, ObservatoryContact contact) 
	{
		return (ObservatoryContact) PersonDTOTools.fromDTO(dto, contact);
	}
}
