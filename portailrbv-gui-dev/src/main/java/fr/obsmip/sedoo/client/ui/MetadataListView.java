package fr.obsmip.sedoo.client.ui;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;

import fr.obsmip.sedoo.client.domain.SummaryDTO;

public interface MetadataListView extends IsWidget{

	void setSummaryDTOList(List<SummaryDTO> summaries);
	void setPresenter(Presenter presenter);
	
	public interface Presenter 
	 {
	        void displayMetadataPDF(String id);
	        void displayMetadata(String id);
	 }
	

}
