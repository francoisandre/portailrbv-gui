package fr.obsmip.sedoo.server.service;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.obsmip.sedoo.client.domain.DrainageBasinDTO;
import fr.obsmip.sedoo.client.domain.ObservatoryDTO;
import fr.obsmip.sedoo.client.service.ObservatoryService;
import fr.obsmip.sedoo.core.RBVApplication;
import fr.obsmip.sedoo.core.dao.DrainageBasinDAO;
import fr.obsmip.sedoo.core.dao.ObservatoryDAO;
import fr.obsmip.sedoo.core.domain.Observatory;
import fr.obsmip.sedoo.server.service.dtotool.DrainageBasinDTOTools;
import fr.obsmip.sedoo.server.service.dtotool.ObservatoryDTOTools;
import fr.obsmip.sedoo.server.service.dtotool.UserDTOTools;

public class ObservatoryServiceImpl extends RemoteServiceServlet implements
ObservatoryService {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4607903276000175883L;

	@Override
	public List<ObservatoryDTO> getObservatories() 
	{
		ObservatoryDAO observatoryDAO = RBVApplication.getInstance().getObservatoryDAO();
		List<Observatory> observatories = observatoryDAO.findAll();
		return ObservatoryDTOTools.toObservatoryDTOList(observatories);
	}

	@Override
	public void deleteObservatory(Long id) throws Exception {
		
		ObservatoryDAO observatoryDAO = RBVApplication.getInstance().getObservatoryDAO();
		observatoryDAO.delete(id);
	}

	@Override
	public ObservatoryDTO getObservatoryById(Long id) {

		ObservatoryDAO observatoryDAO = RBVApplication.getInstance().getObservatoryDAO();
		return ObservatoryDTOTools.toObservatoryDTO(observatoryDAO.getObservatoryById(id, true), true);
	}

	@Override
	public DrainageBasinDTO getDrainageBasinById(Long id) {
		DrainageBasinDAO drainageBasinDAO = RBVApplication.getInstance().getDrainageBasinDAO();
		return DrainageBasinDTOTools.toDrainageBasinDTO(drainageBasinDAO.getDrainageBasinById(id, true), true);
	}

	@Override
	public void saveObservatory(ObservatoryDTO dto) throws Exception 
	{
		ObservatoryDAO observatoryDAO = RBVApplication.getInstance().getObservatoryDAO();
		Long id = dto.getId();
		Observatory observatory = null;
		if (id != null)
		{
			observatory = observatoryDAO.getObservatoryById(id, true);
		}
		else
		{
			observatory = observatoryDAO.create();
		}
		
		observatory = ObservatoryDTOTools.fromObservatoryDTO(observatory, dto);
		
		observatoryDAO.save(observatory);
		
	}

	@Override
	public void deleteDrainageBasin(Long id) throws Exception {
		DrainageBasinDAO drainageBasinDAO = RBVApplication.getInstance().getDrainageBasinDAO();
		drainageBasinDAO.delete(id);		
	}
	
	
	
	
}

