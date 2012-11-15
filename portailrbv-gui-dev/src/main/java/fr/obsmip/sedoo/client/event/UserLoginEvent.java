package fr.obsmip.sedoo.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class UserLoginEvent extends GwtEvent<UserLoginEventHandler>{

	public static final Type<UserLoginEventHandler> TYPE = new Type<UserLoginEventHandler>();
	

	public UserLoginEvent()
	{
		
	}

	@Override
	protected void dispatch(UserLoginEventHandler handler) {
		handler.onNotification(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<UserLoginEventHandler> getAssociatedType() {
		return TYPE;
	}


}
