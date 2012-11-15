package fr.obsmip.sedoo.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MetadataEditingPlace extends Place
{
	
	public MetadataEditingPlace() {
		super();
	}
	
	public MetadataEditingPlace(String id)
	{
		super();
		setId(id);
	}
	
	private String id;
	
	public static MetadataEditingPlace instance;

	public static class Tokenizer implements PlaceTokenizer<MetadataEditingPlace>
	{
		@Override
		public String getToken(MetadataEditingPlace place)
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
		public MetadataEditingPlace getPlace(String token)
		{
			if (instance == null)
			{
				instance = new MetadataEditingPlace();
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
