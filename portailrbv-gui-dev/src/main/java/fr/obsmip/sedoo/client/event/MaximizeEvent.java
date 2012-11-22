package fr.obsmip.sedoo.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class MaximizeEvent extends GwtEvent<MaximizeEventHandler>{

	public static final Type<MaximizeEventHandler> TYPE = new Type<MaximizeEventHandler>();
	

	public MaximizeEvent()
	{
		
	}

	@Override
	protected void dispatch(MaximizeEventHandler handler) {
		handler.onNotification(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<MaximizeEventHandler> getAssociatedType() {
		return TYPE;
	}


}
