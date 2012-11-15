package fr.obsmip.sedoo.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import fr.obsmip.sedoo.client.domain.DrainageBasinDTO;
import fr.obsmip.sedoo.client.domain.SiteDTO;
import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.ui.table.SiteTable;

public class DrainageBasinEditingViewImpl extends AbstractSection implements DrainageBasinEditingView {

	
	private static  DrainageBasinEditingViewImplUiBinder uiBinder = GWT
			.create( DrainageBasinEditingViewImplUiBinder.class);

	interface DrainageBasinEditingViewImplUiBinder extends UiBinder<Widget, DrainageBasinEditingViewImpl> {
	}
	
	@UiField
	TextBox label;

	@UiField
	ListBox climateList;
	
	@UiField
	ListBox lithologyList;
	
	@UiField
	SiteTable siteTable;
	
	private DrainageBasinDTO drainageBasinDTO;
	
	private Presenter presenter;
	
	
	
	
	public DrainageBasinEditingViewImpl() {
		super();
		initWidget(uiBinder.createAndBindUi(this));
		applyCommonStyle();
		init();
	}

	private void init() {
		String climateItems = Message.INSTANCE.climateItems();
		String[] climates = climateItems.split(",");
		climateList.addItem("");
		for (int i = 0; i < climates.length; i++) {
			climateList.addItem(climates[i].trim());
		}
		
		String lithologyItems = Message.INSTANCE.lithologyItems();
		String[] lithologies = lithologyItems.split(",");
		lithologyList.addItem("");
		for (int i = 0; i < lithologies.length; i++) {
			lithologyList.addItem(lithologies[i].trim());
		}
	}
	
	@Override
	public void edit(DrainageBasinDTO drainageBasinDTO) {
		this.drainageBasinDTO = drainageBasinDTO;
		reset();
//		siteTable.setPresenter(null);
//		siteTable.setObservatoryDTO(observatory);
		siteTable.init(drainageBasinDTO.getSiteDTOs());
		label.setText(drainageBasinDTO.getLabel());
	}
	
	public void reset()
	{
		label.setText("");
	}

	@Override
	public void setPresenter(Presenter presenter) 
	{
		this.presenter = presenter;
	}

}
