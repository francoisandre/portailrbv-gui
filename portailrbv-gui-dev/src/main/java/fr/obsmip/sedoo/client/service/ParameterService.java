package fr.obsmip.sedoo.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("parameter")
public interface ParameterService extends RemoteService {
	String getParameter(String name) throws IllegalArgumentException;

	String setParameter(String key, String value);


}

