package fr.obsmip.sedoo.client.ui;

import java.util.Iterator;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import fr.obsmip.sedoo.client.Constants;
import fr.obsmip.sedoo.client.domain.AbstractDTO;
import fr.obsmip.sedoo.client.domain.DrainageBasinDTO;
import fr.obsmip.sedoo.client.domain.ThesaurusItemDTO;
import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.service.ThesaurusService;
import fr.obsmip.sedoo.client.service.ThesaurusServiceAsync;
import fr.obsmip.sedoo.client.ui.misc.DialogBoxTools;
import fr.obsmip.sedoo.client.ui.misc.MapSelector;
import fr.obsmip.sedoo.client.ui.table.SiteTable;

public class DrainageBasinEditingViewImpl extends AbstractDTOEditingView implements DrainageBasinEditingView {

	
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
		
		climateList.addItem(Message.INSTANCE.loading());
		lithologyList.addItem(Message.INSTANCE.loading());
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
		 mapSelector.forcedHide();
		 DialogBoxTools.modalAlert("coucou", "tutu");
		 mapSelector.forcedShow();
	 }
	 
	 @Override
	 public DrainageBasinDTO flush() 
	 {
		 DrainageBasinDTO drainageBasinDTO = new DrainageBasinDTO(); 
		 drainageBasinDTO.setLabel(label.getText().trim());
		 drainageBasinDTO.setGeographicBoundingBoxDTO(mapSelector.getGeographicBoundingBoxDTO());
		 return drainageBasinDTO;
	 }

	 public void setMode(String mode) {
			super.setMode(mode);
			if (mode.compareTo(Constants.CREATE)==0)
			{
				siteTable.setAddButtonEnabled(false);
			}
			else
			{
				siteTable.setAddButtonEnabled(true);
			}
		} 
	 
	 @Override
		public void broadcastSiteDeletion(Long id, boolean success) {
			if (success)
			{
				siteTable.removeRow(id);
			}
			else
			{
				DialogBoxTools.modalAlert("A problem has appeared while deleting the drainage basin",
		                "A problem has appeared while deleting the drainage basin.");
			}	
	 }

	@Override
	public void updateClimateList(List<ThesaurusItemDTO> items) {
		climateList.clear();
		Iterator<ThesaurusItemDTO> iterator = items.iterator();
		while (iterator.hasNext()) 
		{
			ThesaurusItemDTO thesaurusItemDTO = iterator.next();
			climateList.addItem(thesaurusItemDTO.getLabel(), thesaurusItemDTO.getId());
		}
	}

	@Override
	public void updateLithologyList(List<ThesaurusItemDTO> items) {
		lithologyList.clear();
		Iterator<ThesaurusItemDTO> iterator = items.iterator();
		while (iterator.hasNext()) 
		{
			ThesaurusItemDTO thesaurusItemDTO = iterator.next();
			lithologyList.addItem(thesaurusItemDTO.getLabel(), thesaurusItemDTO.getId());
		}		
	}
}
