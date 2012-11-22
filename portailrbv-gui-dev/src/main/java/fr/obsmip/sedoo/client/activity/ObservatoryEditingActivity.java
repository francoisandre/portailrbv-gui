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
import fr.obsmip.sedoo.client.domain.ValidationAlert;
import fr.obsmip.sedoo.client.event.ActionEndEvent;
import fr.obsmip.sedoo.client.event.ActionEventConstant;
import fr.obsmip.sedoo.client.event.ActionStartEvent;
import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.place.DrainageBasinEditingPlace;
import fr.obsmip.sedoo.client.place.ObservatoryContactEditingPlace;
import fr.obsmip.sedoo.client.place.ObservatoryEditingPlace;
import fr.obsmip.sedoo.client.place.ObservatoryManagementPlace;
import fr.obsmip.sedoo.client.service.ObservatoryService;
import fr.obsmip.sedoo.client.service.ObservatoryServiceAsync;
import fr.obsmip.sedoo.client.ui.ObservatoryEditingView;
import fr.obsmip.sedoo.client.ui.ObservatoryEditingView.Presenter;
import fr.obsmip.sedoo.client.ui.misc.DialogBoxTools;
import fr.obsmip.sedoo.client.ui.misc.Shortcut;

public class ObservatoryEditingActivity extends AbstractDTOEditingActivity implements Presenter {

	private Long observatoryId;
	private String mode;
	
	public ObservatoryEditingActivity(ObservatoryEditingPlace place, ClientFactory clientFactory) {
		super(clientFactory);
		if (place.getObservatoryId() != null)
		{
			observatoryId = place.getObservatoryId();
			mode = Constants.MODIFY;
		}
		else
		{
			mode = Constants.CREATE;
		}
	}


