package fr.obsmip.sedoo.client.place;

import com.google.gwt.place.shared.Place;

public abstract class AbstractEditingPlace extends Place
{
	protected final static String SEPARATOR ="!";
	protected Long id;
	protected String mode;
	
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

	public String getTokenString()
	{
		if (getMode() == null)
		{
			return "";
		}
		else
		{
			return getMode()+SEPARATOR+getId();
		}
	}
	
	public void initFromToken(String token)
	{
		if ((token != null) && (token.trim().length()>0))
		{
			String[] split = token.split(SEPARATOR);
			setMode(split[0]);
			setId(new Long(split[1]));
		}
	}
}
