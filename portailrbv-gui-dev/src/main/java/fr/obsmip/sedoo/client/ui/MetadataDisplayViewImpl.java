package fr.obsmip.sedoo.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

import fr.obsmip.sedoo.client.domain.metadata.MetadataDTO;
import fr.obsmip.sedoo.client.ui.tabs.edit.AbstractTab;
import fr.obsmip.sedoo.client.ui.tabs.edit.ConstraintTab;
import fr.obsmip.sedoo.client.ui.tabs.edit.GeographicalLocationTab;
import fr.obsmip.sedoo.client.ui.tabs.edit.IdentificationTab;
import fr.obsmip.sedoo.client.ui.tabs.edit.TemporalExtentTab;

public class MetadataDisplayViewImpl extends AbstractSection implements MetadataDisplayView {

	private static MetadataDisplayViewImplUiBinder uiBinder = GWT
			.create(MetadataDisplayViewImplUiBinder.class);
	
	
	@UiField
	IdentificationTab identificationTab;
	
	@UiField
	ConstraintTab constraintTab;
	
	@UiField
	GeographicalLocationTab geographicalLocationTab;
	
	@UiField
	TemporalExtentTab temporalExtentTab;
	
	private List<AbstractTab> tabs = new ArrayList<AbstractTab>();

	interface MetadataDisplayViewImplUiBinder extends UiBinder<Widget, MetadataDisplayViewImpl> {
	}
	
	
	
	public MetadataDisplayViewImpl() {
		super();
		initWidget(uiBinder.createAndBindUi(this));
		applyCommonStyle();
		tabs.add(identificationTab);
		tabs.add(constraintTab);
		tabs.add(geographicalLocationTab);
		tabs.add(temporalExtentTab);
	}

	@Override
	public void display(MetadataDTO metadata) 
	{
		for (int i =0; i< tabs.size(); i++)
		{
			tabs.get(i).display(metadata);
		}
	}

	@Override
	public void reset() {
		for (int i =0; i< tabs.size(); i++)
		{
			tabs.get(i).reset();
		}
	}


}
