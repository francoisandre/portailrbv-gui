package fr.obsmip.sedoo.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Evènement destiné au notification mole
 * @author francois
 *
 */
public class NotificationEvent extends GwtEvent<NotificationHandler>{
	
	public static final Type<NotificationHandler> TYPE = new Type<NotificationHandler>();

	private String message;
	
	public NotificationEvent(String message)
	{
		this.setMessage(message);
	}
	
	 @Override
	    protected void dispatch(NotificationHandler handler) {
	        handler.onNotification(this);
	    }

	    @Override
	    public com.google.gwt.event.shared.GwtEvent.Type<NotificationHandler> getAssociatedType() {
	        return TYPE;
	    }

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		


}
