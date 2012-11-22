package fr.obsmip.sedoo.client.domain;

import java.util.Iterator;
import java.util.List;

public class ValidationAlert {

	private String message;
	private String field;

	public ValidationAlert(String field, String message) {
		this.message = message;
		setField(field);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		String aux = field.replace(':',' ');
		this.field = aux.trim();
	}

	public static String toHTML(List<ValidationAlert> result) {
		StringBuilder sb = new StringBuilder();
		Iterator<ValidationAlert> iterator = result.iterator();
		while (iterator.hasNext()) {
			ValidationAlert validationAlert = iterator.next();
			sb.append("<b>"+validationAlert.getField()+"</b> : "+validationAlert.getMessage()+"<br>");
		}
		return sb.toString();
	}
}
