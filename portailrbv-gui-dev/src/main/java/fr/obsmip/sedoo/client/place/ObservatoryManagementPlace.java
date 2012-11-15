package fr.obsmip.sedoo.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ObservatoryManagementPlace extends Place
{

	public static ObservatoryManagementPlace instance;

	public static class Tokenizer implements PlaceTokenizer<ObservatoryManagementPlace>
	{
		@Override
		public String getToken(ObservatoryManagementPlace place)
		{
			return "";
		}

		@Override
		public ObservatoryManagementPlace getPlace(String token)
		{
			if (instance == null)
			{
				instance = new ObservatoryManagementPlace();
			}
			return instance;
		}
	}
	
}
