package fr.obsmip.sedoo.client.domain.metadata;

import java.util.ArrayList;
import java.util.List;

import fr.obsmip.sedoo.client.domain.AbstractDTO;
import fr.obsmip.sedoo.client.domain.ValidationAlert;


public class MetadataDTO extends AbstractDTO
{
		
	private MetadataPart metadataPart = new MetadataPart();
	private IdentificationPart identificationPart = new IdentificationPart();
	private ConstraintPart constraintPart = new ConstraintPart();
	private TemporalExtentPart temporalExtentPart = new TemporalExtentPart();
	private GeographicalLocationPart geographicalLocationPart = new GeographicalLocationPart();
	private String uuid;
	
	
	@Override
	public String getHash() {
		return "@"+getIdentificationPart()+"|"+getMetadataPart().getHash()+"|"+getConstraintPart().getHash()+"|"+getTemporalExtentPart().getHash()+"|"+getGeographicalLocationPart().getHash();
	}
	@Override
	public List<ValidationAlert> validate() {
		List<ValidationAlert> result = new ArrayList<ValidationAlert>();
		result.addAll(getIdentificationPart().validate());
		result.addAll(getMetadataPart().validate());
		result.addAll(getTemporalExtentPart().validate());
		result.addAll(getConstraintPart().validate());
		result.addAll(getGeographicalLocationPart().validate());
		return result;
	}
	
	public MetadataPart getMetadataPart() {
		return metadataPart;
	}
	public void setMetadataPart(MetadataPart part) 
	{
		this.metadataPart = part;
	}
	public IdentificationPart getIdentificationPart() {
		return identificationPart;
	}
	public void setIdentificationPart(IdentificationPart identificationPart) {
		this.identificationPart = identificationPart;
	}
	public ConstraintPart getConstraintPart() {
		return constraintPart;
	}
	public void setConstraintPart(ConstraintPart constraintPart) {
		this.constraintPart = constraintPart;
	}
	public TemporalExtentPart getTemporalExtentPart() {
		return temporalExtentPart;
	}
	public void setTemporalExtentPart(TemporalExtentPart temporalExtentPart) {
		this.temporalExtentPart = temporalExtentPart;
	}
	public GeographicalLocationPart getGeographicalLocationPart() {
		return geographicalLocationPart;
	}
	public void setGeographicalLocationPart(GeographicalLocationPart geographicalLocationPart) {
		this.geographicalLocationPart = geographicalLocationPart;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
}

