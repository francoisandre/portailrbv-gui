package fr.obsmip.sedoo.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.obsmip.sedoo.client.domain.SummaryDTO;
import fr.obsmip.sedoo.client.domain.metadata.MetadataDTO;

public interface MetadataServiceAsync {

	void createDefaultMetadata(AsyncCallback<MetadataDTO> callback);

	void getMetadataById(String id, AsyncCallback<MetadataDTO> callback);

	void getPDFURL(String metadataId, AsyncCallback<String> callback);

	void getSummaries(AsyncCallback<List<SummaryDTO>> callback);

	void toXML(MetadataDTO metadata, AsyncCallback<String> callback);

}
