package fr.obsmip.sedoo.client.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
import fr.obsmip.sedoo.client.domain.IdentifiedString;
import fr.obsmip.sedoo.client.domain.MetadataContactDTO;
import fr.obsmip.sedoo.client.domain.ObservatoryContactDTO;
import fr.obsmip.sedoo.client.domain.ObservatoryDTO;
import fr.obsmip.sedoo.client.domain.metadata.MetadataDTO;
import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.misc.DateTools;
import fr.obsmip.sedoo.client.place.MetadataEditingPlace;
import fr.obsmip.sedoo.client.service.MetadataService;
import fr.obsmip.sedoo.client.service.MetadataServiceAsync;
import fr.obsmip.sedoo.client.service.ObservatoryService;
import fr.obsmip.sedoo.client.service.ObservatoryServiceAsync;
import fr.obsmip.sedoo.client.ui.MetadataEditingView;
import fr.obsmip.sedoo.client.ui.misc.DialogBoxTools;
import fr.obsmip.sedoo.client.ui.misc.Shortcut;
import fr.obsmip.sedoo.client.ui.table.MetadataContactSelectionTable;

public class MetadataEditingActivity extends AbstractDTOEditingActivity implements MetadataEditingView.Presenter {

	private final MetadataServiceAsync metadataService = GWT.create(MetadataService.class);
	private final ObservatoryServiceAsync observatoryService = GWT.create(ObservatoryService.class);

	private String metadataUuid;
	private Long drainageBassinId;
	private DrainageBasinDTO drainageBasinDTO;
	private ObservatoryDTO observatoryDTO;

