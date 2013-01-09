package fr.obsmip.sedoo.client.domain;

import java.io.Serializable;
import java.util.List;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.view.client.ProvidesKey;
import com.sun.corba.se.pept.transport.ContactInfo;

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
	
	public static final ProvidesKey<HasId> KEY_PROVIDER = new ProvidesKey<HasId>() {
	      @Override
	      public Object getKey(HasId item) {
	        return item == null ? null : item.getId();
	      }
	    };
	    
	    
	    public static boolean checkDouble(String aux) 
		{
			if (aux == null)
			{
				return false;
			}
			else
			{
				try
				{
					NumberFormat.getDecimalFormat().parse(aux);
					return true;
				}
				catch (NumberFormatException e)
				{
					return false;
				}
			}
		}

		public static String protectNullObject(Object obj) {
			if (obj == null)
			{
				return "";
			}
			else
			{
				return obj.toString();
			}
		}
	
}
