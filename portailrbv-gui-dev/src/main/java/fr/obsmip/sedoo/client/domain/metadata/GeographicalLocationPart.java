package fr.obsmip.sedoo.client.domain.metadata;

import java.util.ArrayList;
import java.util.List;

import fr.obsmip.sedoo.client.domain.AbstractDTO;
import fr.obsmip.sedoo.client.domain.GeographicBoundingBoxDTO;
import fr.obsmip.sedoo.client.domain.ValidationAlert;
import fr.obsmip.sedoo.client.message.Message;

public class GeographicalLocationPart extends AbstractDTO{
	
	private GeographicBoundingBoxDTO box = new GeographicBoundingBoxDTO();
	
	@Override
	public String getHash() {
		StringBuffer aux = new StringBuffer();
		aux.append("@"+getBox().getHash());
		return aux.toString();
	}

	@Override
	public List<ValidationAlert> validate() {
		List<ValidationAlert> result = new ArrayList<ValidationAlert>();
		
		//TODO: controle sémantique
		
		return result;
	}

	public GeographicBoundingBoxDTO getBox() {
		return box;
	}

	public void setBox(GeographicBoundingBoxDTO box) {
		this.box = box;
	}
	
}
