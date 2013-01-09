package fr.obsmip.sedoo.client.ui.menu;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.place.SystemPlace;
import fr.obsmip.sedoo.client.ui.misc.MenuLink;

public class AdministrationMenu extends AbstractMenu {
	
	private MenuLink versionLink;
	
	
public AdministrationMenu() {
	
		VerticalPanel panel = getAdministrationPanel(); 
	    initWidget(panel);
	}

private VerticalPanel getAdministrationPanel() {
	VerticalPanel administrationPanel = new VerticalPanel();
	administrationPanel.setSpacing(4);
	versionLink = new MenuLink(Message.INSTANCE.systemViewHeader());
	versionLink.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent event) 
	        {
	        	getPresenter().goTo(new SystemPlace());
	        }
	});
	
	administrationPanel.add(versionLink);
	return administrationPanel;
}

}
