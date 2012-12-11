package fr.obsmip.sedoo.client.activity;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.obsmip.sedoo.client.ClientFactory;
import fr.obsmip.sedoo.client.Constants;
import fr.obsmip.sedoo.client.ShortcutFactory;
import fr.obsmip.sedoo.client.domain.DrainageBasinDTO;
import fr.obsmip.sedoo.client.domain.ObservatoryContactDTO;
import fr.obsmip.sedoo.client.domain.ObservatoryDTO;
import fr.obsmip.sedoo.client.domain.ThesaurusItemDTO;
import fr.obsmip.sedoo.client.domain.ValidationAlert;
import fr.obsmip.sedoo.client.event.ActionEndEvent;
import fr.obsmip.sedoo.client.event.ActionEventConstant;
import fr.obsmip.sedoo.client.event.ActionStartEvent;
import fr.obsmip.sedoo.client.event.NotificationEvent;
import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.place.DrainageBasinEditingPlace;
import fr.obsmip.sedoo.client.place.ObservatoryEditingPlace;
import fr.obsmip.sedoo.client.service.ObservatoryService;
import fr.obsmip.sedoo.client.service.ObservatoryServiceAsync;
import fr.obsmip.sedoo.client.service.ThesaurusService;
import fr.obsmip.sedoo.client.service.ThesaurusServiceAsync;
import fr.obsmip.sedoo.client.ui.DrainageBasinEditingView;
import fr.obsmip.sedoo.client.ui.DrainageBasinEditingView.Presenter;
import fr.obsmip.sedoo.client.ui.ObservatoryEditingView;
import fr.obsmip.sedoo.client.ui.misc.DialogBoxTools;
import fr.obsmip.sedoo.client.ui.misc.Shortcut;

public class DrainageBasinEditingActivity extends AbstractDTOEditingActivity implements Presenter{

	private Long id;
	private Long observatoryId;
	private String observatoryShortLabel="";
	static List<ThesaurusItemDTO> climateThesaurus;
	static List<ThesaurusItemDTO> lithologyThesaurus;	
//	private DrainageBasinEditingView drainageBasinEditingView;

	public DrainageBasinEditingActivity(DrainageBasinEditingPlace place, ClientFactory clientFactory) {
		super(clientFactory);
		setMode(place.getMode());
		if (place.getMode().compareTo(Constants.CREATE)==0)
		{
			setObservatoryId(place.getId());
		}
		else
		{
			id = place.getId();
		}
	}


