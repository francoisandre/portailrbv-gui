package fr.obsmip.sedoo.client.domain;

import java.io.Serializable;
import java.util.List;

public class DrainageBasinDTO implements Serializable, HasId
{
	private Long id;
	private String label;
	private List<SiteDTO> siteDTOs;
	private GeographicBoundingBoxDTO geographicBoundingBoxDTO;
	
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
		// TODO Auto-generated method stub
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
}
