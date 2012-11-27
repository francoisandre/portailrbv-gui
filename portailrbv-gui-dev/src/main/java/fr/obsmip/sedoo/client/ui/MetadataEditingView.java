package fr.obsmip.sedoo.client.ui;

import fr.obsmip.sedoo.client.domain.metadata.MetadataDTO;

public interface MetadataEditingView extends DTOEditingView
{
	void setPresenter(Presenter presenter);
	void setGeneratedXML(String xml);
	
	 public interface Presenter 
	 {
	        void generateXML(MetadataDTO metadataDTO);
	 }

}
