package fr.obsmip.sedoo.client.domain;

import java.util.ArrayList;
import java.util.List;

public class ObservatoryDTO extends AbstractDTO implements HasId
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1091434570758933491L;
	
	private Long id;
	private String description;
	private String longLabel;
	private String ShortLabel;
	private List<DrainageBasinDTO> drainageBasinDTOs;
	
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
		return "@"+protectNullString(getLongLabel())+"|"+protectNullString(getShortLabel())+"|"+protectNullString(getDescription());
	}
	@Override
	public List<ValidationAlert> validate() {
		List<ValidationAlert> result = new ArrayList<ValidationAlert>();
		if ((getShortLabel() == null) || (getShortLabel().trim().length() ==0))
		{
			result.add(new ValidationAlert("Short label is mandatory"));
		}
		return result;
	}
	
	
}
