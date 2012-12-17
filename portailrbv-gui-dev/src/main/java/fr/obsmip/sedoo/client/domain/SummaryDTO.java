package fr.obsmip.sedoo.client.domain;

import java.io.Serializable;

public class SummaryDTO implements Serializable {
	
	private static final int MAX_ABSTRACT_LENGTH = 1000;
	private String resourceAbstract;
	private String resourceTitle;
	private String uuid;
	
	
	public SummaryDTO() {
		setResourceAbstract("");
		setResourceTitle("");
	}
	
	
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
		if (resourceAbstract.length() > MAX_ABSTRACT_LENGTH)
		{
			this.resourceAbstract = resourceAbstract.substring(0, MAX_ABSTRACT_LENGTH)+"[...]";
		}
		else
		{
			this.resourceAbstract = resourceAbstract;
		}
	}


	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
