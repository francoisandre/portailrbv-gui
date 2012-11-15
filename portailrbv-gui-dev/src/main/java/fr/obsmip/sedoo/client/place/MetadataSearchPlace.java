package fr.obsmip.sedoo.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MetadataSearchPlace extends Place
{
	public static MetadataSearchPlace instance;
	
	public MetadataSearchPlace()
	{
	}

	public static class Tokenizer implements PlaceTokenizer<MetadataSearchPlace>
	{
		
		public MetadataSearchPlace getPlace(String token) {
			if (instance == null)
			{
				instance = new MetadataSearchPlace();
			}
			return instance;
		}

		public String getToken(MetadataSearchPlace place) {
			return "";
		}

		
	}
}
