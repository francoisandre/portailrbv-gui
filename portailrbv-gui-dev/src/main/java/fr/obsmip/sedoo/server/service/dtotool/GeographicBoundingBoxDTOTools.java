package fr.obsmip.sedoo.server.service.dtotool;

import org.opengis.metadata.extent.GeographicBoundingBox;

import fr.obsmip.sedoo.client.domain.AbstractDTO;
import fr.obsmip.sedoo.client.domain.GeographicBoundingBoxDTO;

public class GeographicBoundingBoxDTOTools 
{
	public static GeographicBoundingBoxDTO toDTO(GeographicBoundingBox box) 
	{
		GeographicBoundingBoxDTO dto = new GeographicBoundingBoxDTO();
		dto.setEastBoundLongitude(AbstractDTO.protectNullObject(box.getEastBoundLongitude()));
		dto.setWestBoundLongitude(AbstractDTO.protectNullObject(box.getWestBoundLongitude()));
		dto.setNorthBoundLatitude(AbstractDTO.protectNullObject(box.getNorthBoundLatitude()));
		dto.setSouthBoundLatitude(AbstractDTO.protectNullObject(box.getSouthBoundLatitude()));
		return dto;
	}
}
