package fr.obsmip.sedoo.client.domain;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractDTO implements Serializable{
	
	public static String protectNullString(String value)
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
	
	public static boolean isEmpty(String value)
	{
		String aux = protectNullString(value);
		return (aux.length()==0);
	}

	public abstract String getHash();
	
	public abstract List<ValidationAlert> validate();
	
}
