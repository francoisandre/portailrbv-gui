package fr.obsmip.sedoo.client.activity;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.obsmip.sedoo.client.ClientFactory;
import fr.obsmip.sedoo.client.ShortcutFactory;
import fr.obsmip.sedoo.client.domain.SummaryDTO;
import fr.obsmip.sedoo.client.event.ActionEndEvent;
import fr.obsmip.sedoo.client.event.ActionEventConstant;
import fr.obsmip.sedoo.client.event.ActionStartEvent;
import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.place.MetadataDisplayPlace;
import fr.obsmip.sedoo.client.place.MetadataListPlace;
import fr.obsmip.sedoo.client.service.MetadataService;
import fr.obsmip.sedoo.client.service.MetadataServiceAsync;
import fr.obsmip.sedoo.client.ui.MetadataListView;
import fr.obsmip.sedoo.client.ui.misc.DialogBoxTools;
import fr.obsmip.sedoo.client.ui.misc.Shortcut;

public class MetadataListActivity extends RBVAbstractActivity implements MetadataListView.Presenter {


    private final MetadataServiceAsync metadataService = GWT.create(MetadataService.class);
    
    public MetadataListActivity(MetadataListPlace place, ClientFactory clientFactory) {
        super(clientFactory);
    }

    /**
     * Invoked by the ActivityManager to start a new Activity
     */
    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
    	final MetadataListView metadataListView = clientFactory.getMetadataListView();
    	metadataListView.setPresenter(this);
        containerWidget.setWidget(metadataListView.asWidget());
        broadcastActivityTitle(Message.INSTANCE.listingViewTitle());
        List<Shortcut> shortcuts = new ArrayList<Shortcut>();
		shortcuts.add(ShortcutFactory.getWelcomeShortcut());
		shortcuts.add(ShortcutFactory.getMetadataListShortcut());
		clientFactory.getBreadCrumb().refresh(shortcuts);
        ActionStartEvent e = new ActionStartEvent(Message.INSTANCE.loading(), ActionEventConstant.METADATA_LIST_LOADING_EVENT, true);
        clientFactory.getEventBus().fireEvent(e);
        
        metadataService.getSummaries((new AsyncCallback<List<SummaryDTO>>() {

			@Override
			public void onSuccess(List<SummaryDTO> summaries ) {
				metadataListView.setSummaryDTOList(summaries);
				ActionEndEvent e = new ActionEndEvent(ActionEventConstant.METADATA_LIST_LOADING_EVENT);
		        clientFactory.getEventBus().fireEvent(e);
			}

			@Override
			public void onFailure(Throwable caught) {
				
				DialogBoxTools.modalAlert(Message.INSTANCE.error(), Message.INSTANCE.anErrorHasHappened()+" "+caught.getMessage());
				ActionEndEvent e = new ActionEndEvent(ActionEventConstant.METADATA_LIST_LOADING_EVENT);
		        clientFactory.getEventBus().fireEvent(e);
			}
		}));
        
                
        
    }

    /**
     * Ask user before stopping this activity
     */
    @Override
    public String mayStop() {
//        return "Please hold on. This activity is stopping.";
    	return null;
    }

	@Override
	public void displayMetadataPDF(String id) 
	{
		ActionStartEvent startEvent = new ActionStartEvent("Ouverture du fichier PDF en cours...", ActionEventConstant.METADATA_PDF_LOADING_EVENT, true);
        clientFactory.getEventBus().fireEvent(startEvent);
        
        
        metadataService.getPDFURL(id, new AsyncCallback<String>() {

			@Override
			public void onSuccess(String url ) {
				Window.open(url,"_blank","enabled");
				ActionEndEvent endEvent = new ActionEndEvent(ActionEventConstant.METADATA_PDF_LOADING_EVENT);
		        clientFactory.getEventBus().fireEvent(endEvent);
			}

			@Override
			public void onFailure(Throwable caught) {
				
				DialogBoxTools.modalAlert(Message.INSTANCE.error(), Message.INSTANCE.anErrorHasHappened()+" "+caught.getMessage());
				ActionEndEvent e = new ActionEndEvent(ActionEventConstant.METADATA_PDF_LOADING_EVENT);
		        clientFactory.getEventBus().fireEvent(e);
			}
		});
        
		
		
	}

	@Override
	public void displayMetadata(String id) 
	{
		
		 clientFactory.getPlaceController().goTo(new MetadataDisplayPlace(id));
	}

    
}