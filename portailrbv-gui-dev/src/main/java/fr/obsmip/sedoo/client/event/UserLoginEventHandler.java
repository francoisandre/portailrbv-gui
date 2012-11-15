package fr.obsmip.sedoo.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface UserLoginEventHandler extends EventHandler {
    void onNotification(UserLoginEvent event);
}

