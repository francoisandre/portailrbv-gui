package fr.obsmip.sedoo.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("system")
public interface SystemService extends RemoteService {
	
	String getApplicationVersion();
	String getJavaVersion();
	
}

