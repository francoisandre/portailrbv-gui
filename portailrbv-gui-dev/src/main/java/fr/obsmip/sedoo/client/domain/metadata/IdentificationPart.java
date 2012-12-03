package fr.obsmip.sedoo.client.domain.metadata;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.obsmip.sedoo.client.domain.AbstractDTO;
import fr.obsmip.sedoo.client.domain.IdentifiedString;
import fr.obsmip.sedoo.client.domain.MetadataContactDTO;
import fr.obsmip.sedoo.client.domain.ValidationAlert;
import fr.obsmip.sedoo.client.message.Message;

public class IdentificationPart extends AbstractDTO{

	private String resourceTitle;
	private String resourceAbstract;
	private List<MetadataContactDTO> resourceContacts = new ArrayList<MetadataContactDTO>();
	private List<IdentifiedString> resourceURL = new ArrayList<IdentifiedString>();
	private List<IdentifiedString> resourceIdentifiers = new ArrayList<IdentifiedString>();
	
	
	@Override
	public String getHash() {
		StringBuffer aux = new StringBuffer();
		aux.append("@"+getResourceAbstract()+"|"+getResourceTitle()+"|");
		Iterator<MetadataContactDTO> contactIterator = getResourceContacts().iterator();
		while (contactIterator.hasNext()) {
			aux.append(contactIterator.next().getHash());
		}
		
		Iterator<IdentifiedString> urlIterator = getResourceURL().iterator();
		while (urlIterator.hasNext()) {
			aux.append(urlIterator.next().getValue()+"|");
		}
		return aux.toString();
	}

	@Override
	public List<ValidationAlert> validate() {
		List<ValidationAlert> result = new ArrayList<ValidationAlert>();
		if(isEmpty(getResourceTitle()))
		{
			result.add(new ValidationAlert(Message.INSTANCE.metadataEditingResourceTitle(), Message.INSTANCE.mandatoryData()));
			
		}
		if(isEmpty(getResourceAbstract()))
		{
			result.add(new ValidationAlert(Message.INSTANCE.metadataEditingResourceAbstract(), Message.INSTANCE.mandatoryData()));
			
		}
		if (getResourceContacts().isEmpty())
		{
			result.add(new ValidationAlert(Message.INSTANCE.resourceContactList(), Message.INSTANCE.atLeastOneElementNeeded()));
		}
		else
		{
			Iterator<MetadataContactDTO> iterator = getResourceContacts().iterator();
			result.addAll(iterator.next().validate());
		}
		
		if (getResourceURL().isEmpty())
		{
			result.add(new ValidationAlert(Message.INSTANCE.metadataEditingResourceURL(), Message.INSTANCE.atLeastOneElementNeeded()));
		}
		
		if (getResourceIdentifiers().isEmpty())
		{
			result.add(new ValidationAlert(Message.INSTANCE.metadataEditingResourceIdentifier(), Message.INSTANCE.atLeastOneElementNeeded()));
		}
		
		return result;
	}

	public String getResourceTitle() {
		return resourceTitle;
	}

	public void setResourceTitle(String resourceTitle) {
		this.resourceTitle = resourceTitle;
	}

	public String getResourceAbstract() {
		return resourceAbstract;
	}

	public void setResourceAbstract(String resourceAbstract) {
		this.resourceAbstract = resourceAbstract;
	}

	public List<MetadataContactDTO> getResourceContacts() {
		return resourceContacts;
	}

	public void setResourceContacts(List<MetadataContactDTO> resourceContacts) {
		this.resourceContacts = resourceContacts;
	}
	
	
	public List<IdentifiedString> getResourceURL() {
		return resourceURL;
	}
	public void setResourceURL(List<IdentifiedString> resourceURL) {
		this.resourceURL = resourceURL;
	}

	public List<IdentifiedString> getResourceIdentifiers() {
		return resourceIdentifiers;
	}

	public void setResourceIdentifiers(List<IdentifiedString> resourceIdentifiers) {
		this.resourceIdentifiers = resourceIdentifiers;
	}
	
	

}
