package fr.obsmip.sedoo.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LocaleServiceAsync {

	void getVersion(AsyncCallback<Void> callback);

}
