package fr.obsmip.sedoo.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;

import fr.obsmip.sedoo.client.GlobalBundle;

public class WelcomeViewImpl extends AbstractSection implements WelcomeView {

	private static WelcomeViewImplUiBinder uiBinder = GWT
			.create(WelcomeViewImplUiBinder.class);

	interface WelcomeViewImplUiBinder extends UiBinder<Widget, WelcomeViewImpl> {
	}

	public WelcomeViewImpl() {
		super();
		GWT.<GlobalBundle>create(GlobalBundle.class).css().ensureInjected();
		initWidget(uiBinder.createAndBindUi(this));
		applyCommonStyle();
	}

}
