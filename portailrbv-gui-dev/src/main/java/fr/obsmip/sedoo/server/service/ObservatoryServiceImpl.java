package fr.obsmip.sedoo.server.service;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.obsmip.sedoo.client.domain.DrainageBasinDTO;
import fr.obsmip.sedoo.client.domain.ObservatoryContactDTO;
import fr.obsmip.sedoo.client.domain.ObservatoryDTO;
import fr.obsmip.sedoo.client.domain.PersonDTO;
import fr.obsmip.sedoo.client.service.ObservatoryService;
import fr.obsmip.sedoo.core.RBVApplication;
import fr.obsmip.sedoo.core.dao.DrainageBasinDAO;
import fr.obsmip.sedoo.core.dao.ObservatoryContactDAO;
import fr.obsmip.sedoo.core.dao.ObservatoryDAO;
import fr.obsmip.sedoo.core.dao.PersonDAO;
import fr.obsmip.sedoo.core.dao.SiteDAO;
import fr.obsmip.sedoo.core.domain.DrainageBasin;
import fr.obsmip.sedoo.core.domain.Observatory;
import fr.obsmip.sedoo.core.domain.ObservatoryContact;
import fr.obsmip.sedoo.core.domain.Person;
import fr.obsmip.sedoo.server.service.dtotool.DrainageBasinDTOTools;
import fr.obsmip.sedoo.server.service.dtotool.ObservatoryContactDTOTools;
import fr.obsmip.sedoo.server.service.dtotool.ObservatoryDTOTools;

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
	public void saveDrainageBasin(DrainageBasinDTO dto) throws Exception 
	{
		DrainageBasinDAO dao = RBVApplication.getInstance().getDrainageBasinDAO();
		Long id = dto.getId();
		
		DrainageBasin drainageBasin = null;
		drainageBasin = dao.getDrainageBasinById(id, true);
		drainageBasin = DrainageBasinDTOTools.fromDrainageBasinDTO(drainageBasin, dto);
		dao.save(drainageBasin);
	}

	@Override
	public void deleteDrainageBasin(Long id) throws Exception {
		DrainageBasinDAO drainageBasinDAO = RBVApplication.getInstance().getDrainageBasinDAO();
		drainageBasinDAO.delete(id);		
	}

	@Override
	public void deleteSite(Long id) throws Exception {
		SiteDAO siteDAO = RBVApplication.getInstance().getSiteDAO();
		siteDAO.delete(id);			
	}

	@Override
	public void deletePerson(Long id) throws Exception {
		PersonDAO personDAO = RBVApplication.getInstance().getPersonDAO();
		personDAO.delete(id);			
	}

	@Override
	public void savePerson(PersonDTO personDTO) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveObservatoryContact(ObservatoryContactDTO dto) {
		
	}

	@Override
	public ObservatoryContactDTO getObservatoryContactById(Long id) {
		ObservatoryContactDAO dao = RBVApplication.getInstance().getObservatoryContactDAO();
		ObservatoryContact contact = dao.getObservatoryContactById(id, true);
		if (contact == null)
		{
			return new ObservatoryContactDTO();
		}
		else
		{
			return ObservatoryContactDTOTools.toObservatoryContactDTO(contact);
		}
	}

	@Override
	public Long addObservatoryContact(ObservatoryContactDTO dto, Long id) throws Exception
	{
		ObservatoryDAO observatoryDAO = RBVApplication.getInstance().getObservatoryDAO();
		ObservatoryContact contact = ObservatoryContactDTOTools.fromDTO(dto);
		Observatory observatory = observatoryDAO.getObservatoryById(id, true);
		observatory.addContact(contact);
		observatoryDAO.save(observatory);
		observatory = observatoryDAO.getObservatoryById(id, true);
		List<ObservatoryContact> contacts = observatory.getContacts();
		ObservatoryContact lastObservatoryContact = contacts.get(contacts.size()-1);
		return lastObservatoryContact.getId();
	}

	@Override
	public Long addObservatory(ObservatoryDTO dto) throws Exception 
	{
		ObservatoryDAO observatoryDAO = RBVApplication.getInstance().getObservatoryDAO();
		Observatory observatory = observatoryDAO.create();
		observatory = ObservatoryDTOTools.fromObservatoryDTO(observatory, dto);
		return observatoryDAO.save(observatory);
	}
	
	
	
	
	
	
}
