package fr.obsmip.sedoo.client.ui;

import fr.obsmip.sedoo.client.domain.DrainageBasinDTO;

public interface DrainageBasinEditingView extends DTOEditingView {
	
	void setPresenter(Presenter presenter);
	
	public interface Presenter 
	 {
		void save(DrainageBasinDTO flush);
		void deleteSite(Long id);
	 }

	DrainageBasinDTO flush();
	void broadcastSiteDeletion(Long id, boolean b);

}
