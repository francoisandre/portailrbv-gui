package fr.obsmip.sedoo.server.service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.obsmip.sedoo.client.service.VersionService;
import fr.obsmip.sedoo.core.BeanFactory;
import fr.obsmip.sedoo.core.RBVApplication;
import fr.obsmip.sedoo.core.dao.VersionDAO;

public class VersionServiceImpl extends RemoteServiceServlet implements
VersionService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8099324566476135745L;

	@Override
	public String getVersion() 
	{
		VersionDAO dao = (VersionDAO) RBVApplication.getInstance().getBeanFactory().getBeanByName(BeanFactory.VERSION_DAO_BEAN_NAME);
		return dao.getVersion();
	}
	
}

