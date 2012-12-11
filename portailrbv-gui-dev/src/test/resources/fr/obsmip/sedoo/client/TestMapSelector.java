package fr.obsmip.sedoo.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.obsmip.sedoo.client.domain.GeographicBoundingBoxDTO;
import fr.obsmip.sedoo.client.domain.SiteDTO;
import fr.obsmip.sedoo.client.ui.misc.MapSelector;
import fr.obsmip.sedoo.client.ui.table.SiteTable;

public class TestMapSelector implements EntryPoint {

	@Override
	public void onModuleLoad() {
		GWT.<GlobalBundle>create(GlobalBundle.class).css().ensureInjected();
		RootPanel loadingMessage = RootPanel.get("loadingMessage");
		if (loadingMessage != null)
		{
			DOM.setInnerHTML(loadingMessage.getElement(), "");
		}
		
		GeographicBoundingBoxDTO box = new GeographicBoundingBoxDTO();
		box.setWestBoundLongitude("-69.3128");
		box.setEastBoundLongitude("-69.2956");
		box.setSouthBoundLatitude("-17.3885");
		box.setNorthBoundLatitude("-17.3984");
		
		VerticalPanel panel = new VerticalPanel();
		MapSelector selector = new MapSelector(true);
		MapSelector selector2 = new MapSelector(false);
		
		
	
		
		SiteDTO site1 = new SiteDTO();
		site1.setLongitude(""+-69.3048);
		site1.setLatitude(""+-17.3927);
		site1.setId(1L);
		
		SiteDTO site2 = new SiteDTO();
		site2.setLongitude(""+-69.2944);
		site2.setLatitude(""+-17.3878);
		site2.setId(2L);
		
		List<SiteDTO> sites = new ArrayList<SiteDTO>();
		sites.add(site1);
		sites.add(site2);
		
		selector.setSites(sites);
		SiteTable siteTable = new SiteTable();
		siteTable.init(sites);
		selector.setSiteEventListener(siteTable);
		selector.setSiteIdProvider(siteTable);
		siteTable.setSiteEventListener(selector);

		selector.setGeographicBoundingBoxDTO(box);
		selector2.setGeographicBoundingBoxDTO(box);
		
		panel.add(selector);
		panel.add(siteTable);
		
		RootPanel.get().add(panel);
	}

}
