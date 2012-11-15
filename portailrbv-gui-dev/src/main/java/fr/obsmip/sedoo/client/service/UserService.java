package fr.obsmip.sedoo.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fr.obsmip.sedoo.client.domain.ObservatoryDTO;
import fr.obsmip.sedoo.client.domain.UserDTO;

@RemoteServiceRelativePath("user")
public interface UserService extends RemoteService {
	
	UserDTO login(String login, String password) throws Exception;
	
}

