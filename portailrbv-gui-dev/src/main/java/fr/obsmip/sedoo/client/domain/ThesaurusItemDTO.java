package fr.obsmip.sedoo.client.domain;

import java.io.Serializable;

public class ThesaurusItemDTO implements Serializable
{
	
	private String id;
	private String label;
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public ThesaurusItemDTO()
	{
		
	}
	
	public ThesaurusItemDTO(String id, String label) 
	{
		this.id = id;
		this.label = label;
	}

}
