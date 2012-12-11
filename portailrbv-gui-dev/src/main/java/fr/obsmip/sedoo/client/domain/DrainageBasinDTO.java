package fr.obsmip.sedoo.client.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.obsmip.sedoo.client.message.Message;

public class DrainageBasinDTO extends AbstractDTO implements HasId
{
	private Long id;
	private String label;
	private List<SiteDTO> siteDTOs = new ArrayList<SiteDTO>();
	private GeographicBoundingBoxDTO geographicBoundingBoxDTO = new GeographicBoundingBoxDTO();
	private Long observatoryId;
	private String observatoryShortLabel;
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<SiteDTO> getSiteDTOs() {
		return siteDTOs;
	}
	public void setSiteDTOs(List<SiteDTO> siteDTOs) {
		this.siteDTOs = siteDTOs;
	}
	public GeographicBoundingBoxDTO getGeographicBoundingBoxDTO() {
		return geographicBoundingBoxDTO;
	}
	public void setGeographicBoundingBoxDTO(GeographicBoundingBoxDTO geographicBoundingBoxDTO) {
		this.geographicBoundingBoxDTO = geographicBoundingBoxDTO;
	}
	public String getHash() {
		StringBuilder sb =  new StringBuilder("@"+protectNullString(getLabel())+"|"+getGeographicBoundingBoxDTO().getHash());
		Iterator<SiteDTO> iterator = siteDTOs.iterator();
		while (iterator.hasNext()) {
			sb.append(iterator.next().getHash()+"|");
		}
		return sb.toString();
	}
	@Override
	public List<ValidationAlert> validate() {
		List<ValidationAlert> result = new ArrayList<ValidationAlert>();
		if ((getLabel() == null) || (getLabel().trim().length() ==0))
		{
			result.add(new ValidationAlert(Message.INSTANCE.label(), Message.INSTANCE.mandatoryData()));
		}
		result.addAll(geographicBoundingBoxDTO.validate());
		
		Iterator<SiteDTO> iterator = siteDTOs.iterator();
		while (iterator.hasNext()) {
			result.addAll(iterator.next().validate());
		}
		
		return result;
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
	
}
