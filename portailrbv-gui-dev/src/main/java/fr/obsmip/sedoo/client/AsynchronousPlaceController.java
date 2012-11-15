package fr.obsmip.sedoo.client;

import java.util.logging.Logger;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.gwt.place.shared.PlaceChangeRequestEvent;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.ui.misc.ConfirmCallBack;
import fr.obsmip.sedoo.client.ui.misc.DialogBoxTools;



/**
 * @author F. ANDRE
 * Cette classe surcharge le mécanisme standard de PlaceController afin de lui permettre d'afficher le message de confirmation dans une DialogBox (asynchrone) au lieu d'un Alert(synchrone)
 *
 */

public class AsynchronousPlaceController extends PlaceController implements ConfirmCallBack{

	private final EventBus eventBus;
	private Place where = Place.NOWHERE;
	private Place pendingPlace = Place.NOWHERE;
	
	public AsynchronousPlaceController(EventBus eventBus) {
		super(eventBus);
		this.eventBus = eventBus;
	}
	
	
	
	private static final Logger log = Logger.getLogger(AsynchronousPlaceController.class.getName());

	public void goTo(Place newPlace) {
		log.fine("goTo: " + newPlace);

	    if (getWhere().equals(newPlace)) {
	      log.fine("Asked to return to the same place: " + newPlace);
	      return;
	    }

	    String warning = maybeGoTo(newPlace);
	    if (warning != null)
	    {
	    	pendingPlace = newPlace;
	    	DialogBoxTools.modalConfirm(Message.INSTANCE.confirm(), warning, this).center();
	    }
	    else
	    {
	    	setWhere(newPlace);
	    	eventBus.fireEvent(new PlaceChangeEvent(newPlace));
	    }
	  }
	
	private String maybeGoTo(Place newPlace) {
	    PlaceChangeRequestEvent willChange = new PlaceChangeRequestEvent(newPlace);
	    eventBus.fireEvent(willChange);
	    String warning = willChange.getWarning();
	    return warning;
	  }

	
	@Override
	public Place getWhere() {
		return where;
	}
	
	public void setWhere(Place where) {
		this.where = where;
	}

	@Override
	public void confirm(boolean choice) 
	{
		if (choice == true)
		{
			setWhere(pendingPlace);
		    eventBus.fireEvent(new PlaceChangeEvent(pendingPlace));
		}
		
	}
	
}
