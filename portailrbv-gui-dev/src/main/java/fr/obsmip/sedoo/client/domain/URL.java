package fr.obsmip.sedoo.client.domain;

import java.io.Serializable;

import fr.obsmip.sedoo.client.message.Message;

public class URL implements Serializable, HasId{
	
	public static final String DEFAULT_VALUE = Message.INSTANCE.metadataEditingURLDefaultValue();
	
	private Long id;
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}