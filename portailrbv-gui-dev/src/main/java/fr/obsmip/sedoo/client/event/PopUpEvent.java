package fr.obsmip.sedoo.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class PopUpEvent extends GwtEvent<PopUpEventHandler>{

	public static final Type<PopUpEventHandler> TYPE = new Type<PopUpEventHandler>();
	

	public PopUpEvent()
	{
		
	}

	@Override
	protected void dispatch(PopUpEventHandler handler) {
		handler.onNotification(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<PopUpEventHandler> getAssociatedType() {
		return TYPE;
	}


}
