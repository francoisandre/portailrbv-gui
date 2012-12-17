package fr.obsmip.sedoo.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;

import fr.obsmip.sedoo.client.event.ActionEndEvent;
import fr.obsmip.sedoo.client.event.ActionStartEvent;
import fr.obsmip.sedoo.client.event.ActivityChangeEvent;
import fr.obsmip.sedoo.client.event.UserLoginEvent;
import fr.obsmip.sedoo.client.event.UserLogoutEvent;
import fr.obsmip.sedoo.client.ui.DrainageBasinChoiceView;
import fr.obsmip.sedoo.client.ui.DrainageBasinChoiceViewImpl;
import fr.obsmip.sedoo.client.ui.DrainageBasinEditingView;
import fr.obsmip.sedoo.client.ui.DrainageBasinEditingViewImpl;
import fr.obsmip.sedoo.client.ui.HeaderView;
import fr.obsmip.sedoo.client.ui.HeaderViewImpl;
import fr.obsmip.sedoo.client.ui.LanguageSwitchingView;
import fr.obsmip.sedoo.client.ui.LanguageSwitchingViewImpl;
import fr.obsmip.sedoo.client.ui.LoginView;
import fr.obsmip.sedoo.client.ui.LoginViewImpl;
import fr.obsmip.sedoo.client.ui.MenuView;
import fr.obsmip.sedoo.client.ui.MenuViewImpl;
import fr.obsmip.sedoo.client.ui.MetadataDisplayView;
import fr.obsmip.sedoo.client.ui.MetadataDisplayViewImpl;
import fr.obsmip.sedoo.client.ui.MetadataEditingView;
import fr.obsmip.sedoo.client.ui.MetadataEditingViewImpl;
import fr.obsmip.sedoo.client.ui.MetadataListView;
import fr.obsmip.sedoo.client.ui.MetadataListViewImpl;
import fr.obsmip.sedoo.client.ui.MetadataSearchView;
import fr.obsmip.sedoo.client.ui.MetadataSearchViewImpl;
import fr.obsmip.sedoo.client.ui.ObservatoryContactEditingView;
import fr.obsmip.sedoo.client.ui.ObservatoryContactEditingViewImpl;
import fr.obsmip.sedoo.client.ui.ObservatoryEditingView;
import fr.obsmip.sedoo.client.ui.ObservatoryEditingViewImpl;
import fr.obsmip.sedoo.client.ui.ObservatoryManagementView;
import fr.obsmip.sedoo.client.ui.ObservatoryManagementViewImpl;
import fr.obsmip.sedoo.client.ui.SectionHeaderView;
import fr.obsmip.sedoo.client.ui.SectionHeaderViewImpl;
import fr.obsmip.sedoo.client.ui.StatusBarView;
import fr.obsmip.sedoo.client.ui.StatusBarViewImpl;
import fr.obsmip.sedoo.client.ui.SystemView;
import fr.obsmip.sedoo.client.ui.SystemViewImpl;
import fr.obsmip.sedoo.client.ui.WelcomeView;
import fr.obsmip.sedoo.client.ui.WelcomeViewImpl;
import fr.obsmip.sedoo.client.ui.misc.BreadCrumb;
import fr.obsmip.sedoo.client.ui.misc.BreadCrumbImpl;


public class ClientFactoryImpl implements ClientFactory {

	private static final EventBus EVENT_BUS = new SimpleEventBus();
	private static final PlaceController PLACE_CONTROLLER = new AsynchronousPlaceController(EVENT_BUS);
	private static final WelcomeView WELCOME_VIEW = new WelcomeViewImpl();
	private static final ObservatoryManagementView OBSERVATORY_MANAGEMENT_VIEW = new ObservatoryManagementViewImpl();
	private static final ObservatoryEditingView OBSERVATORY_EDITING_VIEW = new ObservatoryEditingViewImpl();
//	private static final DrainageBasinEditingView DRAINAGE_BASIN_EDITING_VIEW = new DrainageBasinEditingViewFakeImpl();
	private static final DrainageBasinEditingView DRAINAGE_BASIN_EDITING_VIEW = new DrainageBasinEditingViewImpl();
	private static final DrainageBasinChoiceView DRAINAGE_BASIN_CHOICE_VIEW = new DrainageBasinChoiceViewImpl();
	private static final MetadataSearchView METADATA_SEARCH_VIEW = new MetadataSearchViewImpl();
	private static final MetadataListView METADATA_LIST_VIEW = new MetadataListViewImpl();
	private static final BreadCrumb BREAD_CRUMB = new BreadCrumbImpl();
	
