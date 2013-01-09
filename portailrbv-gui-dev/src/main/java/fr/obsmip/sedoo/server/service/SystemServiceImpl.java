package fr.obsmip.sedoo.server.service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.obsmip.sedoo.client.service.SystemService;
import fr.obsmip.sedoo.core.BeanFactory;
import fr.obsmip.sedoo.core.RBVApplication;
import fr.obsmip.sedoo.core.dao.VersionDAO;

public class SystemServiceImpl extends RemoteServiceServlet implements
SystemService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8099324566476135745L;

	@Override
	public String getApplicationVersion() 
	{
		VersionDAO dao = (VersionDAO) RBVApplication.getInstance().getBeanFactory().getBeanByName(BeanFactory.VERSION_DAO_BEAN_NAME);
		return dao.getVersion();
	}

	@Override
	public String getJavaVersion() {
		return System.getProperty("java.version");
	}
	
}

