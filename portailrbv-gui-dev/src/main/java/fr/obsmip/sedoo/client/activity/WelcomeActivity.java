package fr.obsmip.sedoo.client.activity;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.obsmip.sedoo.client.ClientFactory;
import fr.obsmip.sedoo.client.ShortcutFactory;
import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.place.WelcomePlace;
import fr.obsmip.sedoo.client.ui.WelcomeView;
import fr.obsmip.sedoo.client.ui.misc.Shortcut;

public class WelcomeActivity extends RBVAbstractActivity  {

    public WelcomeActivity(WelcomePlace place, ClientFactory clientFactory) {
        super(clientFactory);
    }

    /**
     * Invoked by the ActivityManager to start a new Activity
     */
    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
        WelcomeView welcomeView = clientFactory.getWelcomeView();
        containerWidget.setWidget(welcomeView.asWidget());
        broadcastActivityTitle(Message.INSTANCE.welcomeViewHeader());
        List<Shortcut> shortcuts = new ArrayList<Shortcut>();
		shortcuts.add(ShortcutFactory.getWelcomeShortcut());
		clientFactory.getBreadCrumb().refresh(shortcuts);
    }

    /**
     * Ask user before stopping this activity
     */
    @Override
    public String mayStop() {
//        return "Please hold on. This activity is stopping.";
    	return null;
    }

    /**
     * Navigate to a new Place in the browser
     */
    public void goTo(Place place) {
        clientFactory.getPlaceController().goTo(place);
    }
}