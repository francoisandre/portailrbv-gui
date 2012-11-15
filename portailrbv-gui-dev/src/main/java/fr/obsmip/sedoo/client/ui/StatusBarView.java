package fr.obsmip.sedoo.client.ui;

import com.google.gwt.user.client.ui.IsWidget;

import fr.obsmip.sedoo.client.event.ActionEndEventHandler;
import fr.obsmip.sedoo.client.event.ActionStartEventHandler;

public interface StatusBarView extends IsWidget, ActionEndEventHandler, ActionStartEventHandler {

	void setVersion(String version);

	double getHeight();
}
