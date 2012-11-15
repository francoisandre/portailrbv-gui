package fr.obsmip.sedoo.client.activity;

import fr.obsmip.sedoo.client.ClientFactory;
import fr.obsmip.sedoo.client.domain.AbstractDTO;
import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.ui.DTOEditingView;

public abstract class AbstractDTOEditingActivity extends RBVAbstractActivity{

	public AbstractDTOEditingActivity(ClientFactory clientFactory) {
		super(clientFactory);
	}

	protected DTOEditingView view;
	protected String previousHash;
	
	public String mayStop() {
	    AbstractDTO current = view.flush();
	    if (previousHash.compareTo(current.getHash()) != 0)
	    {
	    	return Message.INSTANCE.unsavedModificationsConfirmation();
	    }
	    else
	    {
	    	return null;
	    }
	}
}
