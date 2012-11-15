package fr.obsmip.sedoo.client.ui;

import com.google.gwt.user.client.ui.IsWidget;

import fr.obsmip.sedoo.client.domain.MetadataDTO;

public interface MetadataEditingView extends IsWidget
{
	void setPresenter(Presenter presenter);
	void edit(MetadataDTO metadataDTO);
	void setGeneratedXML(String xml);
	
	 public interface Presenter 
	 {
	        void generateXML(MetadataDTO metadataDTO);
	 }

}
