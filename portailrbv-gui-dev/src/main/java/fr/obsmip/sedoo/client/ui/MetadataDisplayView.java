package fr.obsmip.sedoo.client.ui;

import com.google.gwt.user.client.ui.IsWidget;

import fr.obsmip.sedoo.client.domain.MetadataDTO;

public interface MetadataDisplayView extends IsWidget
{
	void setPresenter(Presenter presenter);
	void display(MetadataDTO metadataDTO);
	
	 public interface Presenter 
	 {
	 }

	void reset();

}
