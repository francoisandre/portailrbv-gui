package fr.obsmip.sedoo.server.service;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.obsmip.sedoo.client.domain.MetadataDTO;
import fr.obsmip.sedoo.client.domain.SummaryDTO;
import fr.obsmip.sedoo.client.service.MetadataService;
import fr.obsmip.sedoo.core.RBVApplication;
import fr.obsmip.sedoo.core.dao.MetadataDAO;
import fr.obsmip.sedoo.core.domain.Metadata;
import fr.obsmip.sedoo.core.domain.MetadataTools;
import fr.obsmip.sedoo.core.domain.Summary;
import fr.obsmip.sedoo.server.dao.MetadataDTODAO;
import fr.obsmip.sedoo.server.service.dtotool.MetadataDTOTools;

public class MetadataServiceImpl extends RemoteServiceServlet implements
MetadataService {

	MetadataDAO dao = null;
	
	public MetadataServiceImpl() 
	{
		dao = RBVApplication.getInstance().getMetadataDAO();
	}
	
	
	@Override
	public String toXML(MetadataDTO metadataDTO) 
	{
		Metadata metadata = MetadataDTOTools.toMetadata(metadataDTO);
		try {
			String xml = MetadataTools.toXML(metadata);
			return xml;
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public MetadataDTO createDefaultMetadata() {
		
		return MetadataDTODAO.createFakeMetadata();
	}

	@Override
	public List<SummaryDTO> getSummaries() {
		
		List<Summary> summaries = dao.getSummaries();
		return MetadataDTOTools.toSummaryDTO(summaries);
	}


	@Override
	public String getPDFURL(String metadataId) 
	{
		return dao.getPDFURL(metadataId);
	}


	@Override
	public MetadataDTO getMetadataById(String id) throws Exception 
	{
		return MetadataDTOTools.toMetadatoDTO(dao.getMetadataById(id));
	}
	
}
