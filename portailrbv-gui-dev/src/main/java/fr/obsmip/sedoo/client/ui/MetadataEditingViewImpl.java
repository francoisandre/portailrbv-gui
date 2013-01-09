package fr.obsmip.sedoo.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.obsmip.sedoo.client.Constants;
import fr.obsmip.sedoo.client.PortailRBV;
import fr.obsmip.sedoo.client.domain.AbstractDTO;
import fr.obsmip.sedoo.client.domain.metadata.MetadataDTO;
import fr.obsmip.sedoo.client.event.NotificationEvent;
import fr.obsmip.sedoo.client.service.ParameterService;
import fr.obsmip.sedoo.client.service.ParameterServiceAsync;
import fr.obsmip.sedoo.client.ui.misc.DialogBoxTools;
import fr.obsmip.sedoo.client.ui.tabs.edit.ConstraintTab;
import fr.obsmip.sedoo.client.ui.tabs.edit.GeographicalLocationTab;
import fr.obsmip.sedoo.client.ui.tabs.edit.IdentificationTab;
import fr.obsmip.sedoo.client.ui.tabs.edit.MetadataTab;
import fr.obsmip.sedoo.client.ui.tabs.edit.TemporalExtentTab;

public class MetadataEditingViewImpl extends AbstractDTOEditingView implements MetadataEditingView,  Editor<MetadataDTO> {

	private static MetadataEditingViewImplUiBinder uiBinder = GWT
			.create(MetadataEditingViewImplUiBinder.class);
	
	private final ParameterServiceAsync paramService = GWT
			.create(ParameterService.class);
	
	 //interface Driver extends SimpleBeanEditorDriver<MetadataDTO, MetadataEditingViewImpl> {}      
     //Driver driver = GWT.create(Driver.class);
	
	
	private Presenter presenter; 
	
	@UiField
	MetadataTab metadataTab;
	
	@UiField
	IdentificationTab identificationTab;
	
	@UiField
	ConstraintTab constraintTab;
	
	@UiField
	GeographicalLocationTab geographicalLocationTab;
	
	@UiField
	TemporalExtentTab temporalExtentTab;
	

	interface MetadataEditingViewImplUiBinder extends UiBinder<Widget, MetadataEditingViewImpl> {
	}
	
	@UiField 
	HorizontalPanel testPanel;
	@UiField 
	Button generateXMLButton;

	@UiField 
	Button saveButton;
	
	@UiField 
	Button notificationMoleButton;
	
	private String executionMode = null;

	public MetadataEditingViewImpl() {
		super();
		initWidget(uiBinder.createAndBindUi(this));
		applyCommonStyle();
		//driver.initialize(this);
		
		if (executionMode == null)
		{
		paramService.getParameter(Constants.MODE_PARAM, new AsyncCallback<String>() {
			
			@Override
			public void onSuccess(String result) {
				setExecutionMode(result);
				updateMode(result);
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
		}
		else
		{
			updateMode(executionMode);
		}
		
	}

	 private void updateMode(String mode) 
	 {
		  if (mode.compareTo(Constants.DEBUG_MODE)==0)
			{
				if (testPanel != null)
				{
					testPanel.setVisible(true);
				}
			}
	 }

	@UiHandler("generateXMLButton")
	  void onGenerateXMLButtonClicked(ClickEvent event) {
		 
		 //driver.flush();
		 presenter.generateXML(flush());
		  
		 
	  }
	
	@UiHandler("notificationMoleButton")
	  void onNotificationMoleButtonClicked(ClickEvent event) {
		 
		 //driver.flush();
		 EventBus eventBus = PortailRBV.getClientFactory().getEventBus();
		 eventBus.fireEvent(new NotificationEvent("Coucou Coucou Coucou Coucou"));
		 
	  }
	
	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
		identificationTab.setPresenter(presenter);
		metadataTab.setPresenter(presenter);
	}

	@Override
	public void setGeneratedXML(String xml) 
	{
		DialogBoxTools.popUp("XML Généré",xml, DialogBoxTools.TEXT_MODE);
	}

	
	private void setExecutionMode(String mode) {
		this.executionMode = mode;
	}

	@Override
	public MetadataDTO flush() {
		MetadataDTO metadataDTO = new MetadataDTO();
		metadataTab.flush(metadataDTO);
		identificationTab.flush(metadataDTO);
		constraintTab.flush(metadataDTO);
		temporalExtentTab.flush(metadataDTO);
		geographicalLocationTab.flush(metadataDTO);
		return metadataDTO;
	}

	@Override
	public void edit(AbstractDTO dto) {
		MetadataDTO metadata = (MetadataDTO) dto;
		metadataTab.edit(metadata);
		identificationTab.edit(metadata);
		constraintTab.edit(metadata);
		temporalExtentTab.edit(metadata);
		geographicalLocationTab.edit(metadata);
	}


}
