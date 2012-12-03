package fr.obsmip.sedoo.client.activity;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.obsmip.sedoo.client.ClientFactory;
import fr.obsmip.sedoo.client.Constants;
import fr.obsmip.sedoo.client.ShortcutFactory;
import fr.obsmip.sedoo.client.domain.ObservatoryDTO;
import fr.obsmip.sedoo.client.event.ActionEndEvent;
import fr.obsmip.sedoo.client.event.ActionEventConstant;
import fr.obsmip.sedoo.client.event.ActionStartEvent;
import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.place.DrainageBasinChoicePlace;
import fr.obsmip.sedoo.client.place.MetadataEditingPlace;
import fr.obsmip.sedoo.client.service.ObservatoryService;
import fr.obsmip.sedoo.client.service.ObservatoryServiceAsync;
import fr.obsmip.sedoo.client.ui.DrainageBasinChoiceView;
import fr.obsmip.sedoo.client.ui.misc.DialogBoxTools;
import fr.obsmip.sedoo.client.ui.misc.Shortcut;

public class DrainageBasinChoiceActivity extends RBVAbstractActivity implements DrainageBasinChoiceView.Presenter {
	private DrainageBasinChoiceView view;
	
	private final ObservatoryServiceAsync observatoryService = GWT.create(ObservatoryService.class);

	public DrainageBasinChoiceActivity(DrainageBasinChoicePlace place, ClientFactory clientFactory) {
		super(clientFactory);
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		view = clientFactory.getDrainageBasinChoiceView();
		view.setPresenter(this);
		view.reset();
		containerWidget.setWidget(view.asWidget());
		broadcastActivityTitle(getMessage().metadataCreatingTitle());
		List<Shortcut> shortcuts = new ArrayList<Shortcut>();
		shortcuts.add(ShortcutFactory.getWelcomeShortcut());
		clientFactory.getBreadCrumb().refresh(shortcuts);
		ActionStartEvent e = new ActionStartEvent(Message.INSTANCE.loading(), ActionEventConstant.OBSERVATORIES_LOADING_EVENT, true);
        clientFactory.getEventBus().fireEvent(e);
		 observatoryService.getObservatories(new AsyncCallback<List<ObservatoryDTO>>() {

				@Override
				public void onSuccess(List<ObservatoryDTO> observatories ) {
					
					view.setObservatories(observatories);
					ActionEndEvent e = new ActionEndEvent(ActionEventConstant.OBSERVATORIES_LOADING_EVENT);
			        clientFactory.getEventBus().fireEvent(e);
				}


				@Override
				public void onFailure(Throwable caught) {
					DialogBoxTools.modalAlert(Message.INSTANCE.error(), Message.INSTANCE.anErrorHasHappened()+" "+caught.getMessage());
					ActionEndEvent e = new ActionEndEvent(ActionEventConstant.OBSERVATORIES_LOADING_EVENT);
			        clientFactory.getEventBus().fireEvent(e);
				}
			});
		
		
	}

	@Override
	public void createMetadata(Long drainageBasinId) {
		
		MetadataEditingPlace place = new MetadataEditingPlace();
		place.setMode(Constants.CREATE);
		place.setDrainageBasinId(drainageBasinId);
		clientFactory.getPlaceController().goTo(place);	
	}

	@Override
	public void getDrainageBasins(Long observatoryId) {
		ActionStartEvent e = new ActionStartEvent(Message.INSTANCE.loading(), ActionEventConstant.DRAINAGE_BASIN_LOADING_EVENT, true);
        clientFactory.getEventBus().fireEvent(e);
		 observatoryService.getObservatoryById(observatoryId, new AsyncCallback<ObservatoryDTO>() {

				@Override
				public void onSuccess(ObservatoryDTO observatory) {
					view.setDrainageBasins(observatory.getDrainageBasinDTOs());
					ActionEndEvent e = new ActionEndEvent(ActionEventConstant.DRAINAGE_BASIN_LOADING_EVENT);
			        clientFactory.getEventBus().fireEvent(e);
				}


				@Override
				public void onFailure(Throwable caught) {
					DialogBoxTools.modalAlert(Message.INSTANCE.error(), Message.INSTANCE.anErrorHasHappened()+" "+caught.getMessage());
					ActionEndEvent e = new ActionEndEvent(ActionEventConstant.DRAINAGE_BASIN_LOADING_EVENT);
			        clientFactory.getEventBus().fireEvent(e);
				}
			});		
	}

}
