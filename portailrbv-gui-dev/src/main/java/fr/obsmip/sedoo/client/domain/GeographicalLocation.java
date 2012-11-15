package fr.obsmip.sedoo.client.domain;

import java.io.Serializable;

public class GeographicalLocation implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7351392561267322429L;
	private double northBoundLatitude;
	private double southBoundLatitude;
	private double eastBoundLongitude;
	private double westBoundLongitude;
	public double getNorthBoundLatitude() {
		return northBoundLatitude;
	}
	public void setNorthBoundLatitude(double northBoundLatitude) {
		this.northBoundLatitude = northBoundLatitude;
	}
	public double getSouthBoundLatitude() {
		return southBoundLatitude;
	}
	public void setSouthBoundLatitude(double southBoundLatitude) {
		this.southBoundLatitude = southBoundLatitude;
	}
	public double getEastBoundLongitude() {
		return eastBoundLongitude;
	}
	public void setEastBoundLongitude(double eastBoundLongitude) {
		this.eastBoundLongitude = eastBoundLongitude;
	}
	public double getWestBoundLongitude() {
		return westBoundLongitude;
	}
	public void setWestBoundLongitude(double westBoundLongitude) {
		this.westBoundLongitude = westBoundLongitude;
	}
	
	
	

}
