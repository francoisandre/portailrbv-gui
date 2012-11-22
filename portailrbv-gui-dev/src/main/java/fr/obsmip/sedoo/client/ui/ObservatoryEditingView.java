package fr.obsmip.sedoo.client.ui;

import fr.obsmip.sedoo.client.domain.ObservatoryDTO;

public interface ObservatoryEditingView extends DTOEditingView {
	
	
	public interface Presenter 
	 {
		void createDrainageBasin(ObservatoryDTO observatory);
		void createObservatoryContact(ObservatoryDTO observatoryDTO);
		void editDrainageBasin(Long id);
		void editObservatoryContact(Long id);
		void save(ObservatoryDTO dto);
		void deleteDrainageBasin(Long id);
		void deletePerson(Long id);
		void back();
	 }

	void setPresenter(Presenter presenter);
	public void broadcastDrainageBasinDeletion(Long id, boolean result);
	public void broadcastPersonDeletion(Long id, boolean result);
	
}