	static
	{
//		EVENT_BUS.addHandler(NotificationEvent.TYPE, BREAD_CRUMB);
	}
	
	private static final HeaderView HEADER_VIEW = new HeaderViewImpl();
	private static final SectionHeaderView SECTION_HEADER_VIEW = new SectionHeaderViewImpl();
	private static final ObservatoryContactEditingView OBSERVATORY_CONTACT_EDITING_VIEW = new ObservatoryContactEditingViewImpl();
	
	static
	{
		EVENT_BUS.addHandler(UserLoginEvent.TYPE, HEADER_VIEW);
		EVENT_BUS.addHandler(UserLogoutEvent.TYPE, HEADER_VIEW);
	}
	
	private final MenuView MENU_VIEW = new MenuViewImpl();
	private static final SystemView SYSTEM_VIEW = new SystemViewImpl();
//	private static final StatusBarView STATUS_BAR_VIEW = new DebugStatusBarViewImpl(EVENT_BUS);
	private static final StatusBarView STATUS_BAR_VIEW = new StatusBarViewImpl();
	private static final LoginView LOGIN_VIEW = new LoginViewImpl();
	
	static
	{
		EVENT_BUS.addHandler(ActionStartEvent.TYPE, STATUS_BAR_VIEW);
		EVENT_BUS.addHandler(ActionEndEvent.TYPE, STATUS_BAR_VIEW);
	}
	
	private static final MetadataEditingView METADATA_EDITING_VIEW = new MetadataEditingViewImpl();
	private static final MetadataDisplayView METADATA_DISPLAY_VIEW = new MetadataDisplayViewImpl();
	private static final LanguageSwitchingView LANGUAGE_SWITCHING_VIEW = new LanguageSwitchingViewImpl();
	public static final GlobalBundle GLOBAL_BUNDLE =  GWT.create(GlobalBundle.class);
	
	static
	{
		EVENT_BUS.addHandler(ActivityChangeEvent.TYPE, SECTION_HEADER_VIEW);
	}
	
	
	
	
	public ClientFactoryImpl()
	{
		BREAD_CRUMB.setClientFactory(this);
	}
	
	public EventBus getEventBus() {
		return EVENT_BUS;
	}

	public PlaceController getPlaceController() {
		return PLACE_CONTROLLER;
	}

	public WelcomeView getWelcomeView() {
		return WELCOME_VIEW;
	}

	@Override
	public MenuView getMenuView() {
		return MENU_VIEW;
	}
	
	@Override
	public HeaderView getHeaderView() {
		return HEADER_VIEW;
	}
	

	public SystemView getSystemView() {
		return SYSTEM_VIEW;
	}

	@Override
	public MetadataEditingView getMetadataEditingView() {
		return METADATA_EDITING_VIEW;
	}

	@Override
	public StatusBarView getStatusBarView() {
		return STATUS_BAR_VIEW;
	}
	
	@Override
	public SectionHeaderView getSectionHeaderView() {
		return SECTION_HEADER_VIEW;
	}
	
	@Override
	public GlobalBundle getBundle() {
		return GLOBAL_BUNDLE;
	}
	

	@Override
	public LanguageSwitchingView getLanguageSwitchingView() {
		return LANGUAGE_SWITCHING_VIEW;
	}

	@Override
	public MetadataSearchView getMetadataSearchView() {
		return METADATA_SEARCH_VIEW;
	}

	@Override
	public MetadataListView getMetadataListView() {
		return METADATA_LIST_VIEW;
	}

	@Override
	public MetadataDisplayView getMetadataDisplayView() {
		return METADATA_DISPLAY_VIEW;
	}

	@Override
	public LoginView getLoginView() {
		return LOGIN_VIEW;
	}

	@Override
	public ObservatoryManagementView getObservatoryManagementView() {
		return OBSERVATORY_MANAGEMENT_VIEW;
	}
	
	@Override
	public ObservatoryEditingView getObservatoryEditingView() {
		return OBSERVATORY_EDITING_VIEW;
	}

	@Override
	public DrainageBasinEditingView getDrainageBasinEditingView() {
		return DRAINAGE_BASIN_EDITING_VIEW;
	}
	
	@Override
	public ObservatoryContactEditingView getObservatoryContactEditingView() {
		return OBSERVATORY_CONTACT_EDITING_VIEW;
	}

	@Override
	public BreadCrumb getBreadCrumb() {
		return BREAD_CRUMB;
	}

	@Override
	public DrainageBasinChoiceView getDrainageBasinChoiceView() {
		return DRAINAGE_BASIN_CHOICE_VIEW;
	}
	
	  

}
