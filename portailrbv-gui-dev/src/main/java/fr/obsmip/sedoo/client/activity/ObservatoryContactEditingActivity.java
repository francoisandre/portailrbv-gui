package fr.obsmip.sedoo.client.activity;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.obsmip.sedoo.client.ClientFactory;
import fr.obsmip.sedoo.client.Constants;
import fr.obsmip.sedoo.client.domain.ObservatoryContactDTO;
import fr.obsmip.sedoo.client.domain.ValidationAlert;
import fr.obsmip.sedoo.client.event.ActionEndEvent;
import fr.obsmip.sedoo.client.event.ActionEventConstant;
import fr.obsmip.sedoo.client.event.ActionStartEvent;
import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.place.ObservatoryContactEditingPlace;
import fr.obsmip.sedoo.client.place.ObservatoryEditingPlace;
import fr.obsmip.sedoo.client.service.ObservatoryService;
import fr.obsmip.sedoo.client.service.ObservatoryServiceAsync;
import fr.obsmip.sedoo.client.ui.ObservatoryContactEditingView;
import fr.obsmip.sedoo.client.ui.ObservatoryContactEditingView.Presenter;
import fr.obsmip.sedoo.client.ui.misc.DialogBoxTools;

public class ObservatoryContactEditingActivity extends AbstractDTOEditingActivity implements Presenter{

	private Long id;
	private Long observatoryId;
	

	public ObservatoryContactEditingActivity(ObservatoryContactEditingPlace place, ClientFactory clientFactory) {
		super(clientFactory);
		setMode(place.getMode());
		if (place.getMode().compareTo(Constants.CREATE)==0)
		{
			observatoryId = place.getId();
		}
		else
		{
			id = place.getId();
		}
	}


	private final ObservatoryServiceAsync observatoryService = GWT.create(ObservatoryService.class);


	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		ObservatoryContactEditingView observatoryContactEditingView = clientFactory.getObservatoryContactEditingView();
		view = observatoryContactEditingView;
		observatoryContactEditingView.setPresenter(this);
		containerWidget.setWidget(observatoryContactEditingView.asWidget());
		if (getMode().compareTo(Constants.MODIFY) == 0)
		{
			observatoryService.getObservatoryContactById(id, new AsyncCallback<ObservatoryContactDTO>() {

				@Override
				public void onSuccess(ObservatoryContactDTO result) {
					broadcastActivityTitle(Message.INSTANCE.observatoryContactEditingViewModificationHeader() +" ("+result.getPersonName()+")");
					previousHash = result.getHash();
					setObservatoryId(result.getObservatoryId());
					view.edit(result);
					view.setMode(Constants.MODIFY);
				}

				@Override
				public void onFailure(Throwable caught) {
					DialogBoxTools.modalAlert(Message.INSTANCE.error(), Message.INSTANCE.anErrorHasHappened()+" : "+caught.getMessage());

				}
			});
			
			
		}
		else
		{
			previousHash = "";
			broadcastActivityTitle(Message.INSTANCE.observatoryContactEditingViewCreationHeader());
			observatoryContactEditingView.edit(new ObservatoryContactDTO());
			view.setMode(Constants.CREATE);
		}
	}


	
	
	

	@Override
	public void save(final ObservatoryContactDTO observatoryContactDTO) 
	{
		List<ValidationAlert> result = observatoryContactDTO.validate();
		 if (result.isEmpty() == false)
		 {
			 DialogBoxTools.popUp(Message.INSTANCE.error(), ValidationAlert.toHTML(result), DialogBoxTools.HTML_MODE);
			 return;
		 }
		//Modification mode
		if (getMode().compareTo(Constants.MODIFY) == 0)
		{
			observatoryContactDTO.setId(id);
		}
		
		ActionStartEvent startEvent = new ActionStartEvent(Message.INSTANCE.saving(), ActionEventConstant.OBSERVATORY_CONTACT_SAVING_EVENT, true);
        clientFactory.getEventBus().fireEvent(startEvent);
        if (getMode().compareTo(Constants.MODIFY) == 0)
        {
        	observatoryService.saveObservatoryContact(observatoryContactDTO, new AsyncCallback<Void>() {

        		@Override
        		public void onSuccess(Void result) 
        		{
        			previousHash = observatoryContactDTO.getHash();
        			ActionEndEvent e = new ActionEndEvent(ActionEventConstant.OBSERVATORY_CONTACT_SAVING_EVENT);
        			clientFactory.getEventBus().fireEvent(e);
        			broadcastActivityTitle(Message.INSTANCE.observatoryContactEditingViewModificationHeader() +" ("+observatoryContactDTO.getPersonName()+")");
        			view.setMode(Constants.MODIFY);
        		}

        		@Override
        		public void onFailure(Throwable caught) {

        			DialogBoxTools.modalAlert(Message.INSTANCE.error(), Message.INSTANCE.anErrorHasHappened()+" "+caught.getMessage());
        			ActionEndEvent e = new ActionEndEvent(ActionEventConstant.OBSERVATORY_CONTACT_SAVING_EVENT);
        			clientFactory.getEventBus().fireEvent(e);

        		}
        	});
        }
        else
        {
        	observatoryService.addObservatoryContact(observatoryContactDTO, observatoryId, new AsyncCallback<Long>() {
        		@Override
        		public void onSuccess(Long result) 
        		{
        			previousHash = observatoryContactDTO.getHash();
        			ActionEndEvent e = new ActionEndEvent(ActionEventConstant.OBSERVATORY_CONTACT_SAVING_EVENT);
        			clientFactory.getEventBus().fireEvent(e);
        			broadcastActivityTitle(Message.INSTANCE.observatoryContactEditingViewModificationHeader() +" ("+observatoryContactDTO.getPersonName()+")");
        			view.setMode(Constants.MODIFY);
        			id = result;
        		}

        		@Override
        		public void onFailure(Throwable caught) {

        			DialogBoxTools.modalAlert(Message.INSTANCE.error(), Message.INSTANCE.anErrorHasHappened()+" "+caught.getMessage());
        			ActionEndEvent e = new ActionEndEvent(ActionEventConstant.OBSERVATORY_CONTACT_SAVING_EVENT);
        			clientFactory.getEventBus().fireEvent(e);

        		}
        	});
        }
	}






	public Long getObservatoryId() {
		return observatoryId;
	}






	public void setObservatoryId(Long observatoryId) {
		this.observatoryId = observatoryId;
	}



	@Override
	public void back() {
		ObservatoryEditingPlace place = new ObservatoryEditingPlace();
		place.setObservatoryId(getObservatoryId());
		clientFactory.getPlaceController().goTo(place);	
	}

}
