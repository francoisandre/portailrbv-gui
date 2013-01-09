package fr.obsmip.sedoo.client.activity;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.obsmip.sedoo.client.ClientFactory;
import fr.obsmip.sedoo.client.PortailRBV;
import fr.obsmip.sedoo.client.ShortcutFactory;
import fr.obsmip.sedoo.client.domain.UserDTO;
import fr.obsmip.sedoo.client.event.ActionEndEvent;
import fr.obsmip.sedoo.client.event.ActionEventConstant;
import fr.obsmip.sedoo.client.event.ActionStartEvent;
import fr.obsmip.sedoo.client.event.UserLoginEvent;
import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.place.LoginPlace;
import fr.obsmip.sedoo.client.place.WelcomePlace;
import fr.obsmip.sedoo.client.service.UserService;
import fr.obsmip.sedoo.client.service.UserServiceAsync;
import fr.obsmip.sedoo.client.ui.LoginView;
import fr.obsmip.sedoo.client.ui.misc.Shortcut;

public class LoginActivity extends RBVAbstractActivity implements LoginView.Presenter {
	private LoginView loginView;
	
	private final UserServiceAsync userService = GWT.create(UserService.class);

	public LoginActivity(LoginPlace place, ClientFactory clientFactory) {
		super(clientFactory);
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		loginView = clientFactory.getLoginView();
		loginView.setPresenter(this);
		containerWidget.setWidget(loginView.asWidget());
		broadcastActivityTitle(Message.INSTANCE.loginViewHeader());
		List<Shortcut> shortcuts = new ArrayList<Shortcut>();
		shortcuts.add(ShortcutFactory.getWelcomeShortcut());
		shortcuts.add(ShortcutFactory.getLoginShortcut());
		clientFactory.getBreadCrumb().refresh(shortcuts);
		
		
		/*
		 * If the user comes back to the login form after a logout a reset is necessary
		 */
		if (PortailRBV.getUser() == null)
		{
			loginView.reset();
		}
		/*
		versionService.getVersion(new AsyncCallback<String>() {

			@Override
			public void onSuccess(String version) {
				versionView.setVersion(version);
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO onFailure à coder ..
System.out.println("ttoo");
			}
		});*/
	}

	@Override
	public void login(String login, String password) 
	{
		ActionStartEvent e = new ActionStartEvent("Connexion au serveur...", ActionEventConstant.USER_LOGIN_EVENT, true);
        clientFactory.getEventBus().fireEvent(e);
		userService.login(login, password, new AsyncCallback<UserDTO>() {
			
			@Override
			public void onSuccess(UserDTO user) {
				
				PortailRBV.setUser(user);
				
				// TODO Auto-generated method stub
				System.out.println("Login OK");
				ActionEndEvent e = new ActionEndEvent(ActionEventConstant.USER_LOGIN_EVENT);
		        clientFactory.getEventBus().fireEvent(e);
				loginView.updateSucces();
				clientFactory.getEventBus().fireEvent(new UserLoginEvent());
				
				Timer t = new Timer() {
				      public void run() {
				    	  PortailRBV.getPresenter().goTo(new WelcomePlace());
				      }
				    };

				    t.schedule(2000);
				
				
			}
			
			@Override
			public void onFailure(Throwable caught) 
			{
				loginView.updateFailure();
				ActionEndEvent e = new ActionEndEvent(ActionEventConstant.USER_LOGIN_EVENT);
		        clientFactory.getEventBus().fireEvent(e);
				
			}
		});
		
	}
}
