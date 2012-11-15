package fr.obsmip.sedoo.client.place;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.place.shared.PlaceHistoryHandler.DefaultHistorian;

/**
 * Cette classe permet de faire que certaines activités ne soient pas historisées
 * @author andre
 *
 */
public class FilteringHistorian extends DefaultHistorian
{
	
	List<String> tokens = new ArrayList<String>(); 
	
	@Override
	public void newItem(String token, boolean issueEvent) {
		Iterator<String> iterator = tokens.iterator();
		while (iterator.hasNext()) {
			String aux = (String) iterator.next();
			if (token.startsWith(aux))
			{
				return;
			}
		}
		super.newItem(token, issueEvent);
	}
	
	public void addToken(String token)
	{
		tokens.add(token);
	}
	
}
