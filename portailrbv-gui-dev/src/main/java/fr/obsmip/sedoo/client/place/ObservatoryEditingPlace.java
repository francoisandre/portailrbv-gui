package fr.obsmip.sedoo.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ObservatoryEditingPlace extends Place
{

	private Long observatoryId;
	
	public static ObservatoryEditingPlace instance;

	public static class Tokenizer implements PlaceTokenizer<ObservatoryEditingPlace>
	{
		@Override
		public String getToken(ObservatoryEditingPlace place)
		{
			if (place.getObservatoryId() == null)
			{
				return "";
			}
			else
			{
				return ""+place.getObservatoryId();
			}
			
		}

		@Override
		public ObservatoryEditingPlace getPlace(String token)
		{
			if (instance == null)
			{
				instance = new ObservatoryEditingPlace();
				if ((token != null) && (token.trim().length()>0))
				{
					instance.setObservatoryId(new Long(token));
				}
			}
			return instance;
		}
	}

	public Long getObservatoryId() {
		return observatoryId;
	}

	public void setObservatoryId(Long observatoryId) {
		this.observatoryId = observatoryId;
	}

	
	
}
