package fr.obsmip.sedoo.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.obsmip.sedoo.client.Constants;
import fr.obsmip.sedoo.client.domain.MetadataDTO;
import fr.obsmip.sedoo.client.service.ParameterService;
import fr.obsmip.sedoo.client.service.ParameterServiceAsync;
import fr.obsmip.sedoo.client.ui.misc.DialogBoxTools;

public class MetadataEditingViewImpl extends AbstractSection implements MetadataEditingView,  Editor<MetadataDTO> {

	private static MetadataEditingViewImplUiBinder uiBinder = GWT
			.create(MetadataEditingViewImplUiBinder.class);
	
	private final ParameterServiceAsync paramService = GWT
			.create(ParameterService.class);
	
	 //interface Driver extends SimpleBeanEditorDriver<MetadataDTO, MetadataEditingViewImpl> {}      
     //Driver driver = GWT.create(Driver.class);
     
	private MetadataDTO metadata = null;
	
	
	private Presenter presenter; 
	

	interface MetadataEditingViewImplUiBinder extends UiBinder<Widget, MetadataEditingViewImpl> {
	}
	
	
	
	@UiField 
	HorizontalPanel testPanel;
	@UiField 
	Button generateXMLButton;
	
	private String mode = null;

	public MetadataEditingViewImpl() {
		super();
		initWidget(uiBinder.createAndBindUi(this));
		applyCommonStyle();
		//driver.initialize(this);
		
		if (mode == null)
		{
		paramService.getParameter(Constants.MODE_PARAM, new AsyncCallback<String>() {
			
			@Override
			public void onSuccess(String result) {
				setMode(result);
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
			updateMode(mode);
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
		 presenter.generateXML(metadata);
		  
		 
	  }
	 
	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void edit(MetadataDTO metadata) 
	{
		this.metadata = metadata;
		//driver.edit(this.metadata);
	}

	@Override
	public void setGeneratedXML(String xml) 
	{
		DialogBoxTools.popUp("XML Généré",xml);
	}

	
	private void setMode(String mode) {
		this.mode = mode;
	}


}
