package fr.obsmip.sedoo.client.activity;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.obsmip.sedoo.client.ClientFactory;
import fr.obsmip.sedoo.client.place.SystemPlace;
import fr.obsmip.sedoo.client.service.VersionService;
import fr.obsmip.sedoo.client.service.VersionServiceAsync;
import fr.obsmip.sedoo.client.ui.SystemView;

public class SystemActivity extends RBVAbstractActivity {
	
	private final VersionServiceAsync versionService = GWT.create(VersionService.class);

	public SystemActivity(SystemPlace place, ClientFactory clientFactory) {
		super(clientFactory);
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		final SystemView versionView = clientFactory.getSystemView();
		versionView.setVersion("En cours de recherche...");
		containerWidget.setWidget(versionView.asWidget());
		broadcastActivityTitle(getMessage().systemViewHeader());
		versionService.getVersion(new AsyncCallback<String>() {

			@Override
			public void onSuccess(String version) {
				versionView.setVersion(version);
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO onFailure à coder ..
			}
		});
	}
}