	private final ObservatoryServiceAsync observatoryService = GWT.create(ObservatoryService.class);
//	ObservatoryEditingView observatoryEditingView;


	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		ObservatoryEditingView observatoryEditingView = clientFactory.getObservatoryEditingView();
		view = observatoryEditingView;
		observatoryEditingView.setPresenter(this);
		containerWidget.setWidget(observatoryEditingView.asWidget());
		broadcastActivityTitle(getMessage().observatoryEditingViewModificationHeader());
		if (mode.compareTo(Constants.MODIFY)==0)
		{
			observatoryService.getObservatoryById(observatoryId, new AsyncCallback<ObservatoryDTO>() {

				@Override
				public void onSuccess(ObservatoryDTO result) {
					broadcastActivityTitle(getMessage().observatoryEditingViewModificationHeader());
					  List<Shortcut> shortcuts = new ArrayList<Shortcut>();
						shortcuts.add(ShortcutFactory.getWelcomeShortcut());
						shortcuts.add(ShortcutFactory.getObservatoryManagementShortcut());
						shortcuts.add(ShortcutFactory.getObservatoryModificationShortcut(result.getShortLabel(), observatoryId));
						clientFactory.getBreadCrumb().refresh(shortcuts);
					previousHash = result.getHash();
					view.setMode(Constants.MODIFY);
					view.edit(result);

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
			List<Shortcut> shortcuts = new ArrayList<Shortcut>();
			shortcuts.add(ShortcutFactory.getWelcomeShortcut());
			shortcuts.add(ShortcutFactory.getObservatoryManagementShortcut());
			shortcuts.add(ShortcutFactory.getObservatoryCreationShortcut());
			clientFactory.getBreadCrumb().refresh(shortcuts);
			ObservatoryDTO newItem = new ObservatoryDTO();
			previousHash="";
			view.setMode(Constants.CREATE);
			observatoryEditingView.edit(newItem);
		}



	}

	public void createDrainageBasin(ObservatoryDTO observatory)
	{
		DrainageBasinEditingPlace place = new DrainageBasinEditingPlace();
		place.setId(observatory.getId());
		place.setMode(Constants.CREATE);
		clientFactory.getPlaceController().goTo(place);
	}
	
	public void createObservatoryContact(ObservatoryDTO observatory)
	{
		ObservatoryContactEditingPlace place = new ObservatoryContactEditingPlace();
		place.setId(observatory.getId());
		place.setMode(Constants.CREATE);
		clientFactory.getPlaceController().goTo(place);
	}

	@Override
	public void editDrainageBasin(Long id) {
		DrainageBasinEditingPlace place = new DrainageBasinEditingPlace();
		place.setId(id);
		place.setMode(Constants.MODIFY);
		clientFactory.getPlaceController().goTo(place);		
	}
	
	@Override
	public void editObservatoryContact(Long id) {
		ObservatoryContactEditingPlace place = new ObservatoryContactEditingPlace();
		place.setId(id);
		place.setMode(Constants.MODIFY);
		clientFactory.getPlaceController().goTo(place);		
	}
	
	@Override
	public void save(final ObservatoryDTO observatoryDTO) 
	{
		List<ValidationAlert> validationResult = observatoryDTO.validate();
		if (validationResult.isEmpty() == false)
		{
			 DialogBoxTools.popUp(Message.INSTANCE.error(), ValidationAlert.toHTML(validationResult), DialogBoxTools.HTML_MODE);
			 return;
		}
		
		if (mode.compareTo(Constants.MODIFY)==0)
		{
			observatoryDTO.setId(observatoryId);
			ActionStartEvent startEvent = new ActionStartEvent(Message.INSTANCE.saving(), ActionEventConstant.OBSERVATORY_SAVING_EVENT, true);
	        clientFactory.getEventBus().fireEvent(startEvent);
			observatoryService.saveObservatory(observatoryDTO, new AsyncCallback<Void>() {
				
				@Override
				public void onSuccess(Void result) 
				{
					previousHash = observatoryDTO.getHash();
					ActionEndEvent e = new ActionEndEvent(ActionEventConstant.OBSERVATORY_SAVING_EVENT);
			        clientFactory.getEventBus().fireEvent(e);
			        broadcastActivityTitle(getMessage().observatoryEditingViewModificationHeader());
			        List<Shortcut> shortcuts = new ArrayList<Shortcut>();
			        shortcuts.add(ShortcutFactory.getWelcomeShortcut());
			        shortcuts.add(ShortcutFactory.getObservatoryManagementShortcut());
			        shortcuts.add(ShortcutFactory.getObservatoryModificationShortcut(observatoryDTO.getShortLabel(), observatoryId));
			        clientFactory.getBreadCrumb().refresh(shortcuts);
				}
				
				@Override
				public void onFailure(Throwable caught) {
					
					DialogBoxTools.modalAlert(Message.INSTANCE.error(), Message.INSTANCE.anErrorHasHappened()+" "+caught.getMessage());
					ActionEndEvent e = new ActionEndEvent(ActionEventConstant.OBSERVATORY_SAVING_EVENT);
			        clientFactory.getEventBus().fireEvent(e);
					
				}
			});
		}
		else
		{
			ActionStartEvent startEvent = new ActionStartEvent(Message.INSTANCE.saving(), ActionEventConstant.OBSERVATORY_SAVING_EVENT, true);
	        clientFactory.getEventBus().fireEvent(startEvent);
			observatoryService.addObservatory(observatoryDTO, new AsyncCallback<Long>() {
				
				@Override
				public void onSuccess(Long result) {
					previousHash = observatoryDTO.getHash();
					ActionEndEvent e = new ActionEndEvent(ActionEventConstant.OBSERVATORY_SAVING_EVENT);
			        clientFactory.getEventBus().fireEvent(e);
			        broadcastActivityTitle(getMessage().observatoryEditingViewModificationHeader());
			        List<Shortcut> shortcuts = new ArrayList<Shortcut>();
			        shortcuts.add(ShortcutFactory.getWelcomeShortcut());
			        shortcuts.add(ShortcutFactory.getObservatoryManagementShortcut());
			        observatoryId = result;
			        shortcuts.add(ShortcutFactory.getObservatoryModificationShortcut(observatoryDTO.getShortLabel(), observatoryId));
			        clientFactory.getBreadCrumb().refresh(shortcuts);
			        mode = Constants.MODIFY;
			        view.setMode(Constants.MODIFY);
			        
				}
				
				@Override
				public void onFailure(Throwable caught) {
					DialogBoxTools.modalAlert(Message.INSTANCE.error(), Message.INSTANCE.anErrorHasHappened()+" "+caught.getMessage());
					ActionEndEvent e = new ActionEndEvent(ActionEventConstant.OBSERVATORY_SAVING_EVENT);
			        clientFactory.getEventBus().fireEvent(e);					
				}
			});
		}
		
		
		
	}

	@Override
	public void deleteDrainageBasin(final Long id) 
	{
		observatoryService.deleteDrainageBasin(id, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				ObservatoryEditingView aux = (ObservatoryEditingView) view;
				aux.broadcastDrainageBasinDeletion(id, true);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				ObservatoryEditingView aux = (ObservatoryEditingView) view;
				aux.broadcastDrainageBasinDeletion(id, false);				
			}
		});
			
	}

	@Override
	public void deletePerson(final Long id) {
		
		observatoryService.deletePerson(id, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				ObservatoryEditingView aux = (ObservatoryEditingView) view;
				aux.broadcastPersonDeletion(id, true);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				ObservatoryEditingView aux = (ObservatoryEditingView) view;
				aux.broadcastPersonDeletion(id, false);				
			}
		});
		
	}

	@Override
	public void back() {
		clientFactory.getPlaceController().goTo(new ObservatoryManagementPlace());			
	}

}
