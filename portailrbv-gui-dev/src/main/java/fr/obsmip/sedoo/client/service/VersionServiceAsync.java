package fr.obsmip.sedoo.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface VersionServiceAsync {

	void getVersion(AsyncCallback<String> callback);

}
