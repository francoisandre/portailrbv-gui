package fr.obsmip.sedoo.client.mvp;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;

import fr.obsmip.sedoo.client.ClientFactory;

public class MenuPresenterImpl implements Presenter {


	
	private PlaceController placeController;

	public MenuPresenterImpl(ClientFactory clientFactory) 
	{
		placeController = clientFactory.getPlaceController();
	}
	
	@Override
	public void goTo(Place place) 
	{
		placeController.goTo(place);
	}
	
	

}
