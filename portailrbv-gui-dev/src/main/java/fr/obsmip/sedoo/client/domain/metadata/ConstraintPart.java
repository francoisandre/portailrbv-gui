package fr.obsmip.sedoo.client.domain.metadata;

import java.util.ArrayList;
import java.util.List;

import fr.obsmip.sedoo.client.domain.AbstractDTO;
import fr.obsmip.sedoo.client.domain.ValidationAlert;
import fr.obsmip.sedoo.client.message.Message;

public class ConstraintPart extends AbstractDTO{
	
	private String useConditions;
	private String publicAccessLimitations;
	
	@Override
	public String getHash() {
		StringBuffer aux = new StringBuffer();
		aux.append("@"+AbstractDTO.protectNullString(useConditions)+"|"+AbstractDTO.protectNullString(publicAccessLimitations)+"|");
		return aux.toString();
	}

	@Override
	public List<ValidationAlert> validate() {
		List<ValidationAlert> result = new ArrayList<ValidationAlert>();
		if(isEmpty(useConditions))
		{
			result.add(new ValidationAlert(Message.INSTANCE.metadataEditingUseConditions(), Message.INSTANCE.mandatoryData()));
			
		}
		if(isEmpty(publicAccessLimitations))
		{
			result.add(new ValidationAlert(Message.INSTANCE.metadataEditingPublicAccessLimitations(), Message.INSTANCE.mandatoryData()));
			
		}
		return result;
	}

	public String getUseConditions() {
		return useConditions;
	}

	public void setUseConditions(String useConditions) {
		this.useConditions = useConditions;
	}

	public String getPublicAccessLimitations() {
		return publicAccessLimitations;
	}

	public void setPublicAccessLimitations(String publicAccessLimitations) {
		this.publicAccessLimitations = publicAccessLimitations;
	}
	
}
