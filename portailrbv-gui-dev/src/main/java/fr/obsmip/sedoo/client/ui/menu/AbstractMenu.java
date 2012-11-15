package fr.obsmip.sedoo.client.ui.menu;

import com.google.gwt.user.client.ui.Composite;

import fr.obsmip.sedoo.client.PortailRBV;
import fr.obsmip.sedoo.client.mvp.Presenter;

public class AbstractMenu extends Composite{
	
	protected Presenter presenter;
	
	protected Presenter getPresenter()
	{
		if (presenter == null)
		{
			presenter = PortailRBV.getPresenter();
		}
		return presenter;
	}

}
