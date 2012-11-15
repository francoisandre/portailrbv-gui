package fr.obsmip.sedoo.client.ui;

import com.google.gwt.user.client.ui.IsWidget;

public interface LoginView extends IsWidget {

	void setPresenter(Presenter presenter);
	void updateSucces();
	void updateFailure();
	void reset();
	
	public interface Presenter 
	 {
	        void login(String login, String password);
	 }
	
}
