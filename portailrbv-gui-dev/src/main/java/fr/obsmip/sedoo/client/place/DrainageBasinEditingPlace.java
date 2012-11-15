package fr.obsmip.sedoo.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class DrainageBasinEditingPlace extends Place
{
	private final static String SEPARATOR ="!";
	public static final String CREATE = "CREATE";
	public static final String MODIFY = "MODIFY";
	private Long id;
	private String mode;
	
	public static DrainageBasinEditingPlace instance;

	public static class Tokenizer implements PlaceTokenizer<DrainageBasinEditingPlace>
	{
		@Override
		public String getToken(DrainageBasinEditingPlace place)
		{
			if (place.getMode() == null)
			{
				return "";
			}
			else
			{
				return place.getMode()+SEPARATOR+place.getId();
			}
		}

		@Override
		public DrainageBasinEditingPlace getPlace(String token)
		{
			if (instance == null)
			{
				instance = new DrainageBasinEditingPlace();
				if ((token != null) && (token.trim().length()>0))
				{
					String[] split = token.split(SEPARATOR);
					instance.setMode(split[0]);
					instance.setId(new Long(split[1]));
				}
			}
			return instance;
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

}
