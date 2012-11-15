package fr.obsmip.sedoo.client.ui;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;

import fr.obsmip.sedoo.client.domain.ObservatoryDTO;

public interface ObservatoryManagementView extends IsWidget{
	
	void init(List<ObservatoryDTO> observatories);
	public void broadcastObservatoryDeletion(Long id, boolean result);
	
	void setPresenter(Presenter presenter);
	
	public interface Presenter 
	 {
	        void deleteObservatory(Long id);
	        void modifyObservatory(ObservatoryDTO observatoryDTO);
	        int addObservatory(ObservatoryDTO observatoryDTO);
			void goToCreateObservatoryActivity();
			void goToEditObservatoryActivity(ObservatoryDTO observatoryDTO);
	 }
	

}
