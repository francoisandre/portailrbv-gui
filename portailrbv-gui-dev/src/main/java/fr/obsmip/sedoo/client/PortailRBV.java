package fr.obsmip.sedoo.client;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.obsmip.sedoo.client.domain.UserDTO;
import fr.obsmip.sedoo.client.event.MaximizeEvent;
import fr.obsmip.sedoo.client.event.MaximizeEventHandler;
import fr.obsmip.sedoo.client.event.MinimizeEvent;
import fr.obsmip.sedoo.client.event.MinimizeEventHandler;
import fr.obsmip.sedoo.client.event.UserLogoutEvent;
import fr.obsmip.sedoo.client.mvp.AppActivityMapper;
import fr.obsmip.sedoo.client.mvp.AppPlaceHistoryMapper;
import fr.obsmip.sedoo.client.mvp.MenuPresenterImpl;
import fr.obsmip.sedoo.client.mvp.Presenter;
import fr.obsmip.sedoo.client.place.FilteringHistorian;
import fr.obsmip.sedoo.client.place.SwitchLanguagePlace;
import fr.obsmip.sedoo.client.place.WelcomePlace;
import fr.obsmip.sedoo.client.ui.HeaderView;
import fr.obsmip.sedoo.client.ui.SectionHeaderView;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class PortailRBV implements EntryPoint, MaximizeEventHandler, MinimizeEventHandler {
  /**
   * The message displayed to the user when the server cannot be reached or
   * returns an error.
   */
  private static final String SERVER_ERROR = "An error occurred while "
      + "attempting to contact the server. Please check your network "
      + "connection and try again.";

  
  //private final Messages messages = GWT.create(Messages.class);
  
  	//private final MetadataEditingMessage editingMessage = GWT.create(MetadataEditingMessage.class);

	private Place defaultPlace = new WelcomePlace();
	private SimpleLayoutPanel centerPanel =  new SimpleLayoutPanel();
	private DockLayoutPanel mainPanel;
	private static ClientFactory clientFactory;
	private static Presenter presenter;
	private Widget west;
	private Widget south;
	private SplitLayoutPanel splitPanel;
	
	private DockLayoutPanel largeHeader;
	private HeaderView headerView;
	private SectionHeaderView sectionHeaderView;
	private DockLayoutPanel headerFirstPart;
	
	private static UserDTO user;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		GWT.<GlobalBundle>create(GlobalBundle.class).css().ensureInjected();
		clientFactory = getClientFactory();
		presenter = new MenuPresenterImpl(clientFactory);
		guiInit(clientFactory);
		EventBus eventBus = clientFactory.getEventBus();
		PlaceController placeController = clientFactory.getPlaceController();
		
		RootPanel loadingMessage = RootPanel.get("loadingMessage");
		if (loadingMessage != null)
		{
			DOM.setInnerHTML(loadingMessage.getElement(), "");
		}
		
		// Start ActivityManager for the main widget with our ActivityMapper
	    ActivityMapper activityMapper = new AppActivityMapper(clientFactory);
		ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
		activityManager.setDisplay(centerPanel);

		// Start PlaceHistoryHandler with our PlaceHistoryMapper
		AppPlaceHistoryMapper historyMapper= GWT.create(AppPlaceHistoryMapper.class);
		FilteringHistorian historian = new FilteringHistorian();
		String name = SwitchLanguagePlace.class.getName();
		historian.addToken(name.substring(name.lastIndexOf('.')+1));
		
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper,historian);
		historyHandler.register(placeController, eventBus, defaultPlace);

