package fr.obsmip.sedoo.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

import fr.obsmip.sedoo.client.domain.metadata.MetadataDTO;
import fr.obsmip.sedoo.client.ui.tabs.display.IdentificationTab;

public class MetadataDisplayViewImpl extends AbstractSection implements MetadataDisplayView {

	private static MetadataDisplayViewImplUiBinder uiBinder = GWT
			.create(MetadataDisplayViewImplUiBinder.class);
	
	private MetadataDTO metadata = null;
	
	
	private Presenter presenter; 
	
	@UiField
	IdentificationTab identificationTab;

	interface MetadataDisplayViewImplUiBinder extends UiBinder<Widget, MetadataDisplayViewImpl> {
	}
	
	
	
	public MetadataDisplayViewImpl() {
		super();
		initWidget(uiBinder.createAndBindUi(this));
		applyCommonStyle();
	}

	 


	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void display(MetadataDTO metadata) 
	{
		identificationTab.display(metadata);
		
		
	}


	@Override
	public void reset() {
		identificationTab.reset();
		
	}

	


}
