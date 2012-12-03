package fr.obsmip.sedoo.client.ui.menu;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.obsmip.sedoo.client.place.DrainageBasinChoicePlace;
import fr.obsmip.sedoo.client.place.MetadataEditingPlace;
import fr.obsmip.sedoo.client.place.MetadataListPlace;
import fr.obsmip.sedoo.client.ui.misc.MenuLink;

public class MetadataProviderMenu extends AbstractMenu {
	
	private MenuLink creatingLink;
	private MenuLink metadataListLink;
	
public MetadataProviderMenu() {
	
		VerticalPanel panel = getMetadataPanel(); 
	    initWidget(panel);
	}

private VerticalPanel getMetadataPanel() {
	VerticalPanel metadataPanel = new VerticalPanel();
	metadataPanel.setSpacing(4);
	creatingLink = new MenuLink("Créer une nouvelle fiche");
	creatingLink.setTitle("Créer une nouvelle fiche");
	creatingLink.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent event) 
	        {
	        	getPresenter().goTo(new DrainageBasinChoicePlace());
	        }
	});
	metadataPanel.add(creatingLink);
	
	metadataListLink = new MenuLink("Mes fiches");
	metadataListLink.setTitle("Mes fiches");
	metadataListLink.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent event) 
	        {
	        	getPresenter().goTo(new MetadataListPlace());
	        }
	});
	metadataPanel.add(metadataListLink);
	
	
	
	return metadataPanel;
}




}
