package fr.obsmip.sedoo.client;

import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.place.DrainageBasinEditingPlace;
import fr.obsmip.sedoo.client.place.LoginPlace;
import fr.obsmip.sedoo.client.place.MetadataEditingPlace;
import fr.obsmip.sedoo.client.place.MetadataListPlace;
import fr.obsmip.sedoo.client.place.MetadataSearchPlace;
import fr.obsmip.sedoo.client.place.ObservatoryContactEditingPlace;
import fr.obsmip.sedoo.client.place.ObservatoryEditingPlace;
import fr.obsmip.sedoo.client.place.ObservatoryManagementPlace;
import fr.obsmip.sedoo.client.place.SystemPlace;
import fr.obsmip.sedoo.client.place.WelcomePlace;
import fr.obsmip.sedoo.client.ui.misc.Shortcut;

public class ShortcutFactory {
	
	static public Shortcut getWelcomeShortcut()
	{
		return new Shortcut(Message.INSTANCE.welcomeViewHeader(), new WelcomePlace());
	}

	public static Shortcut getSystemShortcut() {
		return new Shortcut(Message.INSTANCE.systemViewHeader(), new SystemPlace());
	}
	
	public static Shortcut getLoginShortcut() {
		return new Shortcut(Message.INSTANCE.loginViewHeader(), new LoginPlace());
	}
	
	public static Shortcut getObservatoryManagementShortcut() {
		return new Shortcut(Message.INSTANCE.observatoryManagementViewHeader(), new ObservatoryManagementPlace());
	}

	public static Shortcut getMetadataListShortcut() {
		return new Shortcut(Message.INSTANCE.listingViewTitle(), new MetadataListPlace());
	}
	
	public static Shortcut getMetadataEditingShortcut() {
		return new Shortcut(Message.INSTANCE.metadataEditingTitle(), new MetadataEditingPlace());
	}
	
	public static Shortcut getMetadataSearchShortcut() {
		return new Shortcut(Message.INSTANCE.metadataShearchingTitle(), new MetadataSearchPlace());
	}

	public static Shortcut getObservatoryModificationShortcut(String shortLabel, Long observatoryId) {
		ObservatoryEditingPlace aux = new ObservatoryEditingPlace();
		aux.setObservatoryId(observatoryId);
		return new Shortcut(Message.INSTANCE.observatoryEditingViewModificationHeader()+" ("+shortLabel+")", aux);
	}

	public static Shortcut getObservatoryCreationShortcut() 
	{
		return new Shortcut(Message.INSTANCE.observatoryEditingViewCreationHeader(),new ObservatoryEditingPlace());
	}

	public static Shortcut getObservatoryContactModificationShortcut(String name, Long id) {
		ObservatoryContactEditingPlace aux = new ObservatoryContactEditingPlace();
		aux.setMode(Constants.MODIFY);
		aux.setId(id);
		
		return new Shortcut(Message.INSTANCE.observatoryContactEditingViewModificationHeader()+" ("+name+")", aux);
	}
	
	public static Shortcut getObservatoryContactCreationShortcut(Long id) {
		ObservatoryContactEditingPlace aux = new ObservatoryContactEditingPlace();
		aux.setId(id);
		aux.setMode(Constants.CREATE);
		return new Shortcut(Message.INSTANCE.observatoryContactEditingViewCreationHeader(), aux);
	}

	public static Shortcut getObservatoryDrainageBasinCreationShortcut(Long id) {
		DrainageBasinEditingPlace aux = new DrainageBasinEditingPlace();
		aux.setId(id);
		aux.setMode(Constants.CREATE);
		return new Shortcut(Message.INSTANCE.drainageBasinEditingViewCreationHeader(), aux);
	}

	public static Shortcut getDrainageBasinModificationShortcut(String label,Long id) {
		DrainageBasinEditingPlace aux = new DrainageBasinEditingPlace();
		aux.setMode(Constants.MODIFY);
		aux.setId(id);
		return new Shortcut(Message.INSTANCE.drainageBasinEditingViewModificationHeader()+" ("+label+")", aux);
	}

}
