package fr.obsmip.sedoo.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class LoginPlace extends Place
{

	public static LoginPlace instance;

	public static class Tokenizer implements PlaceTokenizer<LoginPlace>
	{
		@Override
		public String getToken(LoginPlace place)
		{
			return "";
		}

		@Override
		public LoginPlace getPlace(String token)
		{
			if (instance == null)
			{
				instance = new LoginPlace();
			}
			return instance;
		}
	}
	
}
