package fr.obsmip.sedoo.client.ui;

import fr.obsmip.sedoo.client.domain.DrainageBasinDTO;

public interface DrainageBasinEditingView extends DTOEditingView {
	
	void setPresenter(Presenter presenter);
	
	interface Presenter 
	 {

		void save(DrainageBasinDTO flush);
		void deleteDrainageBasin(Long id);
	 }

	DrainageBasinDTO flush();

}
