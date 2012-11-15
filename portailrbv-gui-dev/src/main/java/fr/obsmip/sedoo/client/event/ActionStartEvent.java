package fr.obsmip.sedoo.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ActionStartEvent extends GwtEvent<ActionStartEventHandler>{

	public static final Type<ActionStartEventHandler> TYPE = new Type<ActionStartEventHandler>();
	private String message;
	private int code;
	private boolean displayLoadingImage;


	public ActionStartEvent(String message, int code, boolean displayLoadingImage)
	{
		this.message = message;
		this.code = code;
		this.displayLoadingImage = displayLoadingImage;
	}

	@Override
	protected void dispatch(ActionStartEventHandler handler) {
		handler.onNotification(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ActionStartEventHandler> getAssociatedType() {
		return TYPE;
	}



	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public boolean isDisplayLoadingImage() {
		return displayLoadingImage;
	}




}
