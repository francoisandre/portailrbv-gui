package fr.obsmip.sedoo.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;

import fr.obsmip.sedoo.client.ui.DrainageBasinEditingView;
import fr.obsmip.sedoo.client.ui.HeaderView;
import fr.obsmip.sedoo.client.ui.LanguageSwitchingView;
import fr.obsmip.sedoo.client.ui.LoginView;
import fr.obsmip.sedoo.client.ui.MenuView;
import fr.obsmip.sedoo.client.ui.MetadataDisplayView;
import fr.obsmip.sedoo.client.ui.MetadataEditingView;
import fr.obsmip.sedoo.client.ui.MetadataListView;
import fr.obsmip.sedoo.client.ui.MetadataSearchView;
import fr.obsmip.sedoo.client.ui.ObservatoryEditingView;
import fr.obsmip.sedoo.client.ui.ObservatoryManagementView;
import fr.obsmip.sedoo.client.ui.SectionHeaderView;
import fr.obsmip.sedoo.client.ui.StatusBarView;
import fr.obsmip.sedoo.client.ui.SystemView;
import fr.obsmip.sedoo.client.ui.WelcomeView;

public interface ClientFactory {
	
	
	EventBus getEventBus();
	PlaceController getPlaceController();
	WelcomeView getWelcomeView();
	MenuView getMenuView();
	HeaderView getHeaderView();
	SystemView getSystemView();
	MetadataEditingView getMetadataEditingView();
	MetadataDisplayView getMetadataDisplayView();
	StatusBarView getStatusBarView();
	GlobalBundle getBundle();
	LanguageSwitchingView getLanguageSwitchingView();
	MetadataSearchView getMetadataSearchView();
	MetadataListView getMetadataListView();
	LoginView getLoginView();
	ObservatoryManagementView getObservatoryManagementView();
	ObservatoryEditingView getObservatoryEditingView();
	SectionHeaderView getSectionHeaderView();
	DrainageBasinEditingView getDrainageBasinEditingView();
}
