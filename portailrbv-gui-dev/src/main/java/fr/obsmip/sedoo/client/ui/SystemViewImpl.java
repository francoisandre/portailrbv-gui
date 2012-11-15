package fr.obsmip.sedoo.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

public class SystemViewImpl extends AbstractSection implements SystemView {

	@UiField Element version;
	
	private static SystemViewImplUiBinder uiBinder = GWT
			.create(SystemViewImplUiBinder.class);

	interface SystemViewImplUiBinder extends UiBinder<Widget, SystemViewImpl> {
	}

	public SystemViewImpl() {
		super();
		initWidget(uiBinder.createAndBindUi(this));
		applyCommonStyle();
	}

	public void setVersion(String version) {
		this.version.setInnerText(version);		
	}

}
