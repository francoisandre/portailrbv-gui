package fr.obsmip.sedoo.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ActionEndEvent extends GwtEvent<ActionEndEventHandler>{

	public static final Type<ActionEndEventHandler> TYPE = new Type<ActionEndEventHandler>();
	private int code;


	public ActionEndEvent(int code)
	{
		this.code = code;
	}

	@Override
	protected void dispatch(ActionEndEventHandler handler) {
		handler.onNotification(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ActionEndEventHandler> getAssociatedType() {
		return TYPE;
	}


	public int getCode() {
		return code;
	}


}
