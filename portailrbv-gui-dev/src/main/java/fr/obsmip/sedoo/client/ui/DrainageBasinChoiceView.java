package fr.obsmip.sedoo.client.ui;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;

import fr.obsmip.sedoo.client.domain.DrainageBasinDTO;
import fr.obsmip.sedoo.client.domain.ObservatoryDTO;

public interface DrainageBasinChoiceView extends IsWidget {

	void setPresenter(Presenter presenter);
	void setObservatories(List<ObservatoryDTO> observatories);
	void setDrainageBasins(List<DrainageBasinDTO> drainageBasins);
//	void updateSucces();
//	void updateFailure();
	void reset();
	public interface Presenter 
	 {
	        void createMetadata(Long drainageBasinId);
	        void getDrainageBasins(Long observatoryId);
	 }
	
}
