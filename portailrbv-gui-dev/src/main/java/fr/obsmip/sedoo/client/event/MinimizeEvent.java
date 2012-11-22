package fr.obsmip.sedoo.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class MinimizeEvent extends GwtEvent<MinimizeEventHandler>{

	public static final Type<MinimizeEventHandler> TYPE = new Type<MinimizeEventHandler>();
	

	public MinimizeEvent()
	{
		
	}

	@Override
	protected void dispatch(MinimizeEventHandler handler) {
		handler.onNotification(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<MinimizeEventHandler> getAssociatedType() {
		return TYPE;
	}


}
