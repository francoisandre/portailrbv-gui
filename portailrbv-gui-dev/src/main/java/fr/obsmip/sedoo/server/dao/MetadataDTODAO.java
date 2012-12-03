package fr.obsmip.sedoo.server.dao;

import java.util.ArrayList;
import java.util.List;

import fr.obsmip.sedoo.client.domain.SummaryDTO;
import fr.obsmip.sedoo.client.domain.metadata.MetadataDTO;

public class MetadataDTODAO {

	
	public static List<SummaryDTO> getFakeSummaryList()
	{
		List<SummaryDTO> result = new ArrayList<SummaryDTO>();
		result.add(createFakeSummaryDTO(1));
		result.add(createFakeSummaryDTO(2));
		result.add(createFakeSummaryDTO(3));
		return result;
	}

	private static SummaryDTO createFakeSummaryDTO(int i) 
	{
		SummaryDTO summaryDTO = new SummaryDTO();
		summaryDTO.setResourceAbstract("Ressource abstract "+i);
		summaryDTO.setResourceTitle("Ressource title "+i);
		return summaryDTO;
	}

	public static MetadataDTO createDefaultMetadata() {
		return new MetadataDTO();
	}
}
