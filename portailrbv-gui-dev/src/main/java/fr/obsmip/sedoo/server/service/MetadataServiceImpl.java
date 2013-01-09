package fr.obsmip.sedoo.server.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.obsmip.sedoo.client.domain.ExtendedSummaryDTO;
import fr.obsmip.sedoo.client.domain.SummaryDTO;
import fr.obsmip.sedoo.client.domain.metadata.MetadataDTO;
import fr.obsmip.sedoo.client.service.MetadataService;
import fr.obsmip.sedoo.core.RBVApplication;
import fr.obsmip.sedoo.core.dao.MetadataDAO;
import fr.obsmip.sedoo.server.dao.MetadataDTODAO;
import fr.obsmip.sedoo.server.service.dtotool.MetadataDTOTools;
import fr.sedoo.commons.metadata.utils.domain.Metadata;
import fr.sedoo.commons.metadata.utils.domain.MetadataTools;
import fr.sedoo.commons.metadata.utils.domain.Summary;

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
		
		return MetadataDTODAO.createDefaultMetadata();
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
	public MetadataDTO getMetadataByUuid(String uuid) throws Exception 
	{
		return MetadataDTOTools.toMetadatoDTO(dao.getMetadataById(uuid));
	}


	@Override
	public List<ExtendedSummaryDTO> getExtendedSummariesByDrainageBasinId(Long id) 
	{
		List<Summary> summaries = dao.getSummaries();
		List<SummaryDTO> summaryDTO = MetadataDTOTools.toSummaryDTO(summaries);
		
		List<ExtendedSummaryDTO> result = new ArrayList<ExtendedSummaryDTO>();
		Iterator<SummaryDTO> iterator = summaryDTO.iterator();
		long i=1L;
		while (iterator.hasNext()) {
			ExtendedSummaryDTO aux = new ExtendedSummaryDTO(iterator.next());
			aux.setId(i);
			i++;
			result.add(aux);
		}
		
		return result;
	}
	
}

