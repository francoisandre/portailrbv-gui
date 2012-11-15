package fr.obsmip.sedoo.client.ui;

import fr.obsmip.sedoo.client.GlobalBundle;

public class AbstractSection extends AbstractStyledResizeComposite{
	
	public AbstractSection() {
		GlobalBundle.INSTANCE.css().ensureInjected();
	}
}
