package fr.obsmip.sedoo.client.ui.misc;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;

import fr.obsmip.sedoo.client.ClientFactory;

public interface BreadCrumb extends IsWidget {

	void setClientFactory(ClientFactory clientFactory);
	void refresh(List<Shortcut> shortcuts);
	void addShortcut(Shortcut shortcut);
	List<Shortcut> getShortcuts();

}
