package fr.obsmip.sedoo.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;

import fr.obsmip.sedoo.client.ClientFactory;
import fr.obsmip.sedoo.client.event.ActivityChangeEvent;
import fr.obsmip.sedoo.client.message.Message;

public abstract class RBVAbstractActivity extends AbstractActivity {
	
	protected ClientFactory clientFactory;
	
	public RBVAbstractActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	protected void broadcastActivityTitle(String title)
	{
		clientFactory.getEventBus().fireEvent(new ActivityChangeEvent(title));
	}
	
	

}
