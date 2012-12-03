package fr.obsmip.sedoo.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.obsmip.sedoo.client.domain.DrainageBasinDTO;
import fr.obsmip.sedoo.client.domain.ObservatoryContactDTO;
import fr.obsmip.sedoo.client.domain.ObservatoryDTO;
import fr.obsmip.sedoo.client.domain.PersonDTO;
import fr.obsmip.sedoo.core.domain.ObservatoryContact;

public interface ObservatoryServiceAsync {

	void getObservatories(AsyncCallback<List<ObservatoryDTO>> callback);

	void deleteObservatory(Long id, AsyncCallback<Void> callback);

	void getObservatoryById(Long id, AsyncCallback<ObservatoryDTO> callback);

	void getDrainageBasinById(Long id, AsyncCallback<DrainageBasinDTO> callback);

	void saveObservatory(ObservatoryDTO observatoryDTO,
			AsyncCallback<Void> callback);

	void deleteDrainageBasin(Long id, AsyncCallback<Void> callback);

	void saveDrainageBasin(DrainageBasinDTO drainageBasinDTO,
			AsyncCallback<Void> callback);

	void deleteSite(Long id, AsyncCallback<Void> callback);

	void savePerson(PersonDTO personDTO, AsyncCallback<Void> callback);

	void deletePerson(Long id, AsyncCallback<Void> callback);

	void saveObservatoryContact(ObservatoryContactDTO dto,
			AsyncCallback<Void> callback);

	void getObservatoryContactById(Long id,
			AsyncCallback<ObservatoryContactDTO> callback);

	void addObservatoryContact(ObservatoryContactDTO contact, Long id,
			AsyncCallback<Long> callback);

	void addObservatory(ObservatoryDTO dto, AsyncCallback<Long> callback);

	void getObservatoryByDrainageBasinId(Long id,
			AsyncCallback<ObservatoryDTO> callback);

}
