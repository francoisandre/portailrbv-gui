package fr.obsmip.sedoo.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;

import fr.obsmip.sedoo.client.GlobalBundle;
import fr.obsmip.sedoo.client.PortailRBV;
import fr.obsmip.sedoo.client.domain.UserDTO;
import fr.obsmip.sedoo.client.event.ActionEndEvent;
import fr.obsmip.sedoo.client.event.ActionEventConstant;
import fr.obsmip.sedoo.client.event.ActionStartEvent;
import fr.obsmip.sedoo.client.event.UserLoginEvent;

public class DebugStatusBarViewImpl extends AbstractStyledResizeComposite implements StatusBarView {

	@UiField
	StatusBarView statusBarView;
	
	@UiField
	Button connectAdminButton;
	
	private static DebugStatusBarViewImplUiBinder uiBinder = GWT
			.create(DebugStatusBarViewImplUiBinder.class);
	
	private EventBus eventBus;

	interface DebugStatusBarViewImplUiBinder extends UiBinder<Widget, DebugStatusBarViewImpl> {
	}

	public DebugStatusBarViewImpl(EventBus eventBus) {
		super();
		this.eventBus = eventBus;
		initWidget(uiBinder.createAndBindUi(this));
		applyCommonStyle();
	}

	@Override
	public void onNotification(ActionEndEvent event) {
		statusBarView.onNotification(event);
	}

	@Override
	public void onNotification(ActionStartEvent event) {
		statusBarView.onNotification(event);
		
	}

	@Override
	public void setVersion(String version) {
		statusBarView.setVersion(version);
		
	}
	
	@Override
	public double getHeight() {
		return 70;
	}
	
	 @UiHandler("connectAdminButton")
	 void onSignInClicked(ClickEvent event) 
	 {
		 PortailRBV.logout();
		 UserDTO admin = new UserDTO();
		 admin.setFirstName("admin");
		 admin.setLastName("admin");
		 admin.setAdmin(true);
		 PortailRBV.setUser(admin);
		 UserLoginEvent e = new UserLoginEvent();
	     eventBus.fireEvent(e);
	 }
	
}


	
