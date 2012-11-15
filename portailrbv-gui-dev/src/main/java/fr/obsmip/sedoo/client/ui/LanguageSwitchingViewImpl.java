package fr.obsmip.sedoo.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

import fr.obsmip.sedoo.client.GlobalBundle;

public class LanguageSwitchingViewImpl extends AbstractSection implements LanguageSwitchingView {

	private static LanguageSwitchingViewImplUiBinder uiBinder = GWT
			.create(LanguageSwitchingViewImplUiBinder.class);

	interface LanguageSwitchingViewImplUiBinder extends UiBinder<Widget, LanguageSwitchingViewImpl> {
	}

	public LanguageSwitchingViewImpl() {
		GWT.<GlobalBundle>create(GlobalBundle.class).css().ensureInjected();
		initWidget(uiBinder.createAndBindUi(this));
		applyCommonStyle();
	}

}
