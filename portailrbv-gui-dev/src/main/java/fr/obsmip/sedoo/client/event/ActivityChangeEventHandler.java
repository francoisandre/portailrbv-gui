package fr.obsmip.sedoo.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface ActivityChangeEventHandler extends EventHandler {
    void onNotification(ActivityChangeEvent event);
}

