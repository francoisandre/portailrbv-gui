package fr.obsmip.sedoo.client.place;

import com.google.gwt.place.shared.PlaceTokenizer;

import fr.obsmip.sedoo.client.Constants;
import fr.obsmip.sedoo.client.domain.AbstractDTO;

public class MetadataEditingPlace extends AbstractEditingPlace
{
	
	public MetadataEditingPlace() {
		super();
	}
	
	public Long getDrainageBasinId() {
		return drainageBasinId;
	}

	public void setDrainageBasinId(Long drainageBasinId) {
		this.drainageBasinId = drainageBasinId;
	}

	public String getMetadataUuid() {
		return metadataUuid;
	}

	public void setMetadataUuid(String metadataUuid) {
		this.metadataUuid = metadataUuid;
	}

	private Long drainageBasinId;
	private String metadataUuid;
	
	
	public static MetadataEditingPlace instance;

	public static class Tokenizer implements PlaceTokenizer<MetadataEditingPlace>
	{
		@Override
		public String getToken(MetadataEditingPlace place)
		{
			if (place.getMode().compareTo(Constants.CREATE)==0)
			{
				return place.getMode()+"@"+AbstractDTO.protectNullString(""+place.getDrainageBasinId());
			}
			else
			{
				return place.getMode()+"@"+AbstractDTO.protectNullString(""+place.getDrainageBasinId())+"@"+AbstractDTO.protectNullString(""+place.getMetadataUuid());
			}
			
		}

		@Override
		public MetadataEditingPlace getPlace(String token)
		{
			if (instance == null)
			{
				instance = new MetadataEditingPlace();
			}
			String[] split = token.split("@");
			if (split.length>=2)
			{
				instance.setMode(split[0]);
				instance.setDrainageBasinId(new Long(split[1]));
			}
			if (split.length==3)
			{
				instance.setMetadataUuid(split[2]);
			}
			return instance;
		}
	}

	
	
}
