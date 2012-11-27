package fr.obsmip.sedoo.client.activity;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.obsmip.sedoo.client.ClientFactory;
import fr.obsmip.sedoo.client.Constants;
import fr.obsmip.sedoo.client.domain.DrainageBasinDTO;
import fr.obsmip.sedoo.client.domain.ThesaurusItemDTO;
import fr.obsmip.sedoo.client.domain.ValidationAlert;
import fr.obsmip.sedoo.client.event.ActionEndEvent;
import fr.obsmip.sedoo.client.event.ActionEventConstant;
import fr.obsmip.sedoo.client.event.ActionStartEvent;
import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.place.DrainageBasinEditingPlace;
import fr.obsmip.sedoo.client.service.ObservatoryService;
import fr.obsmip.sedoo.client.service.ObservatoryServiceAsync;
import fr.obsmip.sedoo.client.service.ThesaurusService;
import fr.obsmip.sedoo.client.service.ThesaurusServiceAsync;
import fr.obsmip.sedoo.client.ui.DrainageBasinEditingView;
import fr.obsmip.sedoo.client.ui.DrainageBasinEditingView.Presenter;
import fr.obsmip.sedoo.client.ui.ObservatoryEditingView;
import fr.obsmip.sedoo.client.ui.misc.DialogBoxTools;

public class DrainageBasinEditingActivity extends AbstractDTOEditingActivity implements Presenter{

	private Long id;
	static List<ThesaurusItemDTO> climateThesaurus;
	static List<ThesaurusItemDTO> lithologyThesaurus;	
//	private DrainageBasinEditingView drainageBasinEditingView;

	public DrainageBasinEditingActivity(DrainageBasinEditingPlace place, ClientFactory clientFactory) {
		super(clientFactory);
		
		if (place.getId() != null)
		{
			id = place.getId();
		}
		if (place.getMode() != null)
		{
			setMode(place.getMode());
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
					previousHash = result.getHash();
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
			broadcastActivityTitle(Message.INSTANCE.drainageBasinEditingViewCreationHeader());
			drainageBasinEditingView.edit(new DrainageBasinDTO());
			view.setMode(Constants.CREATE);
		}
	}


	
	
	

	@Override
	public void save(final DrainageBasinDTO drainageBasinDTO) 
	{
		List<ValidationAlert> validate = drainageBasinDTO.validate();
		if (validate.isEmpty() == false)
		{
			DialogBoxTools.popUp(Message.INSTANCE.error(), "Erreurs...", DialogBoxTools.TEXT_MODE);
			return;
		}
		
		//Modification mode
		if (getMode().compareTo(Constants.MODIFY) == 0)
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
		        view.setMode(Constants.MODIFY);
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





}
