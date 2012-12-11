package fr.obsmip.sedoo.client.ui.misc;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;

import fr.obsmip.sedoo.client.ClientFactory;
import fr.obsmip.sedoo.client.event.NotificationHandler;

public interface BreadCrumb extends IsWidget {

	void setClientFactory(ClientFactory clientFactory);
	void refresh(List<Shortcut> shortcuts);

}
