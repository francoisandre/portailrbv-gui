package fr.obsmip.sedoo.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface UserLogoutEventHandler extends EventHandler {
    void onNotification(UserLogoutEvent event);
}

