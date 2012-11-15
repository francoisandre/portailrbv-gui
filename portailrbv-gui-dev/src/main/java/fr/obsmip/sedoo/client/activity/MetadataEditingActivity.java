package fr.obsmip.sedoo.client.activity;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.obsmip.sedoo.client.ClientFactory;
import fr.obsmip.sedoo.client.domain.MetadataDTO;
import fr.obsmip.sedoo.client.place.MetadataEditingPlace;
import fr.obsmip.sedoo.client.service.MetadataService;
import fr.obsmip.sedoo.client.service.MetadataServiceAsync;
import fr.obsmip.sedoo.client.ui.MetadataEditingView;

public class MetadataEditingActivity extends RBVAbstractActivity implements MetadataEditingView.Presenter {
	
	MetadataEditingView metadataEditingView = null;
	
	private final MetadataServiceAsync metadataService = GWT.create(MetadataService.class);

	private String metadataId;
	
	public MetadataEditingActivity(MetadataEditingPlace place, ClientFactory clientFactory) {
		
		super(clientFactory);
		if (place.getId() != null)
		{
			this.metadataId = place.getId();
		}
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		metadataEditingView = clientFactory.getMetadataEditingView();
		containerWidget.setWidget(metadataEditingView.asWidget());
		broadcastActivityTitle(getMessage().metadataEditingTitle());
		metadataEditingView.setPresenter(this);
		metadataEditingView.edit(createFakeMetadata());
	}
	
	private String generatedXML="";

	
	private MetadataDTO createFakeMetadata()
	{
		MetadataDTO aux = new MetadataDTO();
		aux.setResourceTitle("mon titre");
		aux.setResourceAbstract("mon resumé");
		return aux; 
	}
	
	@Override
	public void generateXML(MetadataDTO metadata) {
		// TODO Auto-generated method stub
		metadataService.toXML(metadata, (new AsyncCallback<String>() {

			@Override
			public void onSuccess(String xml) {
				metadataEditingView.setGeneratedXML(xml);
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		}));
		
		
		
		/*MetadataRequest metadataRequest = clientFactory.getRbvRequestFactory().metadataRequest();
		MetadataProxy proxy = metadataRequest.create(MetadataProxy.class);
		proxy.setResourceTitle("mon titre");
		proxy.setResourceAbstract("mon resumé");
		Request<String> xmlRequest = metadataRequest.toXML(metadata);
		
		xmlRequest.fire( new Receiver<String>() {
		      @Override
		      public void onSuccess(String result) {
		    	  
		    	  setGeneratedXML(result);
		      }
		    });
		
		return getGeneratedXML();*/
	}

	public String getGeneratedXML() {
		return generatedXML;
	}

	public void setGeneratedXML(String generatedXML) {
		this.generatedXML = generatedXML;
	}

}
