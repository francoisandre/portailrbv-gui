package fr.obsmip.sedoo.client.place;

import com.google.gwt.place.shared.PlaceTokenizer;

public class ObservatoryContactEditingPlace extends AbstractEditingPlace
{

	public static ObservatoryContactEditingPlace instance;

	public static class Tokenizer implements PlaceTokenizer<ObservatoryContactEditingPlace>
	{
		@Override
		public String getToken(ObservatoryContactEditingPlace place)
		{
			return place.getTokenString();
		}

		@Override
		public ObservatoryContactEditingPlace getPlace(String token)
		{
			if (instance == null)
			{
				instance = new ObservatoryContactEditingPlace();
				instance.initFromToken(token);
			}
			return instance;
		}
	}
	
	
}
