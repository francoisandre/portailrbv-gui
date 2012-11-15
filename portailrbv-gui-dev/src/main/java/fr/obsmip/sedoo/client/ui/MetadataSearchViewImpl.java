package fr.obsmip.sedoo.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

public class MetadataSearchViewImpl extends AbstractSection implements MetadataSearchView {

	private static MetadataSearchViewImplUiBinder uiBinder = GWT
			.create(MetadataSearchViewImplUiBinder.class);

	interface MetadataSearchViewImplUiBinder extends UiBinder<Widget, MetadataSearchViewImpl> {
	}

	public MetadataSearchViewImpl() {
		super();
		initWidget(uiBinder.createAndBindUi(this));
		applyCommonStyle();
	}

}
