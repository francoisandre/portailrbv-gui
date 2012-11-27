package fr.obsmip.sedoo.client.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.obsmip.sedoo.client.ClientFactory;
import fr.obsmip.sedoo.client.Constants;
import fr.obsmip.sedoo.client.ShortcutFactory;
import fr.obsmip.sedoo.client.domain.MetadataContactDTO;
import fr.obsmip.sedoo.client.domain.metadata.MetadataDTO;
import fr.obsmip.sedoo.client.place.MetadataEditingPlace;
import fr.obsmip.sedoo.client.service.MetadataService;
import fr.obsmip.sedoo.client.service.MetadataServiceAsync;
import fr.obsmip.sedoo.client.ui.MetadataEditingView;
import fr.obsmip.sedoo.client.ui.misc.Shortcut;

public class MetadataEditingActivity extends AbstractDTOEditingActivity implements MetadataEditingView.Presenter {

	private final MetadataServiceAsync metadataService = GWT.create(MetadataService.class);

	private String metadataId;

	public MetadataEditingActivity(MetadataEditingPlace place, ClientFactory clientFactory) {

		super(clientFactory);
		if (place.getId() != null)
		{
			this.metadataId = place.getId();
		}
		if ((place.getId() == null) || (place.getId().trim().length()==0))
		{
			setMode(Constants.CREATE);
		}
		else
		{
			setMode(Constants.MODIFY);
		}
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		view = clientFactory.getMetadataEditingView();
		containerWidget.setWidget(view.asWidget());
		broadcastActivityTitle(getMessage().metadataEditingTitle());
		List<Shortcut> shortcuts = new ArrayList<Shortcut>();
		shortcuts.add(ShortcutFactory.getWelcomeShortcut());
		shortcuts.add(ShortcutFactory.getMetadataEditingShortcut());
		clientFactory.getBreadCrumb().refresh(shortcuts);
		((MetadataEditingView) view).setPresenter(this);
		
		if (getMode().compareTo(Constants.MODIFY) == 0)
		{
			//TODO
		}
		else
		{
			((MetadataEditingView) view).edit(createNewMetadata());
		}
	}

	private String generatedXML="";


	private MetadataDTO createNewMetadata()
	{
		MetadataDTO aux = new MetadataDTO();
//		aux.setResourceTitle("mon titre");
//		aux.setResourceAbstract("mon resumé");
		DateTimeFormat fmt = DateTimeFormat.getFormat("yyyy-MM-dd");
		aux.getMetadataPart().setMetadataLastModificationDate(fmt.format(new Date()));
		String loc = LocaleInfo.getCurrentLocale().getLocaleName();
		if (loc.compareToIgnoreCase(Constants.FRENCH_LOCALE)==0)
		{
			aux.getMetadataPart().setMetadataLanguage(Constants.FRENCH);
		}
		else
		{
			aux.getMetadataPart().setMetadataLanguage(Constants.ENGLISH);
		}
		MetadataContactDTO fakeContact = new MetadataContactDTO();
		fakeContact.setId(1L);
		fakeContact.setPersonName("Jean breille");
		fakeContact.setOrganisationName("CNRS/INSU");
		fakeContact.setRoles("XXX xxx|YYYY|ZZZ");
		fakeContact.setEmail("jeanbreille@insu.cnrs.dir.fr");
		aux.getMetadataPart().getMetadataContacts().add(fakeContact);
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

}
