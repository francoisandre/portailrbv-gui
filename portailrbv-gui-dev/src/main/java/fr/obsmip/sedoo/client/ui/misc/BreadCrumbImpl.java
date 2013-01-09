package fr.obsmip.sedoo.client.ui.misc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import fr.obsmip.sedoo.client.ClientFactory;
import fr.obsmip.sedoo.client.event.MaximizeEvent;
import fr.obsmip.sedoo.client.event.MinimizeEvent;

public class BreadCrumbImpl extends Composite implements BreadCrumb {

	private static BreadCrumbUiBinder uiBinder = GWT.create(BreadCrumbUiBinder.class);

	//	@UiField SpanElement tooltipContent;

	@UiField
	HorizontalPanel contentPanel;

	@UiField 
	Image minimizeImage;

	@UiField 
	Image maximizeImage;
	
	private ClientFactory clientFactory;
	
	List<Shortcut> currentShortcuts = new ArrayList<Shortcut>();

	interface BreadCrumbUiBinder extends UiBinder<Widget, BreadCrumbImpl> {
	}

	public BreadCrumbImpl()  {
		initWidget(uiBinder.createAndBindUi(this));
		contentPanel.setSpacing(5);
	}


	public void refresh(List<Shortcut> shortcuts)
	{
		contentPanel.clear();
		Iterator<Shortcut> iterator = shortcuts.iterator();
		while (iterator.hasNext()) {
			final Shortcut shortcut = iterator.next();
			if (iterator.hasNext())
			{
				Anchor aux = new Anchor();
				aux.setText(shortcut.getLabel());
				aux.setStyleName("breadCrumbInnerToken");
				aux.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						getClientFactory().getPlaceController().goTo(shortcut.getPlace());	
					}
				});
				contentPanel.add(aux);
				contentPanel.add(new Label(" > "));
			}
			else
			{
				Label aux = new Label();
				aux.setText(shortcut.getLabel());
				aux.setStyleName("breadCrumbLastToken");
				contentPanel.add(aux);
			}

		}
		currentShortcuts = shortcuts;
	}

	public ClientFactory getClientFactory() {
		return clientFactory;
	}


	public void setClientFactory(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}


	@UiHandler("maximizeImage")
	void onMaximizeImageClicked(ClickEvent event) {
		clientFactory.getEventBus().fireEvent(new MaximizeEvent());
	}

	@UiHandler("minimizeImage")
	void onMinimizeImageClicked(ClickEvent event) {
		clientFactory.getEventBus().fireEvent(new MinimizeEvent());
	}


	@Override
	public void addShortcut(Shortcut shortcut) {
		currentShortcuts.add(shortcut);
		refresh(currentShortcuts);
	}


	@Override
	public List<Shortcut> getShortcuts() 
	{
		return currentShortcuts;
	}


	
	

}
