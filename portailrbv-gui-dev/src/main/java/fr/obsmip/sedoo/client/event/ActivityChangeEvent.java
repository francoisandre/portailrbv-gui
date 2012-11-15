package fr.obsmip.sedoo.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ActivityChangeEvent extends GwtEvent<ActivityChangeEventHandler>{

	public static final Type<ActivityChangeEventHandler> TYPE = new Type<ActivityChangeEventHandler>();
	
	private String activityTitle;

	public ActivityChangeEvent(String activityTitle)
	{
		this.activityTitle = activityTitle;
	}

	@Override
	protected void dispatch(ActivityChangeEventHandler handler) {
		handler.onNotification(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ActivityChangeEventHandler> getAssociatedType() {
		return TYPE;
	}

	public String getActivityTitle() {
		return activityTitle;
	}

	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}



}
