package fr.obsmip.sedoo.server.service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.obsmip.sedoo.client.domain.UserDTO;
import fr.obsmip.sedoo.client.service.UserService;
import fr.obsmip.sedoo.core.domain.User;
import fr.obsmip.sedoo.core.geonetwork.LoginRequest;
import fr.obsmip.sedoo.server.service.dtotool.UserDTOTools;

public class UserServiceImpl extends RemoteServiceServlet implements
UserService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1052243114242999167L;

	@Override
	public UserDTO login(String login, String password) throws Exception 
	{
		LoginRequest request = new LoginRequest(login, password);
		try
		{
			User user = request.execute();
			return UserDTOTools.toUserDTO(user);
			
		}
		catch (Exception e)
		{
			throw new Exception();
		}
	}

	
}

