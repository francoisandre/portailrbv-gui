package fr.obsmip.sedoo.client.domain;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractDTO implements Serializable{
	
	protected String protectNullString(String value)
	{
		if (value == null)
		{
			return "";
		}
		else
		{
			return value.trim();
		}
	}

	public abstract String getHash();
	
	public abstract List<ValidationAlert> validate();
	
}
