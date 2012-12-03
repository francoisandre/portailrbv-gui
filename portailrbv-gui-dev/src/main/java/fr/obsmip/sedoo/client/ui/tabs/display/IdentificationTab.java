package fr.obsmip.sedoo.client.ui.tabs.display;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

import fr.obsmip.sedoo.client.domain.metadata.MetadataDTO;
import fr.obsmip.sedoo.client.ui.tabs.AbstractTab;

public class IdentificationTab extends AbstractTab {

	private static IdentificationTabUiBinder uiBinder = GWT
			.create(IdentificationTabUiBinder.class);

	interface IdentificationTabUiBinder extends
			UiBinder<Widget, IdentificationTab> {
	}
	
	@UiField
	SpanElement resourceTitle;
	
	@UiField 
	SpanElement resourceAbstract;
	
	public IdentificationTab() {
		initWidget(uiBinder.createAndBindUi(this));
		reset();
	}


	public void reset() {
		
		resourceTitle.setInnerText("");
		resourceAbstract.setInnerText("");
	}
	



	public void display(MetadataDTO metadata) 
	{
		//reset();
		resourceTitle.setInnerText(metadata.getIdentificationPart().getResourceTitle());
		resourceAbstract.setInnerText(metadata.getIdentificationPart().getResourceAbstract());
		
	}
	
	
	
	
}
