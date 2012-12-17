package fr.obsmip.sedoo.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MetadataManagingPlace extends Place
{
	public static MetadataManagingPlace instance;

	public static class Tokenizer implements PlaceTokenizer<MetadataManagingPlace>
	{
		@Override
		public String getToken(MetadataManagingPlace place)
		{
			return "";
		}

		@Override
		public MetadataManagingPlace getPlace(String token)
		{
			if (instance == null)
			{
				instance = new MetadataManagingPlace();
			}
			return instance;
		}
	}
	
}
