package fr.obsmip.sedoo.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;

import fr.obsmip.sedoo.client.ui.menu.AdministrationMenu;
import fr.obsmip.sedoo.client.ui.menu.MetadataProviderMenu;
import fr.obsmip.sedoo.client.ui.menu.MetadataUserMenu;

public class MenuViewImpl extends AbstractStyledResizeComposite implements MenuView {

	 @UiField AdministrationMenu administrationMenu;
	 @UiField MetadataProviderMenu metadataProviderMenu;
	 @UiField MetadataUserMenu metadataUserMenu;
	
	private static MenuViewImplUiBinder uiBinder = GWT
			.create(MenuViewImplUiBinder.class);

	interface MenuViewImplUiBinder extends UiBinder<Widget, MenuViewImpl> {
	}

	public MenuViewImpl() {
		initWidget(uiBinder.createAndBindUi(this)); 
		applyCommonStyle();
	}

	
}
