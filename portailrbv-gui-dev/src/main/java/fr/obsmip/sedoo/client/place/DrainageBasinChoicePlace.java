package fr.obsmip.sedoo.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class DrainageBasinChoicePlace extends Place
{
	public static DrainageBasinChoicePlace instance;

	public static class Tokenizer implements PlaceTokenizer<DrainageBasinChoicePlace>
	{
		@Override
		public String getToken(DrainageBasinChoicePlace place)
		{
			return "";
		}

		@Override
		public DrainageBasinChoicePlace getPlace(String token)
		{
			if (instance == null)
			{
				instance = new DrainageBasinChoicePlace();
			}
			return instance;
		}
	}
	
}
