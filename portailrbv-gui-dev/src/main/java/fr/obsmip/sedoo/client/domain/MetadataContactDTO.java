package fr.obsmip.sedoo.client.domain;

import java.util.List;

import fr.obsmip.sedoo.client.message.Message;

public class MetadataContactDTO extends PersonDTO
{
	public List<ValidationAlert> validate() {
		List<ValidationAlert> result = super.validate();
		
		if (protectNullString(getEmail()).trim().length()==0)
		{
			result.add(new ValidationAlert(Message.INSTANCE.personEmail(), Message.INSTANCE.mandatoryData()));
		}
		
		if (protectNullString(getOrganisationName()).trim().length()==0)
		{
			result.add(new ValidationAlert(Message.INSTANCE.personOrganisationName(), Message.INSTANCE.mandatoryData()));
		}
		
		return result;
	}
}
