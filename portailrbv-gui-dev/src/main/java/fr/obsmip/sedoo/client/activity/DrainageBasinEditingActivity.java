package fr.obsmip.sedoo.client.activity;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.obsmip.sedoo.client.ClientFactory;
import fr.obsmip.sedoo.client.domain.AbstractDTO;
import fr.obsmip.sedoo.client.domain.DrainageBasinDTO;
import fr.obsmip.sedoo.client.domain.ValidationAlert;
import fr.obsmip.sedoo.client.event.ActionEndEvent;
import fr.obsmip.sedoo.client.event.ActionEventConstant;
import fr.obsmip.sedoo.client.event.ActionStartEvent;
import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.place.DrainageBasinEditingPlace;
import fr.obsmip.sedoo.client.service.ObservatoryService;
import fr.obsmip.sedoo.client.service.ObservatoryServiceAsync;
import fr.obsmip.sedoo.client.ui.DrainageBasinEditingView;
import fr.obsmip.sedoo.client.ui.DrainageBasinEditingView.Presenter;
import fr.obsmip.sedoo.client.ui.misc.DialogBoxTools;

public class DrainageBasinEditingActivity extends AbstractDTOEditingActivity implements Presenter{

	private Long id;
	private String mode;
//	private DrainageBasinEditingView drainageBasinEditingView;

	public DrainageBasinEditingActivity(DrainageBasinEditingPlace place, ClientFactory clientFactory) {
		super(clientFactory);
		
		if (place.getId() != null)
		{
			id = place.getId();
		}
		if (place.getMode() != null)
		{
			mode = place.getMode();
		}
	}


	private final ObservatoryServiceAsync observatoryService = GWT.create(ObservatoryService.class);


	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		DrainageBasinEditingView drainageBasinEditingView = clientFactory.getDrainageBasinEditingView();
		view = drainageBasinEditingView;
		drainageBasinEditingView.setPresenter(this);
		containerWidget.setWidget(drainageBasinEditingView.asWidget());
		if (mode.compareTo(DrainageBasinEditingPlace.MODIFY) == 0)
		{
			observatoryService.getDrainageBasinById(id, new AsyncCallback<DrainageBasinDTO>() {

				@Override
				public void onSuccess(DrainageBasinDTO result) {
					broadcastActivityTitle(Message.INSTANCE.drainageBasinEditingViewModificationHeader() +" ("+result.getLabel()+")");
					previousHash = result.getHash();
					view.edit(result);

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
			drainageBasinEditingView.edit(new DrainageBasinDTO());
			broadcastActivityTitle(Message.INSTANCE.drainageBasinEditingViewCreationHeader());
		}
	}


	
	
	

	@Override
	public void save(final DrainageBasinDTO drainageBasinDTO) 
	{
		List<ValidationAlert> validate = drainageBasinDTO.validate();
		if (validate.isEmpty() == false)
		{
			DialogBoxTools.popUp(Message.INSTANCE.error(), "Erreurs...");
			return;
		}
		
		//Modification mode
		if (mode.compareTo(DrainageBasinEditingPlace.MODIFY) == 0)
		{
			drainageBasinDTO.setId(id);
		}
		
		ActionStartEvent startEvent = new ActionStartEvent(Message.INSTANCE.saving(), ActionEventConstant.DRAINAGE_BASIN_SAVING_EVENT, true);
        clientFactory.getEventBus().fireEvent(startEvent);
		observatoryService.saveDrainageBasin(drainageBasinDTO, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) 
			{
				previousHash = drainageBasinDTO.getHash();
				ActionEndEvent e = new ActionEndEvent(ActionEventConstant.DRAINAGE_BASIN_SAVING_EVENT);
		        clientFactory.getEventBus().fireEvent(e);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				
				DialogBoxTools.modalAlert(Message.INSTANCE.error(), Message.INSTANCE.anErrorHasHappened()+" "+caught.getMessage());
				ActionEndEvent e = new ActionEndEvent(ActionEventConstant.DRAINAGE_BASIN_SAVING_EVENT);
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
