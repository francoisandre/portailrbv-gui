package fr.obsmip.sedoo.server.service.dtotool;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.obsmip.sedoo.client.domain.IdentifiedDescribedString;
import fr.obsmip.sedoo.client.domain.IdentifiedString;
import fr.obsmip.sedoo.client.domain.SummaryDTO;
import fr.obsmip.sedoo.client.domain.metadata.MetadataDTO;
import fr.sedoo.commons.metadata.utils.domain.DescribedURL;
import fr.sedoo.commons.metadata.utils.domain.Metadata;
import fr.sedoo.commons.metadata.utils.domain.Summary;

public class MetadataDTOTools {
	
	public static Metadata toMetadata(MetadataDTO metadataDTO) 
	{
		Metadata metadata = new Metadata();
		
		metadata.setResourceTitle(metadataDTO.getIdentificationPart().getResourceTitle());
		metadata.setResourceAbstract(metadataDTO.getIdentificationPart().getResourceAbstract());
		//metadata.setResourceURL(toStringList(metadataDTO.getIdentificationPart().getResourceURL()));
		metadata.setUseConditions(metadataDTO.getConstraintPart().getUseConditions());
//		metadataDTO.getGeographicalLocationPart().getGeographicalBoundingBox().getEastBoundLongitude();
//		DefaultGeographicBoundingBox boundingbox = new DefaultGeographicBoundingBox(metadataDTO.getGeographicalBoundingBox().getWestBoundLongitude(), metadataDTO.getGeographicalBoundingBox().getEastBoundLongitude(), metadataDTO.getGeographicalBoundingBox().getSouthBoundLatitude(), metadataDTO.getGeographicalBoundingBox().getNorthBoundLatitude());
//		metadata.setGeographicBoundingBox(boundingbox);
		
		return metadata;
	}

	public static List<SummaryDTO> toSummaryDTO(List<Summary> summaries) 
	{
		List<SummaryDTO> summaryDTOs = new ArrayList<SummaryDTO>();
		
		for (Iterator<Summary> iterator = summaries.iterator(); iterator.hasNext();) {
			Summary summary = iterator.next();
			summaryDTOs.add(toSummaryDTO(summary));
		}
		
		return summaryDTOs;
	}

	private static SummaryDTO toSummaryDTO(Summary summary) 
	{
		SummaryDTO summaryDTO = new SummaryDTO();
		summaryDTO.setResourceAbstract(summary.getResourceAbstract());
		summaryDTO.setResourceTitle(summary.getResourceTitle());
		summaryDTO.setUuid(summary.getUuid());
		return summaryDTO;
	}

	public static MetadataDTO toMetadatoDTO(Metadata metadata) 
	{
		MetadataDTO dto = new MetadataDTO();
		dto.getIdentificationPart().setResourceAbstract(metadata.getResourceAbstract());
		dto.getIdentificationPart().setResourceTitle(metadata.getResourceTitle());
		dto.getConstraintPart().setUseConditions(metadata.getUseConditions());
		dto.getGeographicalLocationPart().setBox(GeographicBoundingBoxDTOTools.toDTO(metadata.getGeographicBoundingBox()));
		
		List<DescribedURL> resourceURL = metadata.getResourceURL();
		List<IdentifiedDescribedString> aux = new ArrayList<IdentifiedDescribedString>();
		
		Iterator<DescribedURL> iterator = resourceURL.iterator();
		Long index = 0L;
		while (iterator.hasNext()) {
			DescribedURL describedURL = iterator.next();
			IdentifiedDescribedString current = new IdentifiedDescribedString();
			current.setDescription(describedURL.getLabel());
			current.setValue(describedURL.getLink());
			current.setId(index);
			aux.add(current);
			index++;
			
		}
		
		dto.getIdentificationPart().setResourceURL(aux);
		return dto;
	}
	
	public static List<String> toStringList(List<IdentifiedString> src)
	{
		List<String> result = new ArrayList<String>();
		Iterator<IdentifiedString> iterator = src.iterator();
		while (iterator.hasNext()) 
		{
			result.add(iterator.next().getValue());
		}
		return result;
	}

}
