package fr.obsmip.sedoo.server.service.dtotool;


import fr.obsmip.sedoo.client.domain.ObservatoryContactDTO;
import fr.obsmip.sedoo.client.domain.PersonDTO;
import fr.obsmip.sedoo.core.domain.ObservatoryContact;
import fr.obsmip.sedoo.core.domain.Person;

public class PersonDTOTools {
	
	public static PersonDTO toPersonDTO(Person person)
	{
		return toPersonDTO(person, null);
	}
	
	public static PersonDTO toPersonDTO(Person person, PersonDTO target) 
	{
		PersonDTO dto = null;
		if (target == null)
		{
			dto = new PersonDTO();
		}
		else
		{
			dto = target;
		}
		dto.setId(person.getId());
		dto.setEmail(person.getEmail());
		dto.setOrganisationName(person.getOrganisationName());
		dto.setPersonName(person.getPersonName());
		dto.setRoles(person.getRoles());
		return dto;
	}

	public static Person fromDTO(PersonDTO dto,
			Person target) {
		
		Person result = null;
		if (target == null)
		{
			result = new Person();
		}
		else
		{
			result = target;
		}
		result.setEmail(dto.getEmail());
		result.setOrganisationName(dto.getOrganisationName());
		result.setPersonName(dto.getPersonName());
		result.setRoles(dto.getRoles());
		return result;
	}

}
