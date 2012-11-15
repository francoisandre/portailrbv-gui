package fr.obsmip.sedoo.client.activity;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.obsmip.sedoo.client.ClientFactory;
import fr.obsmip.sedoo.client.place.MetadataSearchPlace;
import fr.obsmip.sedoo.client.ui.MetadataSearchView;

public class MetadataSearchActivity extends RBVAbstractActivity  {

    public MetadataSearchActivity(MetadataSearchPlace place, ClientFactory clientFactory) {
        super(clientFactory);
    }

    /**
     * Invoked by the ActivityManager to start a new Activity
     */
    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
    	MetadataSearchView metadataSearchView = clientFactory.getMetadataSearchView();
        containerWidget.setWidget(metadataSearchView.asWidget());
        broadcastActivityTitle(getMessage().metadataShearchingTitle());
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