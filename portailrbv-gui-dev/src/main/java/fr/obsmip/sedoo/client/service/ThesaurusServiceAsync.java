package fr.obsmip.sedoo.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.obsmip.sedoo.client.domain.ThesaurusItemDTO;

public interface ThesaurusServiceAsync {

	void getLithologyThesaurus(String locale,
			AsyncCallback<List<ThesaurusItemDTO>> callback);

	void getClimateThesaurus(String locale,
			AsyncCallback<List<ThesaurusItemDTO>> callback);

	void forceRefresh(AsyncCallback<Void> callback);

}
