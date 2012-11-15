package fr.obsmip.sedoo.client.ui.menu;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.obsmip.sedoo.client.place.MetadataSearchPlace;
import fr.obsmip.sedoo.client.ui.misc.MenuLink;

public class MetadataUserMenu extends AbstractMenu {
	
	
	private MenuLink metadataSearchLink;
	
public MetadataUserMenu() {
	
		VerticalPanel panel = getMetadataPanel(); 
	    initWidget(panel);
	}

private VerticalPanel getMetadataPanel() {
	VerticalPanel metadataPanel = new VerticalPanel();
	metadataPanel.setSpacing(4);
	metadataSearchLink = new MenuLink("Rechercher une fiche");
	
	metadataSearchLink.setTitle("Rechercher une fiche");
	metadataSearchLink.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent event) 
	        {
	        	getPresenter().goTo(new MetadataSearchPlace());
	        }
	});
	metadataPanel.add(metadataSearchLink);
	
	
	
	return metadataPanel;
}


}
