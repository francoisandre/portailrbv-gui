package fr.obsmip.sedoo.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class LoginViewImpl extends AbstractSection implements LoginView {

	
	Presenter presenter;
	
	@UiField
	TextBox login;
	
	@UiField
	PasswordTextBox password;
	
	@UiField 
	Button connectButton;
	
	@UiField
	HorizontalPanel okMessagePanel;
	
	@UiField
	HorizontalPanel koMessagePanel;
	
	private static LoginViewImplUiBinder uiBinder = GWT
			.create(LoginViewImplUiBinder.class);

	interface LoginViewImplUiBinder extends UiBinder<Widget, LoginViewImpl> {
	}

	public LoginViewImpl() {
		super();
		initWidget(uiBinder.createAndBindUi(this));
		applyCommonStyle();
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
		
	}
	
	 @UiHandler("connectButton")
	  void onSignInClicked(ClickEvent event) {
		  presenter.login(login.getText(), password.getText());
	  }

	@Override
	public void updateSucces() {
		okMessagePanel.setVisible(true);
		koMessagePanel.setVisible(false);
		connectButton.setEnabled(false);
		
	}

	@Override
	public void updateFailure() {
		okMessagePanel.setVisible(false);
		koMessagePanel.setVisible(true);
	}
	
	@Override
	public void reset()
	{
		password.setText("");
		login.setText("");
		okMessagePanel.setVisible(false);
		koMessagePanel.setVisible(false);
		connectButton.setEnabled(true);
	}

}
