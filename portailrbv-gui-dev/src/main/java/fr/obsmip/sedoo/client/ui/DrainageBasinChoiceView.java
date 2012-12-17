package fr.obsmip.sedoo.client.ui;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;

import fr.obsmip.sedoo.client.domain.DrainageBasinDTO;
import fr.obsmip.sedoo.client.domain.ExtendedSummaryDTO;
import fr.obsmip.sedoo.client.domain.ObservatoryDTO;

public interface DrainageBasinChoiceView extends IsWidget {

	void setPresenter(Presenter presenter);
	void setObservatories(List<ObservatoryDTO> observatories);
	void setDrainageBasins(List<DrainageBasinDTO> drainageBasins);
	void setEntries(List<ExtendedSummaryDTO> result);
	void reset();
	public interface Presenter 
	 {
	        void createMetadata(Long drainageBasinId);
	        void editMetadata(String uuid, Long drainageBasinId);
	        void viewMetadata(String uuid);
	        void printMetadata(String uuid);
	        void getDrainageBasins(Long observatoryId);
			void getEntries(Long drainageBasinId);
	 }
	
	
}
