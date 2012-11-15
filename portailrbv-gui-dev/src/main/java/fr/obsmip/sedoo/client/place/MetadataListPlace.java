package fr.obsmip.sedoo.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MetadataListPlace extends Place
{
	public static MetadataListPlace instance;
	
	public MetadataListPlace()
	{
	}

	public static class Tokenizer implements PlaceTokenizer<MetadataListPlace>
	{
		
		public MetadataListPlace getPlace(String token) {
			if (instance == null)
			{
				instance = new MetadataListPlace();
			}
			return instance;
		}

		public String getToken(MetadataListPlace place) {
			return "";
		}

		
	}
}
