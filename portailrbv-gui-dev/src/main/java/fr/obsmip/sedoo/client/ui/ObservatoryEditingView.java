package fr.obsmip.sedoo.client.ui;

import fr.obsmip.sedoo.client.domain.ObservatoryDTO;

public interface ObservatoryEditingView extends DTOEditingView {
	
	
	public interface Presenter 
	 {
		void createDrainageBasin(ObservatoryDTO observatory);
		void editDrainageBasin(Long id);
		void save(ObservatoryDTO flush);
		void deleteDrainageBasin(Long id);
	 }

	void setPresenter(Presenter presenter);
	public void broadcastDrainageBasinDeletion(Long id, boolean result);
	
}
