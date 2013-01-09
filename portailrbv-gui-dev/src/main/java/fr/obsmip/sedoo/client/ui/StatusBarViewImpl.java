package fr.obsmip.sedoo.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

import fr.obsmip.sedoo.client.GlobalBundle;
import fr.obsmip.sedoo.client.event.ActionEndEvent;
import fr.obsmip.sedoo.client.event.ActionStartEvent;
import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.service.SystemService;
import fr.obsmip.sedoo.client.service.SystemServiceAsync;
import fr.obsmip.sedoo.client.ui.misc.DialogBoxTools;

public class StatusBarViewImpl extends AbstractStyledResizeComposite implements StatusBarView {

	private final SystemServiceAsync versionService = GWT.create(SystemService.class);
	
	private final int NO_EVENT = 0;
	
	private int currentDisplayedCode = NO_EVENT;
	
	@UiField Element message;
	
	@UiField Element version;
	
	@UiField Image loading;
	
	private static StatusBarViewImplUiBinder uiBinder = GWT
			.create(StatusBarViewImplUiBinder.class);

	interface StatusBarViewImplUiBinder extends UiBinder<Widget, StatusBarViewImpl> {
	}

	public StatusBarViewImpl() {
		GWT.<GlobalBundle>create(GlobalBundle.class).css().ensureInjected();
		initWidget(uiBinder.createAndBindUi(this));
		applyCommonStyle();
		
		
		versionService.getApplicationVersion(new AsyncCallback<String>() {

			@Override
			public void onSuccess(String applicationVersion) {
				setVersion(applicationVersion);
			}

			@Override
			public void onFailure(Throwable caught) {
				DialogBoxTools.modalAlert(Message.INSTANCE.error(), Message.INSTANCE.anErrorHasHappened()+" : "+caught.getMessage());
			}});
	}

			@Override
	public void setVersion(String version) {
		this.version.setInnerText(version);		
	}

			@Override
			public void onNotification(ActionEndEvent event) {
				
				if (event.getCode() == currentDisplayedCode)
				{
					loading.setVisible(false);
					message.setInnerText(" ");
					currentDisplayedCode = NO_EVENT;
				}
				
			}

			@Override
			public void onNotification(ActionStartEvent event) {
				
				if (event.isDisplayLoadingImage())
				{
					loading.setVisible(true);
				}
				else
				{
					loading.setVisible(false);
				}
				message.setInnerText(event.getMessage());
				currentDisplayedCode = event.getCode();
			}

			@Override
			public double getHeight() {
				return 35;
			}

}


	
