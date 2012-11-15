package fr.obsmip.sedoo.client.ui.tabs;

import com.google.gwt.user.client.ui.Composite;

import fr.obsmip.sedoo.client.GlobalBundle;

public class AbstractTab extends Composite{

		
		public AbstractTab() 
		{
			super();
			GlobalBundle.INSTANCE.css().ensureInjected();
		}

}
