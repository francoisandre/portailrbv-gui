package fr.obsmip.sedoo.client.domain.metadata;

import java.util.ArrayList;
import java.util.List;

import fr.obsmip.sedoo.client.domain.AbstractDTO;
import fr.obsmip.sedoo.client.domain.GeographicalLocation;
import fr.obsmip.sedoo.client.domain.ValidationAlert;


public class MetadataDTO extends AbstractDTO
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
	private MetadataPart metadataPart = new MetadataPart();
	

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
	
	
	@Override
	public String getHash() {
		return "@"+protectNullString(getResourceTitle())+"|"+getMetadataPart().getHash();
	}
	@Override
	public List<ValidationAlert> validate() {
		List<ValidationAlert> result = new ArrayList<ValidationAlert>();
		return result;
	}
	
	public MetadataPart getMetadataPart() {
		return metadataPart;
	}
	public void setMetadataPart(MetadataPart part) 
	{
		this.metadataPart = part;
	}
	
}

