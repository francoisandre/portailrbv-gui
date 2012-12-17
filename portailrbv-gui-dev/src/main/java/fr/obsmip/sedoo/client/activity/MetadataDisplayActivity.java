package fr.obsmip.sedoo.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.obsmip.sedoo.client.ClientFactory;
import fr.obsmip.sedoo.client.domain.metadata.MetadataDTO;
import fr.obsmip.sedoo.client.event.ActionEndEvent;
import fr.obsmip.sedoo.client.event.ActionEventConstant;
import fr.obsmip.sedoo.client.event.ActionStartEvent;
import fr.obsmip.sedoo.client.place.MetadataDisplayPlace;
import fr.obsmip.sedoo.client.service.MetadataService;
import fr.obsmip.sedoo.client.service.MetadataServiceAsync;
import fr.obsmip.sedoo.client.ui.MetadataDisplayView;

public class MetadataDisplayActivity extends AbstractActivity  {
	private ClientFactory clientFactory;
	
	MetadataDisplayView metadataDisplayView = null;
	
	private final MetadataServiceAsync metadataService = GWT.create(MetadataService.class);

	private String metadataUiid;
	
	public MetadataDisplayActivity(MetadataDisplayPlace place, ClientFactory clientFactory) {
		
		this.clientFactory = clientFactory;
		if (place.getId() != null)
		{
			this.metadataUiid = place.getId();
		}
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		metadataDisplayView = clientFactory.getMetadataDisplayView();
		containerWidget.setWidget(metadataDisplayView.asWidget());
		ActionStartEvent e = new ActionStartEvent("Chargement de la métadonnée en cours...", ActionEventConstant.METADATA_LOADING_EVENT, true);
        clientFactory.getEventBus().fireEvent(e);
        metadataDisplayView.reset();
		metadataService.getMetadataByUuid(metadataUiid, new AsyncCallback<MetadataDTO>() {

			@Override
			public void onSuccess(MetadataDTO metadataDTO) {
				metadataDisplayView.display(metadataDTO);
				ActionEndEvent e = new ActionEndEvent(ActionEventConstant.METADATA_LOADING_EVENT);
		        clientFactory.getEventBus().fireEvent(e);
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});
		
	}
}
