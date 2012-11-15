package fr.obsmip.sedoo.client.domain;

public class ValidationAlert {

	private String message;

	public ValidationAlert(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
