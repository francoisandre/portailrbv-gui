package fr.obsmip.sedoo.client.ui;

import com.google.gwt.user.client.ui.IsWidget;

import fr.obsmip.sedoo.client.domain.ObservatoryDTO;

public interface ObservatoryEditingView extends IsWidget {
	
	
	public interface Presenter 
	 {
		void createDrainageBasin(ObservatoryDTO observatory);
		void editDrainageBasin(Long id);
		void save(ObservatoryDTO flush);
		void deleteDrainageBasin(Long id);
	 }

	void setPresenter(Presenter presenter);
	void edit(ObservatoryDTO observatory);
	ObservatoryDTO flush();
	
}
