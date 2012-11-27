package fr.obsmip.sedoo.server.service.dtotool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.obsmip.sedoo.client.domain.ThesaurusItemDTO;
import fr.obsmip.sedoo.core.domain.ThesaurusItem;

public class ThesaurusItemDTOTools {

	public static List<ThesaurusItemDTO> fromThesaurusItems(List<ThesaurusItem> src) 
	{
		List<ThesaurusItemDTO> result = new ArrayList<ThesaurusItemDTO>();
		
		if (src != null)
		{
			Iterator<ThesaurusItem> iterator = src.iterator();
			while (iterator.hasNext()) {
				result.add(fromThesaurusItem(iterator.next()));
			}
		}
		return result;
	}

	private static ThesaurusItemDTO fromThesaurusItem(ThesaurusItem item) 
	{
		return new ThesaurusItemDTO(item.getId(), item.getLabel());
	}

}
