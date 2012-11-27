package fr.obsmip.sedoo.client.ui;

import java.util.List;

import fr.obsmip.sedoo.client.domain.DrainageBasinDTO;
import fr.obsmip.sedoo.client.domain.ThesaurusItemDTO;

public interface DrainageBasinEditingView extends DTOEditingView {
	
	void setPresenter(Presenter presenter);
	
	public interface Presenter 
	 {
		void save(DrainageBasinDTO flush);
		void deleteSite(Long id);
	 }

	DrainageBasinDTO flush();
	void broadcastSiteDeletion(Long id, boolean b);
	
	void updateClimateList(List<ThesaurusItemDTO> items);
	void updateLithologyList(List<ThesaurusItemDTO> items);

}
