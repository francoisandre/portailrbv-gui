package fr.obsmip.sedoo.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import fr.obsmip.sedoo.client.PortailRBV;
import fr.obsmip.sedoo.client.domain.UserDTO;
import fr.obsmip.sedoo.client.event.UserLoginEvent;
import fr.obsmip.sedoo.client.event.UserLogoutEvent;
import fr.obsmip.sedoo.client.mvp.Presenter;
import fr.obsmip.sedoo.client.place.LoginPlace;
import fr.obsmip.sedoo.client.place.SwitchLanguagePlace;
import fr.obsmip.sedoo.client.place.WelcomePlace;

public class HeaderViewImpl extends Composite implements HeaderView {

	private Presenter presenter;
	private static HeaderViewImplUiBinder uiBinder = GWT
			.create(HeaderViewImplUiBinder.class);

	interface HeaderViewImplUiBinder extends UiBinder<Widget, HeaderViewImpl> {
	}

	
	 @UiField Anchor signOutLink;
	 @UiField Anchor signInLink;
//	 @UiField Anchor welcomeLink;
	 @UiField Anchor aboutLink;
	 @UiField Image englishLanguageImage;
	 @UiField Image frenchLanguageImage;
	 @UiField Element userName;
	 @UiField Label notConnectedMessage;

	  public HeaderViewImpl() {
	    initWidget(uiBinder.createAndBindUi(this));
	    if (PortailRBV.getUser()!= null)
	    {
	    	signOutLink.setVisible(true);
	    	signInLink.setVisible(false);
	    }
	  }

//	  @UiHandler("welcomeLink")
//	  void onWelcomeClicked(ClickEvent event) {
//		  
//		  getPresenter().goTo(new WelcomePlace());
//	  }
	  
	  @UiHandler("englishLanguageImage")
	  void onEnglishLanguageButtonClicked(ClickEvent event) {
		  getPresenter().goTo(new SwitchLanguagePlace(SwitchLanguagePlace.ENGLISH));
	  }
	  
	  @UiHandler("frenchLanguageImage")
	  void onFrenchLanguageButtonClicked(ClickEvent event) {
		  getPresenter().goTo(new SwitchLanguagePlace(SwitchLanguagePlace.FRENCH));
	  }
	  
	  @UiHandler("signInLink")
	  void onSignInClicked(ClickEvent event) {
		  Presenter presenter = PortailRBV.getPresenter();
		  presenter.goTo(new LoginPlace());
	  }
	  
	  @UiHandler("signOutLink")
	  void onSignOutClicked(ClickEvent event) {
		  PortailRBV.logout();
	  }
	  
	  @UiHandler("aboutLink")
	  void onAboutClicked(ClickEvent event) {
	    // When the 'About' item is selected, show the AboutDialog.
	    // Note that showing a dialog box does not block -- execution continues
	    // normally, and the dialog fires an event when it is closed.
//	    AboutDialog dlg = new AboutDialog();
//	    dlg.show();
//	    dlg.center();
	  }

	  

	
	public Presenter getPresenter()
	{
		if (presenter == null)
		{
			presenter = PortailRBV.getPresenter();
		}
		return presenter;
	}

	@Override
	public void onNotification(UserLoginEvent event) 
	{
		UserDTO user = PortailRBV.getUser();
		notConnectedMessage.setVisible(false);
		userName.setInnerText(user.getFirstName()+" "+user.getLastName());
		signOutLink.setVisible(true);
    	signInLink.setVisible(false);
	}
	
	@Override
	public void onNotification(UserLogoutEvent event) 
	{
		notConnectedMessage.setVisible(true);
		userName.setInnerText("");
		signOutLink.setVisible(false);
    	signInLink.setVisible(true);
	}
	
}
