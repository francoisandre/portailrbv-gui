package fr.obsmip.sedoo.client.ui;

import fr.obsmip.sedoo.client.domain.metadata.MetadataDTO;
import fr.obsmip.sedoo.client.ui.table.MetadataContactSelectionTable;

public interface MetadataEditingView extends DTOEditingView
{
	void setPresenter(Presenter presenter);
	void setGeneratedXML(String xml);
	
	 public interface Presenter 
	 {
	        void generateXML(MetadataDTO metadataDTO);
			void getObservatoryContacts(MetadataContactSelectionTable metadataContactSelectionTable);
	 }

}
