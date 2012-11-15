package fr.obsmip.sedoo.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class SwitchLanguagePlace extends Place
{

	public static final String FRENCH = "fr";
	public static final String ENGLISH = "en";
	
	public static SwitchLanguagePlace instance;
	
	private String locale;
	
	
	public SwitchLanguagePlace(String locale)
	{
		super();
		this.locale = locale;
	}

	public static class Tokenizer implements PlaceTokenizer<SwitchLanguagePlace>
	{
		@Override
		public String getToken(SwitchLanguagePlace place)
		{
			return "";
		}

		@Override
		public SwitchLanguagePlace getPlace(String token)
		{
			if (instance == null)
			{
				instance = new SwitchLanguagePlace(ENGLISH);
			}
			return instance;
		}
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}
	
}
