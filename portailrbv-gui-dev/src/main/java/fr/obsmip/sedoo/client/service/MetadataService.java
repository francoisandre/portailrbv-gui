package fr.obsmip.sedoo.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fr.obsmip.sedoo.client.domain.SummaryDTO;
import fr.obsmip.sedoo.client.domain.metadata.MetadataDTO;

@RemoteServiceRelativePath("metadata")
public interface MetadataService extends RemoteService {
	
	String toXML(MetadataDTO metadata);
	MetadataDTO createDefaultMetadata();
	List<SummaryDTO> getSummaries();
	String getPDFURL(String metadataId);
	MetadataDTO getMetadataById(String id) throws Exception;
	
}

