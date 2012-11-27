package fr.obsmip.sedoo.client.domain.metadata;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.obsmip.sedoo.client.domain.AbstractDTO;
import fr.obsmip.sedoo.client.domain.MetadataContactDTO;
import fr.obsmip.sedoo.client.domain.PersonDTO;
import fr.obsmip.sedoo.client.domain.ValidationAlert;
import fr.obsmip.sedoo.client.message.Message;

public class MetadataPart extends AbstractDTO{

	private String metadataLastModificationDate;
	private String metadataLanguage;
	private List<MetadataContactDTO> metadataContacts = new ArrayList<MetadataContactDTO>();
	
	@Override
	public String getHash() {
		StringBuffer aux = new StringBuffer();
		aux.append("@"+metadataLanguage+"|"+metadataLastModificationDate+"|");
		Iterator<MetadataContactDTO> iterator = metadataContacts.iterator();
		while (iterator.hasNext()) {
			aux.append(iterator.next().getHash());
		}
		return aux.toString();
	}

	@Override
	public List<ValidationAlert> validate() {
		List<ValidationAlert> result = new ArrayList<ValidationAlert>();
		if (getMetadataContacts().isEmpty())
		{
			result.add(new ValidationAlert(Message.INSTANCE.metadataContactList(), Message.INSTANCE.atLeastOneElementNeeded()));
		}
		else
		{
			Iterator<MetadataContactDTO> iterator = getMetadataContacts().iterator();
			result.addAll(iterator.next().validate());
		}
		return result;
	}
	
	public String getMetadataLanguage() {
		return metadataLanguage;
	}
	public void setMetadataLanguage(String metadataLanguage) {
		this.metadataLanguage = metadataLanguage;
	}
	public String getMetadataLastModificationDate() {
		return metadataLastModificationDate;
	}
	public void setMetadataLastModificationDate(
			String metadataLastModificationDate) {
		this.metadataLastModificationDate = metadataLastModificationDate;
	}
	
	public List<MetadataContactDTO> getMetadataContacts() {
		return metadataContacts;
	}
	public void setMetadataContacts(List<MetadataContactDTO> metadataContacts) {
		this.metadataContacts = metadataContacts;
	}

}
