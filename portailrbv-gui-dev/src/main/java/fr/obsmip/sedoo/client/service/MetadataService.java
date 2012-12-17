package fr.obsmip.sedoo.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fr.obsmip.sedoo.client.domain.ExtendedSummaryDTO;
import fr.obsmip.sedoo.client.domain.SummaryDTO;
import fr.obsmip.sedoo.client.domain.metadata.MetadataDTO;

@RemoteServiceRelativePath("metadata")
public interface MetadataService extends RemoteService {
	
	String toXML(MetadataDTO metadata);
	MetadataDTO createDefaultMetadata();
	List<SummaryDTO> getSummaries();
	String getPDFURL(String metadataId);
	MetadataDTO getMetadataByUuid(String uiid) throws Exception;
	List<ExtendedSummaryDTO> getExtendedSummariesByDrainageBasinId(Long id);
}

