package fr.obsmip.sedoo.client.ui.misc;

import org.gwtopenmaps.openlayers.client.Bounds;
import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.Map;
import org.gwtopenmaps.openlayers.client.MapOptions;
import org.gwtopenmaps.openlayers.client.MapWidget;
import org.gwtopenmaps.openlayers.client.Projection;
import org.gwtopenmaps.openlayers.client.control.DragPan;
import org.gwtopenmaps.openlayers.client.control.DrawFeature;
import org.gwtopenmaps.openlayers.client.control.DrawFeature.FeatureAddedListener;
import org.gwtopenmaps.openlayers.client.control.DrawFeatureOptions;
import org.gwtopenmaps.openlayers.client.control.EditingToolbar;
import org.gwtopenmaps.openlayers.client.control.MousePosition;
import org.gwtopenmaps.openlayers.client.control.MousePositionOptions;
import org.gwtopenmaps.openlayers.client.control.MousePositionOutput;
import org.gwtopenmaps.openlayers.client.control.Navigation;
import org.gwtopenmaps.openlayers.client.control.OverviewMap;
import org.gwtopenmaps.openlayers.client.control.ScaleLine;
import org.gwtopenmaps.openlayers.client.feature.VectorFeature;
import org.gwtopenmaps.openlayers.client.handler.RegularPolygonHandler;
import org.gwtopenmaps.openlayers.client.handler.RegularPolygonHandlerOptions;
import org.gwtopenmaps.openlayers.client.layer.OSM;
import org.gwtopenmaps.openlayers.client.layer.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.obsmip.sedoo.client.domain.GeographicBoundingBoxDTO;

public class MapSelector extends Composite  {

	private static MapSelectorUiBinder uiBinder = GWT.create(MapSelectorUiBinder.class);
	
	private static final Projection DEFAULT_PROJECTION = new Projection("EPSG:4326");
	
	@UiField
	TextBox northBoundLatitude;
	
	@UiField
	TextBox southBoundLatitude;
	
	@UiField
	TextBox eastBoundLongitude;
	
	@UiField
	TextBox westBoundLongitude;
	
	Map map;
	
	@UiField 
	VerticalPanel mapPanel;
	
	private DrawFeature drawRectangleFeatureControl = null;
	
	private NumberFormat doubleFormatter = NumberFormat.getFormat("#.0000");
	
	VectorFeature currentFeature;
	
	Vector vectorLayer;

	interface MapSelectorUiBinder extends UiBinder<Widget, MapSelector> {
	}

	public MapSelector() {
		initWidget(uiBinder.createAndBindUi(this));
		MapOptions defaultMapOptions = new MapOptions();
		defaultMapOptions.setDisplayProjection(new Projection("EPSG:4326"));
        defaultMapOptions.setNumZoomLevels(16);
 
        //Create a MapWidget and add 2 OSM layers
        MapWidget mapWidget = new MapWidget("500px", "500px", defaultMapOptions);
        OSM osm_1 = OSM.Mapnik("Mapnik");
        OSM osm_2 = OSM.CycleMap("CycleMap");
        osm_1.setIsBaseLayer(true);
        osm_2.setIsBaseLayer(true);
        map = mapWidget.getMap();
        map.addLayer(osm_1);
        map.addLayer(osm_2);
        
        
        
        /*
        GoogleV3Options gHybridOptions = new GoogleV3Options();
        gHybridOptions.setIsBaseLayer(true);
        gHybridOptions.setType(GoogleV3MapType.G_HYBRID_MAP);
        GoogleV3 gHybrid = new GoogleV3("Google Hybrid", gHybridOptions);
 
        GoogleV3Options gNormalOptions = new GoogleV3Options();
        gNormalOptions.setIsBaseLayer(true);
        gNormalOptions.setType(GoogleV3MapType.G_NORMAL_MAP);
        GoogleV3 gNormal = new GoogleV3("Google Normal", gNormalOptions);
 
        GoogleV3Options gSatelliteOptions = new GoogleV3Options();
        gSatelliteOptions.setIsBaseLayer(true);
        gSatelliteOptions.setType(GoogleV3MapType.G_SATELLITE_MAP);
        GoogleV3 gSatellite = new GoogleV3("Google Satellite", gSatelliteOptions);
 
        GoogleV3Options gTerrainOptions = new GoogleV3Options();
        gTerrainOptions.setIsBaseLayer(true);
        gTerrainOptions.setType(GoogleV3MapType.G_TERRAIN_MAP);
        GoogleV3 gTerrain = new GoogleV3("Google Terrain", gTerrainOptions);
         */
 
        //Lets add some default controls to the map
        //map.addControl(new LayerSwitcher()); //+ sign in the upperright corner to display the layer switcher
        map.addControl(new OverviewMap()); //+ sign in the lowerright to display the overviewmap
        map.addControl(new ScaleLine()); //Display the scaleline
        map.addControl(new Navigation());
        map.addControl(new DragPan());
     
 
        //Center and zoom to a location
        LonLat lonLat = new LonLat(2.2945, 48.8582);
        lonLat.transform(DEFAULT_PROJECTION.getProjectionCode(),
                         map.getProjection()); //transform lonlat to OSM coordinate system
        map.setCenter(lonLat, 16);
        
        MousePositionOutput mpOut = new MousePositionOutput() {
            @Override
            public String format(LonLat lonLat, Map map) {
                String out = "";
                out += "<b>Longitude </b> ";
                out += doubleFormatter.format(lonLat.lon());
                out += "<b>, Latitude</b> ";
                out += doubleFormatter.format(lonLat.lat());
 
                return out;
            }
        };
        
        MousePositionOptions mpOptions = new MousePositionOptions();
        mpOptions.setFormatOutput(mpOut); // rename to setFormatOutput
        
        map.addControl(new MousePosition(mpOptions));
       
        
        
        
        
        vectorLayer = new Vector("Vector layer");
        map.addLayer(vectorLayer);
        map.addControl(new EditingToolbar(vectorLayer)); 
        
        DrawFeatureOptions drawRegularPolygonFeatureOptions = new DrawFeatureOptions();
		drawRegularPolygonFeatureOptions.onFeatureAdded(new LocalListener());
		RegularPolygonHandlerOptions regularPolygonHandlerOptions = new RegularPolygonHandlerOptions();
		regularPolygonHandlerOptions.setSides(4);
		regularPolygonHandlerOptions.setIrregular(true);
		drawRegularPolygonFeatureOptions.setHandlerOptions(regularPolygonHandlerOptions);
		drawRectangleFeatureControl = new DrawFeature(vectorLayer, new RegularPolygonHandler(), drawRegularPolygonFeatureOptions);
        map.addControl(drawRectangleFeatureControl);
        drawRectangleFeatureControl.activate();
 

//    final DockLayoutPanel dock = new DockLayoutPanel(Unit.PX);
//    dock.addNorth(map, 500);

    
    mapPanel.add(mapWidget);

	}

