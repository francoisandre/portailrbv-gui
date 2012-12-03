package fr.obsmip.sedoo.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface PopUpEventHandler extends EventHandler {
    void onNotification(PopUpEvent event);
}

