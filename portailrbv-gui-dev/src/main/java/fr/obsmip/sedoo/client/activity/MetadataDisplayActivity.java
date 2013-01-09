package fr.obsmip.sedoo.client.activity;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.obsmip.sedoo.client.ClientFactory;
import fr.obsmip.sedoo.client.ShortcutFactory;
import fr.obsmip.sedoo.client.domain.metadata.MetadataDTO;
import fr.obsmip.sedoo.client.event.ActionEndEvent;
import fr.obsmip.sedoo.client.event.ActionEventConstant;
import fr.obsmip.sedoo.client.event.ActionStartEvent;
import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.place.MetadataDisplayPlace;
import fr.obsmip.sedoo.client.service.MetadataService;
import fr.obsmip.sedoo.client.service.MetadataServiceAsync;
import fr.obsmip.sedoo.client.ui.MetadataDisplayView;
import fr.obsmip.sedoo.client.ui.misc.Shortcut;

public class MetadataDisplayActivity extends RBVAbstractActivity  {
	
	MetadataDisplayView metadataDisplayView = null;
	
	private final MetadataServiceAsync metadataService = GWT.create(MetadataService.class);

	private String metadataUiid;
	
	public MetadataDisplayActivity(MetadataDisplayPlace place, ClientFactory clientFactory) {
		
		super(clientFactory);
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
				String detail = "";
				
				if (metadataDTO.getIdentificationPart().getResourceTitle().trim().length()>0)
				{
					detail =" ("+metadataDTO.getIdentificationPart().getResourceTitle()+")";
				}
								
				broadcastActivityTitle(Message.INSTANCE.metadataDisplayingTitle()+detail);
				if (clientFactory.getBreadCrumb().getShortcuts().isEmpty())
				{
					List<Shortcut> shortcuts = new ArrayList<Shortcut>();
					shortcuts.add(ShortcutFactory.getWelcomeShortcut());
					shortcuts.add(ShortcutFactory.getMetadataManagementShortcut());
					shortcuts.add(ShortcutFactory.getMetadataDisplayingShortcut(metadataDTO.getIdentificationPart().getResourceTitle()));
					clientFactory.getBreadCrumb().refresh(shortcuts);
				}
				else
				{
					clientFactory.getBreadCrumb().addShortcut(ShortcutFactory.getMetadataDisplayingShortcut(metadataDTO.getIdentificationPart().getResourceTitle()));
				}
				ActionEndEvent e = new ActionEndEvent(ActionEventConstant.METADATA_LOADING_EVENT);
		        clientFactory.getEventBus().fireEvent(e);
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});
		
	}
}
