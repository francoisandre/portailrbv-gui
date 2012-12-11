package fr.obsmip.sedoo.client.domain;

import java.util.ArrayList;
import java.util.List;

import fr.obsmip.sedoo.client.message.Message;

public class ObservatoryDTO extends AbstractDTO implements HasId
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1091434570758933491L;
	
	private Long id;
	private String description;
	private String useConditions;
	private String publicAccessLimitations;
	private String longLabel;
	private String ShortLabel;
	private List<DrainageBasinDTO> drainageBasinDTOs;
	private List<ObservatoryContactDTO> contactDTOs;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLongLabel() {
		return longLabel;
	}
	public void setLongLabel(String longLabel) {
		this.longLabel = longLabel;
	}
	public String getShortLabel() {
		return ShortLabel;
	}
	public void setShortLabel(String shortLabel) {
		ShortLabel = shortLabel;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public List<DrainageBasinDTO> getDrainageBasinDTOs() {
		return drainageBasinDTOs;
	}
	
	public void setDrainageBasinDTOs(List<DrainageBasinDTO> drainageBasinDTOs) {
		this.drainageBasinDTOs = drainageBasinDTOs;
	}
	public String getHash() 
	{
		return "@"+protectNullString(getLongLabel())+"|"+protectNullString(getShortLabel())+"|"+protectNullString(getDescription())+"|"+protectNullString(getPublicAccessLimitations())+"|"+protectNullString(getUseConditions());
	}
	@Override
	public List<ValidationAlert> validate() {
		List<ValidationAlert> result = new ArrayList<ValidationAlert>();
		if ((getShortLabel() == null) || (getShortLabel().trim().length() ==0))
		{
			result.add(new ValidationAlert(Message.INSTANCE.observatoryShortLabel(), Message.INSTANCE.mandatoryData()));
		}
		return result;
	}
	public List<ObservatoryContactDTO> getContactDTOs() {
		return contactDTOs;
	}
	public void setContactDTOs(List<ObservatoryContactDTO> contactDTOs) {
		this.contactDTOs = contactDTOs;
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
