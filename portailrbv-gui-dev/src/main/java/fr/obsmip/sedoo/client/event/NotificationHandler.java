package fr.obsmip.sedoo.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface NotificationHandler extends EventHandler {
    void onNotification(NotificationEvent event);
}

