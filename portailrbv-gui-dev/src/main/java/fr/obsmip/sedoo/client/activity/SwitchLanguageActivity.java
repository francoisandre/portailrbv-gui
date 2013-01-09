package fr.obsmip.sedoo.client.activity;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.obsmip.sedoo.client.ClientFactory;
import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.place.SwitchLanguagePlace;
import fr.obsmip.sedoo.client.ui.LanguageSwitchingView;

public class SwitchLanguageActivity extends RBVAbstractActivity {
	

	private String locale;
	
	public SwitchLanguageActivity(SwitchLanguagePlace place, ClientFactory clientFactory) {
		super(clientFactory);
		setLocale(place.getLocale());
		broadcastActivityTitle(Message.INSTANCE.languageSwitchingTitle());
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		
		final LanguageSwitchingView switchingView = clientFactory.getLanguageSwitchingView();
		containerWidget.setWidget(switchingView.asWidget());
        
		Timer t = new Timer() {
		      public void run() {
		    	  Window.Location.assign( // or replace()
		        		   Window.Location.createUrlBuilder()
		        		      .setParameter(LocaleInfo.getLocaleQueryParam(), getLocale())
		        		      .buildString());
		      }
		    };

		    t.schedule(2000);
		
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}
}
