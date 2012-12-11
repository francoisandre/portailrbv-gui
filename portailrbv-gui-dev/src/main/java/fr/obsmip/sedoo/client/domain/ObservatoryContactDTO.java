package fr.obsmip.sedoo.client.domain;

import java.util.List;

import fr.obsmip.sedoo.client.message.Message;

public class ObservatoryContactDTO extends PersonDTO{
	
	@Override
	public List<ValidationAlert> validate() 
	{
		List<ValidationAlert> alerts =super.validate();
		if (protectNullString(getOrganisationName()).trim().length()==0)
		{
			alerts.add(new ValidationAlert(Message.INSTANCE.personOrganisationName(), Message.INSTANCE.mandatoryData()));
		}
		
		if (protectNullString(getEmail()).trim().length()==0)
		{
			alerts.add(new ValidationAlert(Message.INSTANCE.personEmail(), Message.INSTANCE.mandatoryData()));
		}
		
		return alerts;
	}
	
	public Long getObservatoryId() {
		return observatoryId;
	}

	public void setObservatoryId(Long observatoryId) {
		this.observatoryId = observatoryId;
	}

	public String getObservatoryShortLabel() {
		return observatoryShortLabel;
	}

	public void setObservatoryShortLabel(String observatoryShortLabel) {
		this.observatoryShortLabel = observatoryShortLabel;
	}

	private Long observatoryId;
	private String observatoryShortLabel;

}
