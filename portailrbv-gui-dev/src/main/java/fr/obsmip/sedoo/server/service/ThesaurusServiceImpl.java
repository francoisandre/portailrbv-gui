package fr.obsmip.sedoo.server.service;

import java.util.HashMap;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.obsmip.sedoo.client.domain.ThesaurusItemDTO;
import fr.obsmip.sedoo.client.service.ThesaurusService;
import fr.obsmip.sedoo.core.dao.ClimateThesaurusDAO;
import fr.obsmip.sedoo.core.dao.LithologyThesaurusDAO;
import fr.obsmip.sedoo.core.dao.ThesaurusDAO;
import fr.obsmip.sedoo.core.domain.ThesaurusItem;
import fr.obsmip.sedoo.server.service.dtotool.ThesaurusItemDTOTools;

public class ThesaurusServiceImpl extends RemoteServiceServlet implements
ThesaurusService {
	
	private final String DEFAULT_LOCALE = "en";
	
	HashMap<String, List<ThesaurusItemDTO>> climateThesaurus = new HashMap<String, List<ThesaurusItemDTO>>();
	HashMap<String, List<ThesaurusItemDTO>> lithologyThesaurus = new HashMap<String, List<ThesaurusItemDTO>>();
	boolean needRefresh = true;
	
	public List<ThesaurusItemDTO> getClimateThesaurus(String locale) throws Exception
	{
		ThesaurusDAO  dao = new ClimateThesaurusDAO();
		return getThesaurus(climateThesaurus, locale, dao);
	}
	
	public List<ThesaurusItemDTO> getLithologyThesaurus(String locale) throws Exception
	{
		ThesaurusDAO  dao = new LithologyThesaurusDAO();
		return getThesaurus(lithologyThesaurus, locale, dao);
	}
	
	public List<ThesaurusItemDTO> getThesaurus(HashMap<String, List<ThesaurusItemDTO>> thesaurus, String locale, ThesaurusDAO dao)
			throws Exception {
		checkRefresh();
		String adaptedLocale = checkLocale(locale);
		List<ThesaurusItemDTO> result = thesaurus.get(adaptedLocale);
		if (result == null)
		{
			//Try to update
			List<ThesaurusItem> aux = dao.getThesaurus(adaptedLocale);
			result = ThesaurusItemDTOTools.fromThesaurusItems(aux);
			if (result.isEmpty() == false)
			{
				thesaurus.put(adaptedLocale, result);
			}
		}
		if (result.isEmpty())
		{
			//unsupported locale - Compute default
			result = thesaurus.get(DEFAULT_LOCALE);
			
		}
		return result;
	}

	private String checkLocale(String locale) {
		if ((locale == null) || (locale.trim().length() == 0))
		{
			return DEFAULT_LOCALE;
		}
		else 
		{
			return locale.toLowerCase();
		}
	}

	private void checkRefresh() {
		if (needRefresh)
		{
			climateThesaurus.clear();
			needRefresh = false;
		}
	}

	@Override
	public void forceRefresh() throws Exception {
		// TODO Auto-generated method stub
		
	}

	
}

