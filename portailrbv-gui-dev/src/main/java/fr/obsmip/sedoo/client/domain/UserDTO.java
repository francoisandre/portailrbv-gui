package fr.obsmip.sedoo.client.domain;

import java.io.Serializable;

public class UserDTO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5768249757776868410L;
	private String firstName;
	private String lastName;
	private String email;
	private boolean admin = false;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}
