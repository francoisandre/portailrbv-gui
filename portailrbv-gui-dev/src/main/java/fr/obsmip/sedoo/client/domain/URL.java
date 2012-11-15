package fr.obsmip.sedoo.client.domain;

import java.io.Serializable;

public class URL implements Serializable{
	
	public static final String DEFAULT_VALUE = "Indiquez une URL";
	
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
