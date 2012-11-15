package fr.obsmip.sedoo.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.obsmip.sedoo.client.domain.ObservatoryDTO;
import fr.obsmip.sedoo.client.domain.UserDTO;

public interface UserServiceAsync {

	void login(String login, String password, AsyncCallback<UserDTO> callback);


}
