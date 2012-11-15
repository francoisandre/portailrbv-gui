package fr.obsmip.sedoo.client.domain;

import java.io.Serializable;

public class GeographicBoundingBoxDTO implements Serializable {
	
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

}
