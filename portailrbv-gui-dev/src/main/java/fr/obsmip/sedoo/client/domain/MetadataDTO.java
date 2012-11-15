package fr.obsmip.sedoo.client.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MetadataDTO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5447272101770073769L;
	/**
	 * 
	 */
	private String resourceTitle;
	private String resourceAbstract;
	private List<String> resourceURL = new ArrayList<String>();
	private GeographicalLocation geographicalLocation;

//	String getResourceTitle();
//	void setResourceTitle(String resourceTitle);
//	String getResourceAbstract();
//	void setResourceAbstract(String resourceAbstract);
	
	public String getResourceTitle() {
		return resourceTitle;
	}
	public void setResourceTitle(String resourceTitle) {
		this.resourceTitle = resourceTitle;
	}
	public String getResourceAbstract() {
		return resourceAbstract;
	}
	public void setResourceAbstract(String resourceAbstract) {
		this.resourceAbstract = resourceAbstract;
	}
	public List<String> getResourceURL() {
		return resourceURL;
	}
	public void setResourceURL(List<String> resourceURL) {
		this.resourceURL = resourceURL;
	}
	public GeographicalLocation getGeographicalBoundingBox() {
		return geographicalLocation;
	}
	public void setGeographicalBoundingBox(GeographicalLocation geographicalBoundingBox) {
		this.geographicalLocation = geographicalBoundingBox;
	}
	
	
	
	
}

