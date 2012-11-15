package fr.obsmip.sedoo.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import fr.obsmip.sedoo.client.domain.AbstractDTO;
import fr.obsmip.sedoo.client.domain.DrainageBasinDTO;
import fr.obsmip.sedoo.client.domain.GeographicBoundingBoxDTO;
import fr.obsmip.sedoo.client.domain.ObservatoryDTO;
import fr.obsmip.sedoo.client.domain.SiteDTO;
import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.ui.misc.MapSelector;
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
	
	@UiField
	Button saveButton;
	
	@UiField
	MapSelector mapSelector;
	
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
	public void edit(AbstractDTO dto) {
		reset();
//		siteTable.setPresenter(null);
//		siteTable.setObservatoryDTO(observatory);
		DrainageBasinDTO drainageBasinDTO = (DrainageBasinDTO) dto;
		siteTable.init(drainageBasinDTO.getSiteDTOs());
		label.setText(drainageBasinDTO.getLabel());
		mapSelector.setGeographicBoundingBoxDTO(drainageBasinDTO.getGeographicBoundingBoxDTO());
	}
	
	public void reset()
	{
		label.setText("");
		mapSelector.reset();
	}

	@Override
	public void setPresenter(Presenter presenter) 
	{
		this.presenter = presenter;
	}
	
	 @UiHandler("saveButton")
	 void onSaveButtonClicked(ClickEvent event) 
	 {
		presenter.save(flush());
	 }
	 
	 @Override
	 public DrainageBasinDTO flush() 
	 {
		 DrainageBasinDTO drainageBasinDTO = new DrainageBasinDTO(); 
		 drainageBasinDTO.setLabel(label.getText().trim());
		 drainageBasinDTO.setGeographicBoundingBoxDTO(mapSelector.getGeographicBoundingBoxDTO());
		 return drainageBasinDTO;
	 }

}
