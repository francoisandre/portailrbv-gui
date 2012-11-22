package fr.obsmip.sedoo.client.domain;

import java.util.ArrayList;
import java.util.List;

import fr.obsmip.sedoo.client.message.Message;

public class PersonDTO extends AbstractDTO implements HasId{
	
	private Long id;
	private String organisationName;
	private String personName;
	private String email;
	private String roles;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrganisationName() {
		return organisationName;
	}
	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	@Override
	public String getHash() {
		return "@"+protectNullString(getPersonName())+"|"+protectNullString(getRoles())+"|"+protectNullString(getOrganisationName())+"|"+protectNullString(getEmail());
	}
	@Override
	public List<ValidationAlert> validate() {
		List<ValidationAlert> result = new ArrayList<ValidationAlert>();
		String aux = protectNullString(getEmail());
		String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(?:[a-zA-Z]{2,6})$";
		if (aux.matches(emailPattern) == false)
		{
			result.add(new ValidationAlert(Message.INSTANCE.personEmail(), Message.INSTANCE.emailData()));
		}
		return result;
	}

}
