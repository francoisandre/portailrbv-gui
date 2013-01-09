package fr.obsmip.sedoo.client.activity;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.obsmip.sedoo.client.ClientFactory;
import fr.obsmip.sedoo.client.ShortcutFactory;
import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.place.SystemPlace;
import fr.obsmip.sedoo.client.service.SystemService;
import fr.obsmip.sedoo.client.service.SystemServiceAsync;
import fr.obsmip.sedoo.client.ui.SystemView;
import fr.obsmip.sedoo.client.ui.misc.DialogBoxTools;
import fr.obsmip.sedoo.client.ui.misc.Shortcut;

public class SystemActivity extends RBVAbstractActivity {
	
	private final SystemServiceAsync systemService = GWT.create(SystemService.class);

	public SystemActivity(SystemPlace place, ClientFactory clientFactory) {
		super(clientFactory);
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		final SystemView versionView = clientFactory.getSystemView();
		versionView.setApplicationVersion("En cours de recherche...");
		containerWidget.setWidget(versionView.asWidget());
		broadcastActivityTitle(Message.INSTANCE.systemViewHeader());
		List<Shortcut> shortcuts = new ArrayList<Shortcut>();
		shortcuts.add(ShortcutFactory.getWelcomeShortcut());
		shortcuts.add(ShortcutFactory.getSystemShortcut());
		clientFactory.getBreadCrumb().refresh(shortcuts);
		systemService.getApplicationVersion(new AsyncCallback<String>() {

			@Override
			public void onSuccess(String version) {
				versionView.setApplicationVersion(version);
			}

			@Override
			public void onFailure(Throwable caught) {
				DialogBoxTools.modalAlert(Message.INSTANCE.error(), Message.INSTANCE.anErrorHasHappened()+" : "+caught.getMessage());
			}
		});
		
		systemService.getJavaVersion(new AsyncCallback<String>() {

			@Override
			public void onSuccess(String javaVersion) {
				versionView.setJavaVersion(javaVersion);
			}

			@Override
			public void onFailure(Throwable caught) {
				DialogBoxTools.modalAlert(Message.INSTANCE.error(), Message.INSTANCE.anErrorHasHappened()+" : "+caught.getMessage());
			}
		});
	}
}
