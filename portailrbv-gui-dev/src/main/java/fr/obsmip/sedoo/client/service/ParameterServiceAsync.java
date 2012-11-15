package fr.obsmip.sedoo.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ParameterServiceAsync {

	void getParameter(String name, AsyncCallback<String> callback);

	void setParameter(String key, String value, AsyncCallback<String> callback);

}
