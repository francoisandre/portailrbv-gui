package fr.obsmip.sedoo.client.ui.tabs.edit;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import fr.obsmip.sedoo.client.ui.misc.RoleLabelProvider;

public class RolePanel extends Composite {

	@UiField
	CheckBox author;
	
	@UiField
	CheckBox originator;
	
	@UiField
	CheckBox distributor;
	
	@UiField
	CheckBox resourceProvider;
	
	@UiField
	CheckBox custodian;
	
	@UiField
	CheckBox processor;
	
	@UiField
	CheckBox principalInvestigator;
	
	@UiField
	CheckBox pointOfContact;
	
	@UiField
	CheckBox ownerX;
	
	@UiField
	CheckBox user;
	
	@UiField
	CheckBox publisher;
	
	
	
	private static RolePanelUiBinder uiBinder = GWT
			.create(RolePanelUiBinder.class);

	interface RolePanelUiBinder extends UiBinder<Widget, RolePanel> {
	}

	public RolePanel() {
		initWidget(uiBinder.createAndBindUi(this));
		author.setText(RoleLabelProvider.getLabel(RoleLabelProvider.AUTHOR));
		originator.setText(RoleLabelProvider.getLabel(RoleLabelProvider.ORIGINATOR));
		distributor.setText(RoleLabelProvider.getLabel(RoleLabelProvider.DISTRIBUTOR));
		resourceProvider.setText(RoleLabelProvider.getLabel(RoleLabelProvider.RESOURCE_PROVIDER));
		custodian.setText(RoleLabelProvider.getLabel(RoleLabelProvider.CUSTODIAN));
		processor.setText(RoleLabelProvider.getLabel(RoleLabelProvider.PROCESSOR));
		principalInvestigator.setText(RoleLabelProvider.getLabel(RoleLabelProvider.PRINCIPAL_INVESTIGATOR));
		pointOfContact.setText(RoleLabelProvider.getLabel(RoleLabelProvider.POINT_OF_CONTACT));
		ownerX.setText(RoleLabelProvider.getLabel(RoleLabelProvider.OWNER));
		user.setText(RoleLabelProvider.getLabel(RoleLabelProvider.USER));
		publisher.setText(RoleLabelProvider.getLabel(RoleLabelProvider.PUBLISHER));
	}
	
	public void edit(String roles)
	{
		reset();
		String aux = roles;
		if (aux == null)
		{
			aux = "";
		}
		author.setValue(aux.contains(RoleLabelProvider.AUTHOR));
		originator.setValue(aux.contains(RoleLabelProvider.ORIGINATOR));
		distributor.setValue(aux.contains(RoleLabelProvider.DISTRIBUTOR));
		resourceProvider.setValue(aux.contains(RoleLabelProvider.RESOURCE_PROVIDER));
		custodian.setValue(aux.contains(RoleLabelProvider.CUSTODIAN));
		processor.setValue(aux.contains(RoleLabelProvider.PROCESSOR));
		principalInvestigator.setValue(aux.contains(RoleLabelProvider.PRINCIPAL_INVESTIGATOR));
		pointOfContact.setValue(aux.contains(RoleLabelProvider.POINT_OF_CONTACT));
		ownerX.setValue(aux.contains(RoleLabelProvider.OWNER));
		user.setValue(aux.contains(RoleLabelProvider.USER));
		publisher.setValue(aux.contains(RoleLabelProvider.PUBLISHER));
	}
	
	public String flush()
	{
		StringBuilder sb = new StringBuilder();
		if (author.getValue()== true)
		{
			sb.append("|"+RoleLabelProvider.AUTHOR);
		}
		if (originator.getValue()== true)
		{
			sb.append("|"+RoleLabelProvider.ORIGINATOR);
		}
		if (distributor.getValue()== true)
		{
			sb.append("|"+RoleLabelProvider.DISTRIBUTOR);
		}
		if (resourceProvider.getValue()== true)
		{
			sb.append("|"+RoleLabelProvider.RESOURCE_PROVIDER);
		}
		if (custodian.getValue()== true)
		{
			sb.append("|"+RoleLabelProvider.CUSTODIAN);
		}
		if (processor.getValue()== true)
		{
			sb.append("|"+RoleLabelProvider.PROCESSOR);
		}
		if (principalInvestigator.getValue()== true)
		{
			sb.append("|"+RoleLabelProvider.PRINCIPAL_INVESTIGATOR);
		}
		if (pointOfContact.getValue()== true)
		{
			sb.append("|"+RoleLabelProvider.POINT_OF_CONTACT);
		}
		if (ownerX.getValue()== true)
		{
			sb.append("|"+RoleLabelProvider.OWNER);
		}
		if (user.getValue()== true)
		{
			sb.append("|"+RoleLabelProvider.USER);
		}
		if (publisher.getValue()== true)
		{
			sb.append("|"+RoleLabelProvider.PUBLISHER);
		}
		String aux = sb.toString();
		if (aux.length()>0)
		{
			//On enlève le premier |
			return aux.substring(1);
		}
		else
		{
			//On retourne en fait une chaine vide
			return aux;
		}
	}

	private void reset() {
		//	Pas de traitement particulier
	}

}
