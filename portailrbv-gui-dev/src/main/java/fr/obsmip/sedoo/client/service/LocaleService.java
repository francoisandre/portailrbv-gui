package fr.obsmip.sedoo.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("version")
public interface LocaleService extends RemoteService {
	
	void getVersion();
	
}

