package fr.obsmip.sedoo.client.ui.menu;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.place.MetadataManagingPlace;
import fr.obsmip.sedoo.client.place.ObservatoryManagementPlace;
import fr.obsmip.sedoo.client.ui.misc.MenuLink;

public class MetadataProviderMenu extends AbstractMenu {
	
	private MenuLink metadataManagingLink;
	private MenuLink observatoryMangagementLink;
	
public MetadataProviderMenu() {
	
		VerticalPanel panel = getMetadataPanel(); 
	    initWidget(panel);
	}

private VerticalPanel getMetadataPanel() {
	VerticalPanel metadataPanel = new VerticalPanel();
	metadataPanel.setSpacing(4);
	metadataManagingLink = new MenuLink(Message.INSTANCE.metadataProviderMenuManageMetadata());
	metadataManagingLink.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent event) 
	        {
	        	getPresenter().goTo(new MetadataManagingPlace());
	        }
	});
	metadataPanel.add(metadataManagingLink);
	
	observatoryMangagementLink = new MenuLink(Message.INSTANCE.observatoryManagementViewHeader());
	observatoryMangagementLink.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent event) 
	        {
	        	getPresenter().goTo(new ObservatoryManagementPlace());
	        }
	});
	metadataPanel.add(observatoryMangagementLink);
	return metadataPanel;
}




}