	private final ObservatoryServiceAsync observatoryService = GWT.create(ObservatoryService.class);


	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		final DrainageBasinEditingView drainageBasinEditingView = clientFactory.getDrainageBasinEditingView();
		view = drainageBasinEditingView;
		drainageBasinEditingView.setPresenter(this);
		containerWidget.setWidget(drainageBasinEditingView.asWidget());
		
		
		if (climateThesaurus == null)
		{
			ThesaurusServiceAsync thesaurusService = GWT.create(ThesaurusService.class);
			thesaurusService.getClimateThesaurus(LocaleInfo.getCurrentLocale().getLocaleName(), new AsyncCallback<List<ThesaurusItemDTO>>() {
				
				@Override
				public void onSuccess(List<ThesaurusItemDTO> result) {
					drainageBasinEditingView.updateClimateList(result);
					
				}
				
				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}
			});
					
		}
		else
		{
			drainageBasinEditingView.updateClimateList(climateThesaurus);
		}
		
		if (lithologyThesaurus == null)
		{
			ThesaurusServiceAsync thesaurusService = GWT.create(ThesaurusService.class);
			thesaurusService.getLithologyThesaurus(LocaleInfo.getCurrentLocale().getLocaleName(), new AsyncCallback<List<ThesaurusItemDTO>>() {
				
				@Override
				public void onSuccess(List<ThesaurusItemDTO> result) {
					drainageBasinEditingView.updateLithologyList(result);
					
				}
				
				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}
			});
					
		}
		else
		{
			drainageBasinEditingView.updateLithologyList(lithologyThesaurus);
		}
		
		
		if (getMode().compareTo(Constants.MODIFY) == 0)
		{
			observatoryService.getDrainageBasinById(id, new AsyncCallback<DrainageBasinDTO>() {

				@Override
				public void onSuccess(DrainageBasinDTO result) {
					broadcastActivityTitle(Message.INSTANCE.drainageBasinEditingViewModificationHeader() +" ("+result.getLabel()+")");
					setObservatoryId(result.getObservatoryId());
					previousHash = result.getHash();
					List<Shortcut> shortcuts = new ArrayList<Shortcut>();
					shortcuts.add(ShortcutFactory.getWelcomeShortcut());
			        shortcuts.add(ShortcutFactory.getObservatoryManagementShortcut());
			        observatoryShortLabel = result.getObservatoryShortLabel();
			        shortcuts.add(ShortcutFactory.getObservatoryModificationShortcut(observatoryShortLabel, observatoryId));
			        shortcuts.add(ShortcutFactory.getDrainageBasinModificationShortcut(result.getLabel(), id));
			        clientFactory.getBreadCrumb().refresh(shortcuts);
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
			drainageBasinEditingView.edit(new DrainageBasinDTO());
			observatoryService.getObservatoryById(observatoryId, new AsyncCallback<ObservatoryDTO>() {
				
				@Override
				public void onSuccess(ObservatoryDTO result) {
					List<Shortcut> shortcuts = new ArrayList<Shortcut>();
					shortcuts.add(ShortcutFactory.getWelcomeShortcut());
			        shortcuts.add(ShortcutFactory.getObservatoryManagementShortcut());
			        observatoryShortLabel = result.getShortLabel();
			        shortcuts.add(ShortcutFactory.getObservatoryModificationShortcut(observatoryShortLabel, observatoryId));
			        shortcuts.add(ShortcutFactory.getObservatoryDrainageBasinCreationShortcut(id));
			        clientFactory.getBreadCrumb().refresh(shortcuts);		
				}
				
				@Override
				public void onFailure(Throwable caught) {
					// Nothing done ...
					
				}
			});
	        
			view.setMode(Constants.CREATE);
			
			
		}
	}


	@Override
	public void save(final DrainageBasinDTO drainageBasinDTO) 
	{
		List<ValidationAlert> validate = drainageBasinDTO.validate();
		if (validate.isEmpty() == false)
		{
			DialogBoxTools.popUp(Message.INSTANCE.error(), ValidationAlert.toHTML(validate), DialogBoxTools.HTML_MODE);
			return;
		}
		
		//Modification mode
		if (getMode().compareTo(Constants.MODIFY) == 0)
		{
			drainageBasinDTO.setId(id);
			ActionStartEvent startEvent = new ActionStartEvent(Message.INSTANCE.saving(), ActionEventConstant.DRAINAGE_BASIN_SAVING_EVENT, true);
	        clientFactory.getEventBus().fireEvent(startEvent);
	       
	        observatoryService.saveDrainageBasin(drainageBasinDTO, new AsyncCallback<Void>() {

	        	@Override
	        	public void onSuccess(Void result) 
	        	{
	        		previousHash = drainageBasinDTO.getHash();
	        		ActionEndEvent e = new ActionEndEvent(ActionEventConstant.DRAINAGE_BASIN_SAVING_EVENT);
	        		clientFactory.getEventBus().fireEvent(e);
	        		broadcastActivityTitle(Message.INSTANCE.observatoryContactEditingViewModificationHeader() +" ("+drainageBasinDTO.getLabel()+")");
	        		view.setMode(Constants.MODIFY);
	        		clientFactory.getEventBus().fireEvent(new NotificationEvent(Message.INSTANCE.savedModifications()));
	        	}

	        	@Override
	        	public void onFailure(Throwable caught) {

	        		DialogBoxTools.modalAlert(Message.INSTANCE.error(), Message.INSTANCE.anErrorHasHappened()+" "+caught.getMessage());
	        		ActionEndEvent e = new ActionEndEvent(ActionEventConstant.DRAINAGE_BASIN_SAVING_EVENT);
	        		clientFactory.getEventBus().fireEvent(e);

	        	}
	        });
		}
		else
		{
			ActionStartEvent startEvent = new ActionStartEvent(Message.INSTANCE.saving(), ActionEventConstant.DRAINAGE_BASIN_SAVING_EVENT, true);
			clientFactory.getEventBus().fireEvent(startEvent);
			observatoryService.addDrainageBasin(drainageBasinDTO, observatoryId, new AsyncCallback<Long>() {

				@Override
				public void onSuccess(Long result) 
				{
					previousHash = drainageBasinDTO.getHash();
					ActionEndEvent e = new ActionEndEvent(ActionEventConstant.DRAINAGE_BASIN_SAVING_EVENT);
					clientFactory.getEventBus().fireEvent(e);
					broadcastActivityTitle(Message.INSTANCE.drainageBasinEditingViewModificationHeader() +" ("+drainageBasinDTO.getLabel()+")");
					view.setMode(Constants.MODIFY);
					id = result;
					List<Shortcut> shortcuts = new ArrayList<Shortcut>();
					shortcuts.add(ShortcutFactory.getWelcomeShortcut());
			        shortcuts.add(ShortcutFactory.getObservatoryManagementShortcut());
			        shortcuts.add(ShortcutFactory.getObservatoryModificationShortcut(observatoryShortLabel, observatoryId));
			        shortcuts.add(ShortcutFactory.getDrainageBasinModificationShortcut(drainageBasinDTO.getLabel(), id));
			        clientFactory.getBreadCrumb().refresh(shortcuts);	
			        clientFactory.getEventBus().fireEvent(new NotificationEvent(Message.INSTANCE.addedElement()));
				}

				@Override
				public void onFailure(Throwable caught) {

					DialogBoxTools.modalAlert(Message.INSTANCE.error(), Message.INSTANCE.anErrorHasHappened()+" "+caught.getMessage());
					ActionEndEvent e = new ActionEndEvent(ActionEventConstant.DRAINAGE_BASIN_SAVING_EVENT);
					clientFactory.getEventBus().fireEvent(e);

				}
			});
		}
		
	}

	@Override
	public void deleteSite(final Long id) 
	{
		observatoryService.deleteSite(id, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				DrainageBasinEditingView aux = (DrainageBasinEditingView) view;
				aux.broadcastSiteDeletion(id, true);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				DrainageBasinEditingView aux = (DrainageBasinEditingView) view;
				aux.broadcastSiteDeletion(id, false);				
			}
		});
			
	}

	@Override
	public void back() {
		ObservatoryEditingPlace place = new ObservatoryEditingPlace();
		place.setObservatoryId(getObservatoryId());
		
		clientFactory.getPlaceController().goTo(place);	
	}






	public Long getObservatoryId() {
		return observatoryId;
	}






	public void setObservatoryId(Long observatoryId) {
		this.observatoryId = observatoryId;
	}



}