//		mainPanel.setWidth("1000px");
		DockLayoutPanel centeringPanel = new DockLayoutPanel(Unit.EM);
		//centeringPanel.getElement().getStyle().setProperty("background", "radial-gradient(center top, rgb(0, 255, 0) 0%, rgb(0, 0, 255) 100%)");
		centeringPanel.add(mainPanel);
		
		RootLayoutPanel.get().add(centeringPanel);
		Window.enableScrolling(false);
	    Window.setMargin("0px");
		// Goes to the place represented on URL else default place
		historyHandler.handleCurrentHistory();
	}

	private void guiInit(ClientFactory clientFactory) 
	{
		mainPanel = new DockLayoutPanel(Unit.PX);
		mainPanel.getElement().getStyle().setProperty("border","white solid 0px");
		mainPanel.getElement().getStyle().setProperty("boxShadow","1px 1px 5px 1px rgba(0, 0, 0, 0.7)");
		mainPanel.setWidth("1039px");
		mainPanel.getElement().getStyle().setProperty("margin", "auto");
		
//		DockLayoutPanel header = new DockLayoutPanel(Unit.PX);
//		header.addNorth(clientFactory.getHeaderView(), 147);
//		header.addSouth(clientFactory.getBreadCrumb(), 25);
//		DockLayoutPanel topPanel = new DockLayoutPanel(Unit.PX);
		
		
		headerFirstPart = new DockLayoutPanel(Unit.PX);
		headerView = clientFactory.getHeaderView();
		headerFirstPart.addNorth(headerView, 147);
		headerFirstPart.addSouth(clientFactory.getBreadCrumb(), 25);
		
		
		largeHeader = new DockLayoutPanel(Unit.PX);
		largeHeader.addNorth(headerFirstPart, 172);
		sectionHeaderView = clientFactory.getSectionHeaderView();
		largeHeader.addSouth(sectionHeaderView, 50);
		
		//thinHeader = new DockLayoutPanel(Unit.PX);
		//thinHeader.addSouth(clientFactory.getBreadCrumb(), 25);
		
		mainPanel.addNorth(largeHeader,222);
		splitPanel = new SplitLayoutPanel();
		west = clientFactory.getMenuView().asWidget();
		splitPanel.addWest(west, 194);
		south = clientFactory.getStatusBarView().asWidget();
		mainPanel.addSouth(clientFactory.getStatusBarView(), clientFactory.getStatusBarView().getHeight());
		splitPanel.add(centerPanel);
		mainPanel.add(splitPanel);
		
		
		
		clientFactory.getEventBus().addHandler(MaximizeEvent.TYPE, this);
		clientFactory.getEventBus().addHandler(MinimizeEvent.TYPE, this);
	}
	
	public static ClientFactory getClientFactory() {
		if (clientFactory == null)
		{
			clientFactory = GWT.create(ClientFactory.class);
		}
		return clientFactory;
	}
	
	public static Presenter getPresenter()
	{
		return presenter;
	}

	public static UserDTO getUser() {
		return user;
	}

	public static void setUser(UserDTO user) 
	{
		PortailRBV.user = user;
		
	}

	public static void logout() {
		if (PortailRBV.getUser() != null)
		{
			PortailRBV.setUser(null);
			getClientFactory().getEventBus().fireEvent(new UserLogoutEvent());
			getPresenter().goTo(new WelcomePlace());
		}
	}

	@Override
	public void onNotification(MinimizeEvent event) {
		splitPanel.setWidgetHidden(west, false);
		mainPanel.setWidgetHidden(south, false);
		headerFirstPart = new DockLayoutPanel(Unit.PX);
		headerView = clientFactory.getHeaderView();
		headerFirstPart.addNorth(headerView, 147);
		headerFirstPart.addSouth(clientFactory.getBreadCrumb(), 25);
		largeHeader.clear();
		largeHeader.addNorth(headerFirstPart, 172);
		mainPanel.setWidgetSize(largeHeader, 222);
		sectionHeaderView = clientFactory.getSectionHeaderView();
		largeHeader.addSouth(sectionHeaderView, 50);
		mainPanel.setWidgetHidden(largeHeader, false);
		splitPanel.animate(500);
		headerFirstPart.animate(100);
		mainPanel.animate(500);
		
	}

	@Override
	public void onNotification(MaximizeEvent event) {
		splitPanel.setWidgetHidden(west, true);
		mainPanel.setWidgetHidden(south, true);
		mainPanel.setWidgetSize(largeHeader, 25);
		largeHeader.clear();
		largeHeader.addSouth(clientFactory.getBreadCrumb(), 25);
		splitPanel.animate(500);
		headerFirstPart.animate(100);
		mainPanel.animate(500);
	}

  
}
