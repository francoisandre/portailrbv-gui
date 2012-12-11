package fr.obsmip.sedoo.client.domain;

import java.util.ArrayList;
import java.util.List;

import org.gwtopenmaps.openlayers.client.Bounds;
import org.gwtopenmaps.openlayers.client.LonLat;

import com.google.gwt.i18n.client.NumberFormat;

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
		
		if (!checkDouble(getNorthBoundLatitude()))
		{
			result.add(new ValidationAlert(Message.INSTANCE.mapSelectorNorthLatitude(), Message.INSTANCE.numericalData()));
		}
		
		if (!checkDouble(getSouthBoundLatitude()))
		{
			result.add(new ValidationAlert(Message.INSTANCE.mapSelectorSouthLatitude(), Message.INSTANCE.numericalData()));
		}
		
		if (!checkDouble(getEastBoundLongitude()))
		{
			result.add(new ValidationAlert(Message.INSTANCE.mapSelectorEastLongitude(), Message.INSTANCE.numericalData()));
		}
		
		if (!checkDouble(getWestBoundLongitude()))
		{
			result.add(new ValidationAlert(Message.INSTANCE.mapSelectorWestLongitude(), Message.INSTANCE.numericalData()));
		}
		return result;
		
	}
	
	
	public LonLat getRightLowerCorner() {
		return new LonLat(NumberFormat.getDecimalFormat().parse(eastBoundLongitude), NumberFormat.getDecimalFormat().parse(southBoundLatitude));
	}
	public LonLat getLeftUpperCorner() 
	{
		return new LonLat(NumberFormat.getDecimalFormat().parse(westBoundLongitude), NumberFormat.getDecimalFormat().parse(northBoundLatitude));
	}
	public LonLat getRightLowerDisplayCorner() {
		
		double horizontalPadding = getHorizontalPadding();
		double verticalPadding = getVerticalPadding();
		return new LonLat(NumberFormat.getDecimalFormat().parse(eastBoundLongitude)+horizontalPadding, NumberFormat.getDecimalFormat().parse(southBoundLatitude)-verticalPadding);
	}
	
	public LonLat getLeftUpperDisplayCorner() 
	{
		double horizontalPadding = getHorizontalPadding();
		double verticalPadding = getVerticalPadding();
		return new LonLat(NumberFormat.getDecimalFormat().parse(westBoundLongitude)-horizontalPadding, NumberFormat.getDecimalFormat().parse(northBoundLatitude)+verticalPadding);
	}
	
	public double getHorizontalPadding()
	{
		 return 0.2*(NumberFormat.getDecimalFormat().parse(eastBoundLongitude)-NumberFormat.getDecimalFormat().parse(westBoundLongitude));
	}
	
	public double getVerticalPadding()
	{
		 return 0.2*(NumberFormat.getDecimalFormat().parse(northBoundLatitude)-NumberFormat.getDecimalFormat().parse(southBoundLatitude));
	}
	

}
