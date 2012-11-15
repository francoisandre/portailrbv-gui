package fr.obsmip.sedoo.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fr.obsmip.sedoo.client.domain.DrainageBasinDTO;
import fr.obsmip.sedoo.client.domain.ObservatoryDTO;

@RemoteServiceRelativePath("observatory")
public interface ObservatoryService extends RemoteService {
	
	List<ObservatoryDTO> getObservatories();
	void deleteObservatory(Long id) throws Exception;
	void deleteDrainageBasin(Long id) throws Exception;
	void saveObservatory(ObservatoryDTO observatoryDTO) throws Exception;
	ObservatoryDTO getObservatoryById(Long id);
	DrainageBasinDTO getDrainageBasinById(Long id);
	void saveDrainageBasin(DrainageBasinDTO drainageBasinDTO) throws Exception;
	
}