	class LocalListener implements FeatureAddedListener
	{

		@Override
		public void onFeatureAdded(VectorFeature vectorFeature) {
			if (currentFeature != null)
			{
				currentFeature.destroy();
			}
			Bounds bounds = vectorFeature.getGeometry().getBounds();
			LonLat lowerLeft = new LonLat(bounds.getLowerLeftX(), bounds.getLowerLeftY());
			LonLat upperRight = new LonLat(bounds.getUpperRightX(), bounds.getUpperRightY());
			lowerLeft.transform(map.getProjection(), DEFAULT_PROJECTION.getProjectionCode());
			upperRight.transform(map.getProjection(), DEFAULT_PROJECTION.getProjectionCode());
			northBoundLatitude.setText(format(upperRight.lat()));
			eastBoundLongitude.setText(format(upperRight.lon()));
			southBoundLatitude.setText(format(lowerLeft.lat()));
			westBoundLongitude.setText(format(lowerLeft.lon()));
			currentFeature = vectorFeature;
		}
		
	}
	
	String format (double value)
	{
		return doubleFormatter.format(value);
	}
	
	public GeographicBoundingBoxDTO getGeographicBoundingBoxDTO()
	{
		GeographicBoundingBoxDTO result = new GeographicBoundingBoxDTO();
		result.setEastBoundLongitude(eastBoundLongitude.getText());
		result.setWestBoundLongitude(westBoundLongitude.getText());
		result.setSouthBoundLatitude(southBoundLatitude.getText());
		result.setNorthBoundLatitude(northBoundLatitude.getText());
		return result;
	}

	public void setGeographicBoundingBoxDTO(GeographicBoundingBoxDTO box)
	{
		if (currentFeature != null)
		{
			currentFeature.destroy();
			currentFeature = null;
		}
		
		northBoundLatitude.setText(box.getNorthBoundLatitude());
		southBoundLatitude.setText(box.getSouthBoundLatitude());
		eastBoundLongitude.setText(box.getEastBoundLongitude());
		westBoundLongitude.setText(box.getWestBoundLongitude());
		
		
//		Point[] points = new Point[4]; 
//		VectorFeature rect = new VectorFeature(geometry);
//		vectorLayer.addFeature(rect);
	}

	public void reset() {
		northBoundLatitude.setText("");
		southBoundLatitude.setText("");
		eastBoundLongitude.setText("");
		westBoundLongitude.setText("");
		
		if (currentFeature != null)
		{
			currentFeature.destroy();
		}
	}

}
