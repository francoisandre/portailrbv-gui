package fr.obsmip.sedoo.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class UserLogoutEvent extends GwtEvent<UserLogoutEventHandler>{

	public static final Type<UserLogoutEventHandler> TYPE = new Type<UserLogoutEventHandler>();
	

	public UserLogoutEvent()
	{
		
	}

	@Override
	protected void dispatch(UserLogoutEventHandler handler) {
		handler.onNotification(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<UserLogoutEventHandler> getAssociatedType() {
		return TYPE;
	}


}
