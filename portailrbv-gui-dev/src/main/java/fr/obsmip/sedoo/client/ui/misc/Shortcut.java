package fr.obsmip.sedoo.client.ui.misc;

import com.google.gwt.place.shared.Place;

public class Shortcut 
{
	private String label;
	private Place place;
	
	public Shortcut(String label, Place place) 
	{
		this.setLabel(label);
		this.setPlace(place);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}
}
