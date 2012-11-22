package fr.obsmip.sedoo.client.place;

import com.google.gwt.place.shared.PlaceTokenizer;

public class DrainageBasinEditingPlace extends AbstractEditingPlace
{
	public static DrainageBasinEditingPlace instance;

	public static class Tokenizer implements PlaceTokenizer<DrainageBasinEditingPlace>
	{
		@Override
		public String getToken(DrainageBasinEditingPlace place)
		{
			return place.getTokenString();
		}

		@Override
		public DrainageBasinEditingPlace getPlace(String token)
		{
			if (instance == null)
			{
				instance = new DrainageBasinEditingPlace();
				instance.initFromToken(token);
			}
			return instance;
		}
	}
	
}
