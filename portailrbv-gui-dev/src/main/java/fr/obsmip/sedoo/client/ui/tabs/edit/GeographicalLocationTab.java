package fr.obsmip.sedoo.client.ui.tabs.edit;

import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.Map;
import org.gwtopenmaps.openlayers.client.MapOptions;
import org.gwtopenmaps.openlayers.client.MapWidget;
import org.gwtopenmaps.openlayers.client.Projection;
import org.gwtopenmaps.openlayers.client.control.LayerSwitcher;
import org.gwtopenmaps.openlayers.client.control.OverviewMap;
import org.gwtopenmaps.openlayers.client.control.ScaleLine;
import org.gwtopenmaps.openlayers.client.layer.OSM;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class GeographicalLocationTab extends Composite {

	  private static final Projection DEFAULT_PROJECTION = new Projection(
	            "EPSG:4326");
	
	private static GeographicalLocationTabUiBinder uiBinder = GWT
			.create(GeographicalLocationTabUiBinder.class);

	interface GeographicalLocationTabUiBinder extends
			UiBinder<Widget, GeographicalLocationTab> {
	}
	
	@UiField
	TextBox northBoundLatitude;
	
	@UiField
	TextBox southBoundLatitude;
	
	@UiField
	TextBox eastBoundLongitude;
	
	@UiField
	TextBox westBoundLongitude;
	
	@UiField 
	VerticalPanel mapPanel;
	
	public GeographicalLocationTab() {
		initWidget(uiBinder.createAndBindUi(this));
		
		
		/*final MapOptions options = new MapOptions();
	    // Zoom level. Required
	    options.setZoom(8);
	    // Open a map centered on Cawker City, KS USA. Required
	    options.setCenter(new LatLng(39.509, -98.434));
	    // Map type. Required.
	    options.setMapTypeId(new MapTypeId().getRoadmap());
	    
	    // Enable maps drag feature. Disabled by default.
	    options.setDraggable(true);
	    // Enable and add default navigation control. Disabled by default.
	    options.setNavigationControl(true);
	    // Enable and add map type control. Disabled by default.
	    options.setMapTypeControl(true);
	    MapWidget mapWidget = new MapWidget(options);
	    mapWidget.setSize("800px", "600px");
	    
	    */
		
		 MapOptions defaultMapOptions = new MapOptions();
	        defaultMapOptions.setNumZoomLevels(16);
	 
	        //Create a MapWidget and add 2 OSM layers
	        MapWidget mapWidget = new MapWidget("500px", "500px", defaultMapOptions);
	        OSM osm_1 = OSM.Mapnik("Mapnik");
	        OSM osm_2 = OSM.CycleMap("CycleMap");
	        osm_1.setIsBaseLayer(true);
	        osm_2.setIsBaseLayer(true);
	        Map map = mapWidget.getMap();
	        map.addLayer(osm_1);
	        map.addLayer(osm_2);
	 
	        //Lets add some default controls to the map
	        map.addControl(new LayerSwitcher()); //+ sign in the upperright corner to display the layer switcher
	        map.addControl(new OverviewMap()); //+ sign in the lowerright to display the overviewmap
	        map.addControl(new ScaleLine()); //Display the scaleline
	 
	        //Center and zoom to a location
	        LonLat lonLat = new LonLat(6.95, 50.94);
	        lonLat.transform(DEFAULT_PROJECTION.getProjectionCode(),
	                         map.getProjection()); //transform lonlat to OSM coordinate system
	        map.setCenter(lonLat, 12);

//	    final DockLayoutPanel dock = new DockLayoutPanel(Unit.PX);
//	    dock.addNorth(map, 500);

	    
	    mapPanel.add(mapWidget);
	    
		
	}

}
