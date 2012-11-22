package fr.obsmip.sedoo.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fr.obsmip.sedoo.client.domain.DrainageBasinDTO;
import fr.obsmip.sedoo.client.domain.ObservatoryContactDTO;
import fr.obsmip.sedoo.client.domain.ObservatoryDTO;
import fr.obsmip.sedoo.client.domain.PersonDTO;

@RemoteServiceRelativePath("observatory")
public interface ObservatoryService extends RemoteService {
	
	List<ObservatoryDTO> getObservatories();
	void deleteObservatory(Long id) throws Exception;
	void deleteDrainageBasin(Long id) throws Exception;
	void deletePerson(Long id) throws Exception;
	void deleteSite(Long id) throws Exception;
	void saveObservatory(ObservatoryDTO dto) throws Exception;
	Long addObservatory(ObservatoryDTO dto) throws Exception;
	ObservatoryDTO getObservatoryById(Long id);
	DrainageBasinDTO getDrainageBasinById(Long id);
	void saveDrainageBasin(DrainageBasinDTO dto) throws Exception;
	void savePerson(PersonDTO dto) throws Exception;
	void saveObservatoryContact(ObservatoryContactDTO dto);
	ObservatoryContactDTO getObservatoryContactById(Long id);
	Long addObservatoryContact(ObservatoryContactDTO contact, Long id) throws Exception;
}

