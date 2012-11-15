package fr.obsmip.sedoo.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MetadataDisplayPlace extends Place
{
	
	public MetadataDisplayPlace() {
		super();
	}
	
	public MetadataDisplayPlace(String id)
	{
		super();
		setId(id);
	}
	
	private String id;
	
	public static MetadataDisplayPlace instance;

	public static class Tokenizer implements PlaceTokenizer<MetadataDisplayPlace>
	{
		@Override
		public String getToken(MetadataDisplayPlace place)
		{
			if (place.getId()==null)
			{
				return "";
			}
			else
			{
				return place.getId();
			}
		}

		@Override
		public MetadataDisplayPlace getPlace(String token)
		{
			if (instance == null)
			{
				instance = new MetadataDisplayPlace();
			}
			if (token.length()>0)
			{
				instance.setId(token);
			}
			else
			{
				instance.setId(null);
			}
			return instance;
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
