package fr.obsmip.sedoo.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

import fr.obsmip.sedoo.client.ClientFactory;
import fr.obsmip.sedoo.client.activity.DrainageBasinEditingActivity;
import fr.obsmip.sedoo.client.activity.LoginActivity;
import fr.obsmip.sedoo.client.activity.MetadataDisplayActivity;
import fr.obsmip.sedoo.client.activity.MetadataEditingActivity;
import fr.obsmip.sedoo.client.activity.MetadataListActivity;
import fr.obsmip.sedoo.client.activity.MetadataSearchActivity;
import fr.obsmip.sedoo.client.activity.ObservatoryContactEditingActivity;
import fr.obsmip.sedoo.client.activity.ObservatoryEditingActivity;
import fr.obsmip.sedoo.client.activity.ObservatoryManagementActivity;
import fr.obsmip.sedoo.client.activity.SwitchLanguageActivity;
import fr.obsmip.sedoo.client.activity.SystemActivity;
import fr.obsmip.sedoo.client.activity.WelcomeActivity;
import fr.obsmip.sedoo.client.place.DrainageBasinEditingPlace;
import fr.obsmip.sedoo.client.place.LoginPlace;
import fr.obsmip.sedoo.client.place.MetadataDisplayPlace;
import fr.obsmip.sedoo.client.place.MetadataEditingPlace;
import fr.obsmip.sedoo.client.place.MetadataListPlace;
import fr.obsmip.sedoo.client.place.MetadataSearchPlace;
import fr.obsmip.sedoo.client.place.ObservatoryContactEditingPlace;
import fr.obsmip.sedoo.client.place.ObservatoryEditingPlace;
import fr.obsmip.sedoo.client.place.ObservatoryManagementPlace;
import fr.obsmip.sedoo.client.place.SwitchLanguagePlace;
import fr.obsmip.sedoo.client.place.SystemPlace;
import fr.obsmip.sedoo.client.place.WelcomePlace;

public class AppActivityMapper implements ActivityMapper {

	private ClientFactory clientFactory;

	/**
	 * AppActivityMapper associates each Place with its corresponding
	 * {@link Activity}
	 * 
	 * @param clientFactory
	 *            Factory to be passed to activities
	 */
	public AppActivityMapper(ClientFactory clientFactory) {
		super();
		this.clientFactory = clientFactory;
	}

	/**
	 * Map each Place to its corresponding Activity. This would be a great use
	 * for GIN.
	 */
	@Override
	public Activity getActivity(Place place) {
		if (place instanceof WelcomePlace)
		{
			return new WelcomeActivity((WelcomePlace) place, clientFactory);
		}
		else if (place instanceof SystemPlace)
		{
			return new SystemActivity((SystemPlace) place, clientFactory);
		}
		else if (place instanceof MetadataEditingPlace)
		{
			return new MetadataEditingActivity((MetadataEditingPlace) place, clientFactory);
		}
		else if (place instanceof MetadataDisplayPlace)
		{
			return new MetadataDisplayActivity((MetadataDisplayPlace) place, clientFactory);
		}
		else if (place instanceof SwitchLanguagePlace)
		{
			return new SwitchLanguageActivity((SwitchLanguagePlace) place, clientFactory);
		}
		else if (place instanceof MetadataListPlace)
		{
			return new MetadataListActivity((MetadataListPlace) place, clientFactory);
		}
		else if (place instanceof MetadataSearchPlace)
		{
			return new MetadataSearchActivity((MetadataSearchPlace) place, clientFactory);
		}
		else if (place instanceof LoginPlace)
		{
			return new LoginActivity((LoginPlace) place, clientFactory);
		}
		else if (place instanceof ObservatoryManagementPlace)
		{
			return new ObservatoryManagementActivity((ObservatoryManagementPlace) place, clientFactory);
		}
		else if (place instanceof ObservatoryEditingPlace)
		{
			return new ObservatoryEditingActivity((ObservatoryEditingPlace) place, clientFactory);
		}
		else if (place instanceof DrainageBasinEditingPlace)
		{
			return new DrainageBasinEditingActivity((DrainageBasinEditingPlace) place, clientFactory);
		}
		else if (place instanceof ObservatoryContactEditingPlace)
		{
			return new ObservatoryContactEditingActivity((ObservatoryContactEditingPlace) place, clientFactory);
		}
		return null;
	}

}
