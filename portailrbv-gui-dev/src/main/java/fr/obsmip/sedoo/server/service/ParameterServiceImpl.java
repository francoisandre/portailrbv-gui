package fr.obsmip.sedoo.server.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.obsmip.sedoo.client.service.ParameterService;
import fr.obsmip.sedoo.core.BeanFactory;
import fr.obsmip.sedoo.core.RBVApplication;
import fr.obsmip.sedoo.core.parameter.parameterprovider.ReadableParameterProvider;
import fr.obsmip.sedoo.core.parameter.parameterprovider.WritableParameterProvider;

public class ParameterServiceImpl extends RemoteServiceServlet implements
ParameterService {

List<ReadableParameterProvider> parameterProviders = null;

@Override
public String getParameter(String input) throws IllegalArgumentException {

initParameterProviders();
if (parameterProviders == null) {
	return null;
} else {
	Iterator<ReadableParameterProvider> iterator = parameterProviders
			.iterator();
	while (iterator.hasNext()) {
		ReadableParameterProvider parameterProvider = iterator.next();
		String aux = parameterProvider.getParameter(input);
		if (aux != null) {
			return aux;
		}
	}
	return null;
}

}


private void initParameterProviders() {
if (parameterProviders == null) {
	
	List<ReadableParameterProvider> aux = (List<ReadableParameterProvider>) RBVApplication.getInstance().getBeanFactory().getBeanByName(BeanFactory.PARAMETER_PROVIDER_LIST_BEAN_NAME);
	
	if (aux != null)
	{
		parameterProviders = aux;
	}
	else
	{
		parameterProviders = new ArrayList<ReadableParameterProvider>();
	}
	
	
}
}

@Override
public String setParameter(String key, String value) {
initParameterProviders();
if (parameterProviders == null) {
	return null;
} else {
	Iterator<ReadableParameterProvider> iterator = parameterProviders
			.iterator();

	WritableParameterProvider setter = null;
	while (iterator.hasNext()) {
		ReadableParameterProvider parameterProvider = iterator.next();
		if (parameterProvider instanceof WritableParameterProvider)
		{
			setter = (WritableParameterProvider) parameterProvider;
			break;
		}
	}
	
	if (setter == null)
	{
		return null;
	}
	else
	{
		setter.setParameter(key, value);
		return "ok";
	}
}
}

}