	public MetadataEditingActivity(MetadataEditingPlace place, ClientFactory clientFactory) {

		super(clientFactory);
		metadataUuid = place.getMetadataUuid();
		drainageBassinId = place.getDrainageBasinId();
		setMode(place.getMode());
		
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		view = clientFactory.getMetadataEditingView();
		containerWidget.setWidget(view.asWidget());
		if (isCreateMode())
		{
			broadcastActivityTitle(Message.INSTANCE.metadataCreatingTitle());
		}
		else
		{
			broadcastActivityTitle(Message.INSTANCE.metadataEditingTitle());
		}
		((MetadataEditingView) view).setPresenter(this);
		
		
		
		//On charge les infos du DrainageBasin
		observatoryService.getDrainageBasinById(drainageBassinId, new AsyncCallback<DrainageBasinDTO>() {

			@Override
			public void onSuccess(DrainageBasinDTO result) {
				drainageBasinDTO = result;
				
				broadcastActivityTitle(Message.INSTANCE.metadataCreatingTitle() +" ("+drainageBasinDTO.getLabel()+")");
				List<Shortcut> shortcuts = new ArrayList<Shortcut>();
				shortcuts.add(ShortcutFactory.getWelcomeShortcut());
				shortcuts.add(ShortcutFactory.getMetadataEditingShortcut(drainageBasinDTO.getLabel()));
				clientFactory.getBreadCrumb().refresh(shortcuts);
				
				// On charge les infos de l'observatoire
				observatoryService.getObservatoryByDrainageBasinId(drainageBassinId, new AsyncCallback<ObservatoryDTO>() {

					@Override
					public void onSuccess(ObservatoryDTO result) {
						observatoryDTO = result;
						
						//On charge la fiche de métadonnées
						
						if (getMode().compareTo(Constants.MODIFY) == 0)
						{
							metadataService.getMetadataByUuid(metadataUuid, new AsyncCallback<MetadataDTO>() {

								@Override
								public void onSuccess(MetadataDTO result) {
									broadcastActivityTitle(Message.INSTANCE.drainageBasinEditingViewModificationHeader() +" ("+result.getIdentificationPart().getResourceTitle()+")");
									previousHash = result.getHash();
									view.setMode(Constants.MODIFY);
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
							previousHash="";
							view.setMode(Constants.CREATE);
							((MetadataEditingView) view).edit(createNewMetadata());
						}

						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						DialogBoxTools.modalAlert(Message.INSTANCE.error(), Message.INSTANCE.anErrorHasHappened()+" : "+caught.getMessage());

					}
				});
				
			}

			@Override
			public void onFailure(Throwable caught) {
				DialogBoxTools.modalAlert(Message.INSTANCE.error(), Message.INSTANCE.anErrorHasHappened()+" : "+caught.getMessage());

			}
		});
		
	}

	private String generatedXML="";


	private MetadataDTO createNewMetadata()
	{
		MetadataDTO aux = new MetadataDTO();
		aux.getTemporalExtentPart().setEndDate("now");
		aux.getTemporalExtentPart().setStartDate("1975-01-24");
		aux.getIdentificationPart().setResourceAbstract("mon resumé");
		aux.getMetadataPart().setMetadataLastModificationDate(DateTools.getRBVDateFormat().format(new Date()));
		String loc = LocaleInfo.getCurrentLocale().getLocaleName();
		if (loc.compareToIgnoreCase(Constants.FRENCH_LOCALE)==0)
		{
			aux.getMetadataPart().setMetadataLanguage(Constants.FRENCH);
		}
		else
		{
			aux.getMetadataPart().setMetadataLanguage(Constants.ENGLISH);
		}
		
		IdentifiedString url1 = new IdentifiedString();
		url1.setId(1L);
		url1.setValue("http://www.cnrs.fr");
		
		IdentifiedString url2 = new IdentifiedString();
		url2.setId(2L);
		url2.setValue("http://www.ird.fr");
		
		aux.getIdentificationPart().getResourceURL().add(url1);
		aux.getIdentificationPart().getResourceURL().add(url2);
		
		
		MetadataContactDTO fakeContact = new MetadataContactDTO();
		fakeContact.setId(1L);
		fakeContact.setPersonName("Jean breille");
		fakeContact.setOrganisationName("CNRS/INSU");
		fakeContact.setRoles("XXX xxx|YYYY|ZZZ");
		fakeContact.setEmail("jeanbreille@insu.cnrs.dir.fr");
//		aux.getMetadataPart().getMetadataContacts().add(fakeContact);
		aux.getIdentificationPart().getResourceContacts().add(fakeContact);
		if (!observatoryDTO.isEmpty(observatoryDTO.getPublicAccessLimitations()))
		{
			aux.getConstraintPart().setPublicAccessLimitations(observatoryDTO.getPublicAccessLimitations().trim());
		}
		if (!observatoryDTO.isEmpty(observatoryDTO.getUseConditions()))
		{
			aux.getConstraintPart().setUseConditions(observatoryDTO.getUseConditions().trim());
		}
		
		return aux; 
	}

	@Override
	public void generateXML(MetadataDTO metadata) {
		// TODO Auto-generated method stub
		metadataService.toXML(metadata, (new AsyncCallback<String>() {

			@Override
			public void onSuccess(String xml) {
				((MetadataEditingView) view).setGeneratedXML(xml);
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		}));



		/*MetadataRequest metadataRequest = clientFactory.getRbvRequestFactory().metadataRequest();
		MetadataProxy proxy = metadataRequest.create(MetadataProxy.class);
		proxy.setResourceTitle("mon titre");
		proxy.setResourceAbstract("mon resumé");
		Request<String> xmlRequest = metadataRequest.toXML(metadata);

		xmlRequest.fire( new Receiver<String>() {
		      @Override
		      public void onSuccess(String result) {

		    	  setGeneratedXML(result);
		      }
		    });

		return getGeneratedXML();*/
	}

	public String getGeneratedXML() {
		return generatedXML;
	}

	public void setGeneratedXML(String generatedXML) {
		this.generatedXML = generatedXML;
	}

	@Override
	public void getObservatoryContacts(final MetadataContactSelectionTable table) 
	{
		observatoryService.getObservatoryContactsByDrainageBasinId(drainageBassinId, new AsyncCallback<List<ObservatoryContactDTO>>() {

			@Override
			public void onSuccess(List<ObservatoryContactDTO> result) 
			{
				List<MetadataContactDTO> aux = new ArrayList<MetadataContactDTO>();
				if (result != null)
				{
					Long index = 1L;
					Iterator<ObservatoryContactDTO> iterator = result.iterator();
					while (iterator.hasNext()) {
						ObservatoryContactDTO current = (ObservatoryContactDTO) iterator.next();
						MetadataContactDTO contact = new MetadataContactDTO();
						contact.setId(index);
						contact.setEmail(current.getEmail());
						contact.setOrganisationName(current.getOrganisationName());
						contact.setRoles(current.getRoles());
						contact.setPersonName(current.getPersonName());
						aux.add(contact);
						index++;
					}
					
				}
				table.init(aux);
			}

			@Override
			public void onFailure(Throwable caught) {
				DialogBoxTools.modalAlert(Message.INSTANCE.error(), Message.INSTANCE.anErrorHasHappened()+" : "+caught.getMessage());

			}
		});
	}

}
