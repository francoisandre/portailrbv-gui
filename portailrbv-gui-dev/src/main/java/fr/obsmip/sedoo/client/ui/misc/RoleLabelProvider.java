package fr.obsmip.sedoo.client.ui.misc;

import java.util.HashMap;

import fr.obsmip.sedoo.client.message.Message;

public class RoleLabelProvider {

	public static final String AUTHOR = "author";
	public static final String ORIGINATOR = "originator";
	public static final String DISTRIBUTOR = "distributor";
	public static final String OWNER = "owner";
	public static final String USER = "user";
	public static final String POINT_OF_CONTACT = "pointOfContact";
	public static final String RESOURCE_PROVIDER = "resourceProvider";
	public static final String CUSTODIAN = "custodian";
	public static final String PROCESSOR = "processor";
	public static final String PRINCIPAL_INVESTIGATOR = "principalInvestigator";
	public static final String PUBLISHER = "publisher";
	
	private static HashMap<String, String> labels = null; 
	
	public static String getLabel(String key) 
	{
		if (labels == null)
		{
			labels = new HashMap<String, String>();
			String aux = Message.INSTANCE.personRoleItems();
			String[] split = aux.split("@");
			for (int i = 0; i < split.length; i++) {
				String[] split2 = split[i].split("\\|");
				labels.put(split2[0], split2[1]);
			}
		}
		return labels.get(key);
	}

}
