package fr.obsmip.sedoo.server.service.metadata;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.geotoolkit.metadata.iso.extent.DefaultGeographicBoundingBox;

import fr.obsmip.sedoo.client.domain.IdentifiedString;
import fr.obsmip.sedoo.client.domain.SummaryDTO;
import fr.obsmip.sedoo.client.domain.metadata.MetadataDTO;
import fr.obsmip.sedoo.core.domain.Metadata;
import fr.obsmip.sedoo.core.domain.MetadataInitialisationException;
import fr.obsmip.sedoo.core.domain.Summary;

public class MetadataDTOTools {
	
	public static Metadata toMetadata(MetadataDTO metadataDTO) 
	{
		Metadata metadata = null;
		try {
			metadata = new Metadata();
		} catch (MetadataInitialisationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		metadata.setResourceTitle(metadataDTO.getIdentificationPart().getResourceTitle());
		metadata.setResourceAbstract(metadataDTO.getIdentificationPart().getResourceAbstract());
		metadata.setResourceURL(toStringList(metadataDTO.getIdentificationPart().getResourceURL()));
//		metadataDTO.getGeographicalBoundingBox().getEastBoundLongitude();
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
		summaryDTO.setIdentifier(summary.getIdentifier());
		return summaryDTO;
	}

	public static MetadataDTO toMetadatoDTO(Metadata metadata) 
	{
		MetadataDTO metadataDTO = new MetadataDTO();
		metadataDTO.getIdentificationPart().setResourceAbstract(metadata.getResourceAbstract());
		metadataDTO.getIdentificationPart().setResourceTitle(metadata.getResourceTitle());
		return metadataDTO;
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
