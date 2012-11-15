package fr.obsmip.sedoo.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class WelcomePlace extends Place
{
	public static WelcomePlace instance;
	
	public WelcomePlace()
	{
	}

	public static class Tokenizer implements PlaceTokenizer<WelcomePlace>
	{
		
		public WelcomePlace getPlace(String token) {
			if (instance == null)
			{
				instance = new WelcomePlace();
			}
			return instance;
		}

		public String getToken(WelcomePlace place) {
			return "";
		}

		
	}
}
