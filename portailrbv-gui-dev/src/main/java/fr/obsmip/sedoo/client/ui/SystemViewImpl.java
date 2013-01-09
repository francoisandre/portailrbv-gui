package fr.obsmip.sedoo.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

public class SystemViewImpl extends AbstractSection implements SystemView {

	@UiField Element applicationVersion;
	@UiField Element javaVersion;
	
	private static SystemViewImplUiBinder uiBinder = GWT
			.create(SystemViewImplUiBinder.class);

	interface SystemViewImplUiBinder extends UiBinder<Widget, SystemViewImpl> {
	}

	public SystemViewImpl() {
		super();
		initWidget(uiBinder.createAndBindUi(this));
		applyCommonStyle();
	}

	public void setApplicationVersion(String version) {
		this.applicationVersion.setInnerText(version);		
	}
	
	public void setJavaVersion(String javaVersion) {
		this.javaVersion.setInnerText(javaVersion);		
	}

}
