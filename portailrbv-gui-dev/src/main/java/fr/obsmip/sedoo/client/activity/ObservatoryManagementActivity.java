package fr.obsmip.sedoo.client.activity;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.obsmip.sedoo.client.ClientFactory;
import fr.obsmip.sedoo.client.domain.ObservatoryDTO;
import fr.obsmip.sedoo.client.event.ActionEndEvent;
import fr.obsmip.sedoo.client.event.ActionEventConstant;
import fr.obsmip.sedoo.client.event.ActionStartEvent;
import fr.obsmip.sedoo.client.place.ObservatoryEditingPlace;
import fr.obsmip.sedoo.client.place.ObservatoryManagementPlace;
import fr.obsmip.sedoo.client.service.ObservatoryService;
import fr.obsmip.sedoo.client.service.ObservatoryServiceAsync;
import fr.obsmip.sedoo.client.service.UserService;
import fr.obsmip.sedoo.client.service.UserServiceAsync;
import fr.obsmip.sedoo.client.ui.ObservatoryManagementView;
import fr.obsmip.sedoo.client.ui.ObservatoryManagementView.Presenter;

public class ObservatoryManagementActivity extends RBVAbstractActivity implements Presenter {
	

	public ObservatoryManagementActivity(ObservatoryManagementPlace place, ClientFactory clientFactory) {
		super(clientFactory);
	}
	
	private final UserServiceAsync userService = GWT.create(UserService.class);
	private final ObservatoryServiceAsync observatoryService = GWT.create(ObservatoryService.class);
	ObservatoryManagementView observatoryManagementView;
	

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		observatoryManagementView = clientFactory.getObservatoryManagementView();
		observatoryManagementView.setPresenter(this);
		containerWidget.setWidget(observatoryManagementView.asWidget());
		broadcastActivityTitle(getMessage().observatoryManagementViewHeader());
		
		 ActionStartEvent e = new ActionStartEvent("Chargement des observatoires en cours...", ActionEventConstant.OBSERVATORIES_LOADING_EVENT, true);
	        clientFactory.getEventBus().fireEvent(e);
	        
	        observatoryService.getObservatories(new AsyncCallback<List<ObservatoryDTO>>() {

				@Override
				public void onSuccess(List<ObservatoryDTO> observatories ) {
					
					observatoryManagementView.init(observatories);
					ActionEndEvent e = new ActionEndEvent(ActionEventConstant.OBSERVATORIES_LOADING_EVENT);
			        clientFactory.getEventBus().fireEvent(e);
				}


				@Override
				public void onFailure(Throwable caught) {
					//TODO Traiter correctement
					ActionEndEvent e = new ActionEndEvent(ActionEventConstant.OBSERVATORIES_LOADING_EVENT);
			        clientFactory.getEventBus().fireEvent(e);
					//metadataListView.displayFailureMessage();
				}
			});
		
		
		}

	@Override
	public void deleteObservatory(final Long id) {
		observatoryService.deleteObservatory(id, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				observatoryManagementView.broadcastObservatoryDeletion(id, true);
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				observatoryManagementView.broadcastObservatoryDeletion(id, false);
				
			}
		});
		
	}

	@Override
	public void modifyObservatory(ObservatoryDTO observatoryDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int addObservatory(ObservatoryDTO observatoryDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void goToCreateObservatoryActivity() {
		clientFactory.getPlaceController().goTo(new ObservatoryEditingPlace());
	}

	@Override
	public void goToEditObservatoryActivity(ObservatoryDTO observatoryDTO) {
		ObservatoryEditingPlace place = new ObservatoryEditingPlace();
		place.setObservatoryId(observatoryDTO.getId());
		clientFactory.getPlaceController().goTo(place);
		
	}
}
