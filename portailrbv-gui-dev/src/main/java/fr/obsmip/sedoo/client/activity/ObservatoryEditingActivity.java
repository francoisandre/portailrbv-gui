package fr.obsmip.sedoo.client.activity;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.obsmip.sedoo.client.ClientFactory;
import fr.obsmip.sedoo.client.domain.ObservatoryDTO;
import fr.obsmip.sedoo.client.event.ActionEndEvent;
import fr.obsmip.sedoo.client.event.ActionEventConstant;
import fr.obsmip.sedoo.client.event.ActionStartEvent;
import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.place.DrainageBasinEditingPlace;
import fr.obsmip.sedoo.client.place.ObservatoryEditingPlace;
import fr.obsmip.sedoo.client.service.ObservatoryService;
import fr.obsmip.sedoo.client.service.ObservatoryServiceAsync;
import fr.obsmip.sedoo.client.ui.ObservatoryEditingView;
import fr.obsmip.sedoo.client.ui.ObservatoryEditingView.Presenter;
import fr.obsmip.sedoo.client.ui.misc.DialogBoxTools;

public class ObservatoryEditingActivity extends RBVAbstractActivity implements Presenter {

	private Long observatoryId;
	
	private String previousHash;

	public ObservatoryEditingActivity(ObservatoryEditingPlace place, ClientFactory clientFactory) {
		super(clientFactory);
		if (place.getObservatoryId() != null)
		{
			observatoryId = place.getObservatoryId();
		}
	}


	private final ObservatoryServiceAsync observatoryService = GWT.create(ObservatoryService.class);
	ObservatoryEditingView observatoryEditingView;


	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		observatoryEditingView = clientFactory.getObservatoryEditingView();
		observatoryEditingView.setPresenter(this);
		containerWidget.setWidget(observatoryEditingView.asWidget());
		broadcastActivityTitle(getMessage().observatoryEditingViewModificationHeader());
		if (observatoryId != null)
		{
			observatoryService.getObservatoryById(observatoryId, new AsyncCallback<ObservatoryDTO>() {

				@Override
				public void onSuccess(ObservatoryDTO result) {
					broadcastActivityTitle(getMessage().observatoryEditingViewModificationHeader()+" ("+result.getShortLabel()+")");
					previousHash = result.getHash();
					observatoryEditingView.edit(result);

				}

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub

				}
			});


		}
		else
		{
			broadcastActivityTitle(getMessage().observatoryEditingViewCreationHeader());
			ObservatoryDTO newItem = new ObservatoryDTO();
			previousHash="";
			observatoryEditingView.edit(newItem);
		}



	}

	public void createDrainageBasin(ObservatoryDTO observatory)
	{
		DrainageBasinEditingPlace place = new DrainageBasinEditingPlace();
		place.setId(observatory.getId());
		place.setMode(DrainageBasinEditingPlace.CREATE);
		clientFactory.getPlaceController().goTo(place);
	}

	@Override
	public void editDrainageBasin(Long id) {
		DrainageBasinEditingPlace place = new DrainageBasinEditingPlace();
		place.setId(id);
		place.setMode(DrainageBasinEditingPlace.MODIFY);
		clientFactory.getPlaceController().goTo(place);		
	}
	
	public String mayStop() {
	    ObservatoryDTO current = observatoryEditingView.flush();
	    if (previousHash.compareTo(current.getHash()) != 0)
	    {
	    	return Message.INSTANCE.unsavedModificationsConfirmation();
	    }
	    else
	    {
	    	return null;
	    }
	}

	@Override
	public void save(final ObservatoryDTO observatoryDTO) 
	{
		if (observatoryId != null)
		{
			observatoryDTO.setId(observatoryId);
		}
		
		ActionStartEvent startEvent = new ActionStartEvent(Message.INSTANCE.saving(), ActionEventConstant.OBSERVATORY_SAVING_EVENT, true);
        clientFactory.getEventBus().fireEvent(startEvent);
		observatoryService.saveObservatory(observatoryDTO, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) 
			{
				previousHash = observatoryDTO.getHash();
				ActionEndEvent e = new ActionEndEvent(ActionEventConstant.OBSERVATORY_SAVING_EVENT);
		        clientFactory.getEventBus().fireEvent(e);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				
				DialogBoxTools.modalAlert(Message.INSTANCE.error(), Message.INSTANCE.anErrorHasHappened()+" "+caught.getMessage());
				ActionEndEvent e = new ActionEndEvent(ActionEventConstant.OBSERVATORY_SAVING_EVENT);
		        clientFactory.getEventBus().fireEvent(e);
				
			}
		});
		
	}

	@Override
	public void deleteDrainageBasin(Long id) 
	{
		observatoryService.deleteDrainageBasin(id, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
			
	}

}
