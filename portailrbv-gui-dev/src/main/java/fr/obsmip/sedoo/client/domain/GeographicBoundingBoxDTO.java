package fr.obsmip.sedoo.client.domain;

import java.util.ArrayList;
import java.util.List;

import fr.obsmip.sedoo.client.message.Message;

public class GeographicBoundingBoxDTO extends AbstractDTO {
	
	private String  northBoundLatitude;
	private String  southBoundLatitude;
	private String  eastBoundLongitude;
	private String  westBoundLongitude;
	
	public String getNorthBoundLatitude() {
		return northBoundLatitude;
	}
	public void setNorthBoundLatitude(String northBoundLatitude) {
		this.northBoundLatitude = northBoundLatitude;
	}
	public String getSouthBoundLatitude() {
		return southBoundLatitude;
	}
	public void setSouthBoundLatitude(String southBoundLatitude) {
		this.southBoundLatitude = southBoundLatitude;
	}
	public String getEastBoundLongitude() {
		return eastBoundLongitude;
	}
	public void setEastBoundLongitude(String eastBoundLongitude) {
		this.eastBoundLongitude = eastBoundLongitude;
	}
	public String getWestBoundLongitude() {
		return westBoundLongitude;
	}
	public void setWestBoundLongitude(String westBoundLongitude) {
		this.westBoundLongitude = westBoundLongitude;
	}
	public String getHash() {
		return "@"+protectNullString(getNorthBoundLatitude())+"|"+protectNullString(getSouthBoundLatitude())+"|"+protectNullString(getEastBoundLongitude())+"|"+protectNullString(getWestBoundLongitude());
	}
	@Override
	public List<ValidationAlert> validate() {
		List<ValidationAlert> result = new ArrayList<ValidationAlert>();
		
		if (checkDouble(getNorthBoundLatitude()))
		{
			result.add(new ValidationAlert(Message.INSTANCE.mapSelectorNorthLatitude(), Message.INSTANCE.numericalData()));
		}
		
		if (checkDouble(getSouthBoundLatitude()))
		{
			result.add(new ValidationAlert(Message.INSTANCE.mapSelectorSouthLatitude(), Message.INSTANCE.numericalData()));
		}
		
		if (checkDouble(getEastBoundLongitude()))
		{
			result.add(new ValidationAlert(Message.INSTANCE.mapSelectorEastLongitude(), Message.INSTANCE.numericalData()));
		}
		
		if (checkDouble(getWestBoundLongitude()))
		{
			result.add(new ValidationAlert(Message.INSTANCE.mapSelectorWestLongitude(), Message.INSTANCE.numericalData()));
		}
		return result;
		
	}
	
	private boolean checkDouble(String aux) 
	{
		if (aux == null)
		{
			return true;
		}
		else
		{
			try
			{
				Double tmp = new Double(aux);
				return true;
			}
			catch (NumberFormatException e)
			{
				return false;
			}
		}
	}

}
