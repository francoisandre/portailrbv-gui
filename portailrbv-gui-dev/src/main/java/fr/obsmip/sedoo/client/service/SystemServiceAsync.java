
package fr.obsmip.sedoo.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SystemServiceAsync {

	void getApplicationVersion(AsyncCallback<String> callback);

	void getJavaVersion(AsyncCallback<String> callback);

}
