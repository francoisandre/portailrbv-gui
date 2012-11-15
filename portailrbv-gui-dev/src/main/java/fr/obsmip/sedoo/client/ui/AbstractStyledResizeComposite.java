package fr.obsmip.sedoo.client.ui;

import com.google.gwt.user.client.ui.ResizeComposite;

import fr.obsmip.sedoo.client.GlobalBundle;

public class AbstractStyledResizeComposite extends ResizeComposite{
	
	public AbstractStyledResizeComposite() {
		GlobalBundle.INSTANCE.css().ensureInjected();
	}
	
	protected  void applyCommonStyle()
	{
		getElement().getStyle().setProperty("backgroundColor", "#ffffff");
	}

}
