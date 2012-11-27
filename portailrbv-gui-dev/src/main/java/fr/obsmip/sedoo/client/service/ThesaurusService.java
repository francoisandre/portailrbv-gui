package fr.obsmip.sedoo.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fr.obsmip.sedoo.client.domain.ObservatoryDTO;
import fr.obsmip.sedoo.client.domain.ThesaurusItemDTO;
import fr.obsmip.sedoo.client.domain.UserDTO;

@RemoteServiceRelativePath("thesaurus")
public interface ThesaurusService extends RemoteService {
	
	List<ThesaurusItemDTO> getClimateThesaurus(String locale) throws Exception;
	List<ThesaurusItemDTO> getLithologyThesaurus(String locale) throws Exception;
	void forceRefresh() throws Exception;
	
}

