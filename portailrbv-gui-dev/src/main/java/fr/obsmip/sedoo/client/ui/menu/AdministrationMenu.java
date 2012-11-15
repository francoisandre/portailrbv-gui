package fr.obsmip.sedoo.client.ui.menu;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.obsmip.sedoo.client.place.ObservatoryManagementPlace;
import fr.obsmip.sedoo.client.place.SystemPlace;
import fr.obsmip.sedoo.client.ui.misc.MenuLink;

public class AdministrationMenu extends AbstractMenu {
	
	private MenuLink versionLink;
	private MenuLink siteMangagementLink;
	private Anchor logLink;
	private Anchor baseManagementLink;
	
public AdministrationMenu() {
	
		VerticalPanel panel = getAdministrationPanel(); 
	    initWidget(panel);
	}

private VerticalPanel getAdministrationPanel() {
	VerticalPanel administrationPanel = new VerticalPanel();
	administrationPanel.setSpacing(4);
	versionLink = new MenuLink("Informations système");
	versionLink.setTitle("Affiche...");
	versionLink.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent event) 
	        {
	        	getPresenter().goTo(new SystemPlace());
	        }
	});
	
	siteMangagementLink = new MenuLink("Gestion des observatoires");
	siteMangagementLink.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent event) 
	        {
	        	getPresenter().goTo(new ObservatoryManagementPlace());
	        }
	});
	
	
	administrationPanel.add(versionLink);
	administrationPanel.add(siteMangagementLink);
	
	
	
	return administrationPanel;
}

}
