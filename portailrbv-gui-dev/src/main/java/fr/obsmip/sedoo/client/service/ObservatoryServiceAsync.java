package fr.obsmip.sedoo.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.obsmip.sedoo.client.domain.DrainageBasinDTO;
import fr.obsmip.sedoo.client.domain.ObservatoryDTO;

public interface ObservatoryServiceAsync {

	void getObservatories(AsyncCallback<List<ObservatoryDTO>> callback);

	void deleteObservatory(Long id, AsyncCallback<Void> callback);

	void getObservatoryById(Long id, AsyncCallback<ObservatoryDTO> callback);

	void getDrainageBasinById(Long id, AsyncCallback<DrainageBasinDTO> callback);

	void saveObservatory(ObservatoryDTO observatoryDTO,
			AsyncCallback<Void> callback);

	void deleteDrainageBasin(Long id, AsyncCallback<Void> callback);



}
