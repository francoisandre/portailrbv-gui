package fr.obsmip.sedoo.client.ui;

import com.google.gwt.user.client.ui.IsWidget;

import fr.obsmip.sedoo.client.domain.DrainageBasinDTO;

public interface DrainageBasinEditingView extends IsWidget {
	
	public void edit(DrainageBasinDTO drainageBasinDTO);
	public void setPresenter(Presenter presenter);
	
	public interface Presenter 
	 {
	 }

}
