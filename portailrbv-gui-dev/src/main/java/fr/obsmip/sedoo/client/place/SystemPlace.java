package fr.obsmip.sedoo.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class SystemPlace extends Place
{

	public static SystemPlace instance;

	public static class Tokenizer implements PlaceTokenizer<SystemPlace>
	{
		@Override
		public String getToken(SystemPlace place)
		{
			return "";
		}

		@Override
		public SystemPlace getPlace(String token)
		{
			if (instance == null)
			{
				instance = new SystemPlace();
			}
			return instance;
		}
	}
	
}
