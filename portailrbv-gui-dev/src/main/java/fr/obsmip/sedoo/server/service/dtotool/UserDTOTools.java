package fr.obsmip.sedoo.server.service.dtotool;


import fr.obsmip.sedoo.client.domain.UserDTO;
import fr.obsmip.sedoo.core.domain.User;

public class UserDTOTools {
	
	public static UserDTO toUserDTO(User user) 
	{
		UserDTO userDTO = new UserDTO();
		userDTO.setEmail(user.getEmail());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setAdmin(user.isAdmin());
		return userDTO;
	}
}
