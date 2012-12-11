package fr.obsmip.sedoo.client.ui.misc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.gwtopenmaps.openlayers.client.Bounds;
import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.Map;
import org.gwtopenmaps.openlayers.client.MapOptions;
import org.gwtopenmaps.openlayers.client.MapWidget;
import org.gwtopenmaps.openlayers.client.Projection;
import org.gwtopenmaps.openlayers.client.Style;
import org.gwtopenmaps.openlayers.client.control.DragPan;
import org.gwtopenmaps.openlayers.client.control.DrawFeature;
import org.gwtopenmaps.openlayers.client.control.DrawFeature.FeatureAddedListener;
import org.gwtopenmaps.openlayers.client.control.DrawFeatureOptions;
import org.gwtopenmaps.openlayers.client.control.LayerSwitcher;
import org.gwtopenmaps.openlayers.client.control.MousePosition;
import org.gwtopenmaps.openlayers.client.control.MousePositionOptions;
import org.gwtopenmaps.openlayers.client.control.MousePositionOutput;
import org.gwtopenmaps.openlayers.client.control.Navigation;
import org.gwtopenmaps.openlayers.client.control.OverviewMap;
import org.gwtopenmaps.openlayers.client.control.ScaleLine;
import org.gwtopenmaps.openlayers.client.control.SelectFeature;
import org.gwtopenmaps.openlayers.client.control.SelectFeature.ClickFeatureListener;
import org.gwtopenmaps.openlayers.client.control.SelectFeatureOptions;
import org.gwtopenmaps.openlayers.client.event.VectorFeatureAddedListener;
import org.gwtopenmaps.openlayers.client.event.VectorFeatureSelectedListener;
import org.gwtopenmaps.openlayers.client.event.VectorFeatureUnselectedListener;
import org.gwtopenmaps.openlayers.client.feature.VectorFeature;
import org.gwtopenmaps.openlayers.client.geometry.Point;
import org.gwtopenmaps.openlayers.client.handler.PointHandler;
import org.gwtopenmaps.openlayers.client.handler.RegularPolygonHandler;
import org.gwtopenmaps.openlayers.client.handler.RegularPolygonHandlerOptions;
import org.gwtopenmaps.openlayers.client.layer.GoogleV3;
import org.gwtopenmaps.openlayers.client.layer.GoogleV3MapType;
import org.gwtopenmaps.openlayers.client.layer.GoogleV3Options;
import org.gwtopenmaps.openlayers.client.layer.Vector;
import org.gwtopenmaps.openlayers.client.util.Attributes;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.obsmip.sedoo.client.GlobalBundle;
import fr.obsmip.sedoo.client.domain.GeographicBoundingBoxDTO;
import fr.obsmip.sedoo.client.domain.SiteDTO;
import fr.obsmip.sedoo.client.message.Message;

public class MapSelector extends Composite implements ClickHandler, SiteEventListener
{

	private static MapSelectorUiBinder uiBinder = GWT.create(MapSelectorUiBinder.class);
	
	private static final Projection DEFAULT_PROJECTION = new Projection("EPSG:4326");
	private static final String ID_ATTRIBUTE = "localId";
	
	@UiField
	TextBox northBoundLatitude;
	
	@UiField
	TextBox southBoundLatitude;
	
	@UiField
	TextBox eastBoundLongitude;
	
	@UiField
	TextBox westBoundLongitude;
	
	private  Map map;
	 
	private  VerticalPanel mapPanel;
	
	private SiteEventListener siteEventListener;
	
	@UiField
	VerticalPanel contentPanel;
	
	ToggleButton drawDrainageBasinButton;
	ToggleButton eraseDrainageBasinButton;
	ToggleButton drawSiteButton;
	ToggleButton eraseSiteButton;
	ToggleButton dragPanButton;
	
	List<ToggleButton> buttonList;
	
	private DrawFeature drawDrainageBasinControl = null;
	private DrawFeature drawSiteControl = null;
	private SelectFeature hoverSiteSelectFeature = null;
	private SelectFeature hoverDrainageBasinSelectFeature = null;
	
	private NumberFormat doubleFormatter = NumberFormat.getFormat("#.0000");
	
	VectorFeature drainageBassinFeature;
	
	Vector drainageBasinLayer;
	Vector siteLayer;

	private HorizontalPanel toolBar;

	private Style siteStyle;
	private Style selectedSiteStyle;
	private SiteIdProvider siteIdProvider;

	interface MapSelectorUiBinder extends UiBinder<Widget, MapSelector> {
	}

	@UiConstructor
	public MapSelector(boolean complete) {
		initWidget(uiBinder.createAndBindUi(this));
		MapOptions defaultMapOptions = new MapOptions();
		defaultMapOptions.setDisplayProjection(new Projection("EPSG:4326"));
        defaultMapOptions.setNumZoomLevels(20);
    
        MapWidget mapWidget = new MapWidget("500px", "500px", defaultMapOptions);
        
        siteStyle = new Style();
        siteStyle.setExternalGraphic("http://maps.google.com/mapfiles/ms/micons/blue.png");
        siteStyle.setGraphicSize(20, 20);
        siteStyle.setFillOpacity(1);
        siteStyle.setGraphicOffset(-9, -20);
        siteStyle.setCursor("default");
        
        selectedSiteStyle = new Style();
        selectedSiteStyle.setExternalGraphic("http://maps.google.com/mapfiles/ms/micons/red.png");
        selectedSiteStyle.setGraphicSize(20, 20);
        selectedSiteStyle.setFillOpacity(1);
        selectedSiteStyle.setGraphicOffset(-9, -20);
        selectedSiteStyle.setCursor("pointer");
        
        
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
         
        map = mapWidget.getMap();
        map.addLayer(gHybrid);
        map.addLayer(gNormal);
        map.addLayer(gSatellite);
        map.addLayer(gTerrain);
 
        //Lets add some default controls to the map
        map.addControl(new LayerSwitcher()); //+ sign in the upperright corner to display the layer switcher
        map.addControl(new OverviewMap()); //+ sign in the lowerright to display the overviewmap
        map.addControl(new ScaleLine()); //Display the scaleline
        map.addControl(new Navigation());
        map.addControl(new DragPan());
        
        
        MousePositionOutput mpOut = new MousePositionOutput() {
            @Override
            public String format(LonLat lonLat, Map map) {
                String out = "";
                out += "<span style=\"color:white;background-color:black\"><b>Longitude </b> ";
                out += doubleFormatter.format(lonLat.lon());
                out += "<b>, Latitude</b> ";
                out += doubleFormatter.format(lonLat.lat());
                out += "</span>";
                return out;
            }
        };
        
        MousePositionOptions mpOptions = new MousePositionOptions();
        mpOptions.setFormatOutput(mpOut); // rename to setFormatOutput
        
        map.addControl(new MousePosition(mpOptions));
       
        
        drainageBasinLayer = new Vector("DrainageBasinLayer");
        siteLayer = new Vector("SiteLayer");
        
        map.addLayer(drainageBasinLayer);
        map.addLayer(siteLayer);
        
        SiteLayerListener siteLayerListener = new SiteLayerListener();
		 DrainageBasinLayerListener drainageBasinLayerListener = new DrainageBasinLayerListener();
        
        // Contrôle gérant le dessin du rectangle du bassin versant
        DrawFeatureOptions drainageBassinOptions = new DrawFeatureOptions();
		drainageBassinOptions.onFeatureAdded(drainageBasinLayerListener);
		RegularPolygonHandlerOptions regularPolygonHandlerOptions = new RegularPolygonHandlerOptions();
		regularPolygonHandlerOptions.setSides(4);
		regularPolygonHandlerOptions.setIrregular(true);
		drainageBassinOptions.setHandlerOptions(regularPolygonHandlerOptions);
		drawDrainageBasinControl = new DrawFeature(drainageBasinLayer, new RegularPolygonHandler(), drainageBassinOptions);
		
		// Contrôle gérant le dessin des sites			
		PointHandler pointHandler = new PointHandler();
		drawSiteControl = new DrawFeature(siteLayer, pointHandler);
        
		// Contrôle gérant la selection des sites 
		SelectFeatureOptions siteHoverSelectFeatureOptions = new SelectFeatureOptions();
		siteHoverSelectFeatureOptions.setHover();
		siteHoverSelectFeatureOptions.clickFeature(siteLayerListener);
        hoverSiteSelectFeature = new SelectFeature(siteLayer,siteHoverSelectFeatureOptions);
        
        // Contrôle gérant la selection du bassin versant 
        SelectFeatureOptions drainageBasinHoverSelectFeatureOptions = new SelectFeatureOptions();
        drainageBasinHoverSelectFeatureOptions.setHover();
        drainageBasinHoverSelectFeatureOptions.clickFeature(drainageBasinLayerListener);
        hoverDrainageBasinSelectFeature = new SelectFeature(drainageBasinLayer,drainageBasinHoverSelectFeatureOptions);
		
		drawDrainageBasinControl.deactivate();
        drawSiteControl.deactivate();
        hoverSiteSelectFeature.deactivate();
        hoverDrainageBasinSelectFeature.deactivate();

        map.addControl(drawDrainageBasinControl);
        map.addControl(drawSiteControl);
        map.addControl(hoverSiteSelectFeature);
        map.addControl(hoverDrainageBasinSelectFeature);
        
        siteLayer.addVectorFeatureAddedListener(siteLayerListener);
        siteLayer.addVectorFeatureSelectedListener(siteLayerListener);
        siteLayer.addVectorFeatureUnselectedListener(siteLayerListener);
        
//    final DockLayoutPanel dock = new DockLayoutPanel(Unit.PX);
//    dock.addNorth(map, 500);

     // force the map to fall behind popups
        mapWidget.getElement().getFirstChildElement().getStyle().setZIndex(0);

        mapPanel = new VerticalPanel();
        mapPanel.add(mapWidget);

        mapWidget.addDomHandler(new MapMouseOverHandler(), MouseOverEvent.getType());
        toolBar = new HorizontalPanel();
        toolBar.setStylePrimaryName("map-selector-toolbar");
        toolBar.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        toolBar.setSpacing(3);  
        drawDrainageBasinButton = new ToggleButton(new Image(GlobalBundle.INSTANCE.drainageBasinDraw()));
        drawDrainageBasinButton.setTitle(Message.INSTANCE.drawDrainageBasinButtonTooltip());
        eraseDrainageBasinButton = new ToggleButton(new Image(GlobalBundle.INSTANCE.drainageBasinDelete()));
        eraseDrainageBasinButton.setTitle(Message.INSTANCE.eraseDrainageBasinButtonTooltip());
        drawSiteButton = new ToggleButton(new Image(GlobalBundle.INSTANCE.siteDraw()));
        drawSiteButton.setTitle(Message.INSTANCE.drawSiteButtonTooltip());
        eraseSiteButton = new ToggleButton(new Image(GlobalBundle.INSTANCE.siteDelete()));
        eraseSiteButton.setTitle(Message.INSTANCE.eraseSiteButtonTooltip());
        dragPanButton = new ToggleButton(new Image(GlobalBundle.INSTANCE.drag()));
        dragPanButton.setTitle(Message.INSTANCE.dragPanButtonTooltip());
        buttonList = new ArrayList<ToggleButton>();
        buttonList.add(drawDrainageBasinButton);
        buttonList.add(eraseDrainageBasinButton);

        if (complete == true)
        {
        	buttonList.add(drawSiteButton);
        	buttonList.add(eraseSiteButton);
        }
        buttonList.add(dragPanButton);
        init(buttonList);

        dragPanButton.setDown(true);
        contentPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        contentPanel.add(toolBar);
        contentPanel.add(mapPanel);

	}

	private void init(List<ToggleButton> buttonList) 
	{
		Iterator<ToggleButton> iterator = buttonList.iterator();
		while (iterator.hasNext()) {
			ToggleButton toggleButton = iterator.next();
			toggleButton.addClickHandler(this);
			toolBar.add(toggleButton);
			
		}
	}
	
	/**
	 * This handler force the updateSize commande whenever the mouse enters the map. It avoids the offset between the mouse pointer and drawn feature in case of an inner scrolled div  
	 * @author francois
	 * 
	 */
	class MapMouseOverHandler implements MouseOverHandler
	{

		@Override
		public void onMouseOver(MouseOverEvent event) {
			map.updateSize();
		}
		
	}
	
	class SiteLayerListener implements VectorFeatureAddedListener, VectorFeatureSelectedListener, VectorFeatureUnselectedListener, ClickFeatureListener
	{
		@Override
		public void onFeatureAdded(FeatureAddedEvent eventObject) {
			
			eventObject.getVectorFeature().setStyle(siteStyle);
			
			Attributes attributes = eventObject.getVectorFeature().getAttributes();
			if (attributes.getAttributeAsFloat(ID_ATTRIBUTE) == 0)
			{
				Long newId = siteIdProvider.getNewId();
				eventObject.getVectorFeature().getAttributes().setAttribute(ID_ATTRIBUTE, newId);
				SiteDTO aux = new SiteDTO();
				aux.setId(newId);
				aux.setName("Site "+newId);
				LonLat center = eventObject.getVectorFeature().getCenterLonLat();
				center.transform(map.getProjection(), DEFAULT_PROJECTION.getProjectionCode());
				aux.setLongitude(format(center.lon()));
				aux.setLatitude(format(center.lat()));
				siteEventListener.siteAdded(aux);
			}
			siteLayer.redraw();
			
		}
		
		public void onFeatureSelected(FeatureSelectedEvent eventObject) {
			eventObject.getVectorFeature().setStyle(selectedSiteStyle);
			siteLayer.redraw();
        }

		@Override
		public void onFeatureUnselected(FeatureUnselectedEvent eventObject) {
			eventObject.getVectorFeature().setStyle(siteStyle);
			siteLayer.redraw();
			
		}

		@Override
		public void onFeatureClicked(VectorFeature vectorFeature) {
			Long id = (long) vectorFeature.getAttributes().getAttributeAsFloat(ID_ATTRIBUTE);
			vectorFeature.destroy();
			siteEventListener.siteRemoved(id);
		}
	}
	
	class DrainageBasinLayerListener implements ClickFeatureListener, FeatureAddedListener
	{

		@Override
		public void onFeatureClicked(VectorFeature vectorFeature) {
			vectorFeature.destroy();
			
			northBoundLatitude.setText("");
			southBoundLatitude.setText("");
			eastBoundLongitude.setText("");
			westBoundLongitude.setText("");
			drainageBassinFeature = null;
		}
		
		@Override
		public void onFeatureAdded(VectorFeature vectorFeature) {
			if (drainageBassinFeature != null)
			{
				drainageBassinFeature.destroy();
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
			drainageBassinFeature = vectorFeature;
			
			//On rebascule en mode "drag" afin d'éviter une fausse manip de l'utilisateur
			drawDrainageBasinControl.deactivate();
			dragPanButton.setDown(true);
			drawDrainageBasinButton.setDown(false);
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
		if (drainageBassinFeature != null)
		{
			drainageBassinFeature.destroy();
			drainageBassinFeature = null;
		}
		
		northBoundLatitude.setText(box.getNorthBoundLatitude());
		southBoundLatitude.setText(box.getSouthBoundLatitude());
		eastBoundLongitude.setText(box.getEastBoundLongitude());
		westBoundLongitude.setText(box.getWestBoundLongitude());
		
		center(box);
		
//		Point[] points = new Point[4]; 
//		VectorFeature rect = new VectorFeature(geometry);
//		vectorLayer.addFeature(rect);
	}
	
	
	public void reset() {
		
		//RAZ des zones de textes
		northBoundLatitude.setText("");
		southBoundLatitude.setText("");
		eastBoundLongitude.setText("");
		westBoundLongitude.setText("");
		
		//Suppression du basin versant
		drainageBasinLayer.destroyFeatures();
		
		//Suppression des sites
		siteLayer.destroyFeatures();
		
		deactivateAllControls();
	}
	
	public void center(GeographicBoundingBoxDTO box)
	{
		
		LonLat rightLowerDisplay;
	    LonLat leftUpperDisplay; 
		
		if (box.validate().isEmpty())
		{
			
		    LonLat rightLower = box.getRightLowerCorner();
		    LonLat leftUpper = box.getLeftUpperCorner();
		    
		    rightLowerDisplay = box.getRightLowerDisplayCorner();
		    leftUpperDisplay = box.getLeftUpperDisplayCorner();
		    
		    rightLower.transform(DEFAULT_PROJECTION.getProjectionCode(),map.getProjection()); 
		    leftUpper.transform(DEFAULT_PROJECTION.getProjectionCode(),map.getProjection()); 
		    
		    
		    Bounds drainageBasinBounds = new Bounds();		    
		    drainageBasinBounds.extend(rightLower);
		    drainageBasinBounds.extend(leftUpper);
		    drainageBassinFeature = new VectorFeature(drainageBasinBounds.toGeometry());
			drainageBasinLayer.addFeature(drainageBassinFeature);
		}
		else
		{
			//On affiche une carte complète du monde par défaut		    
		    rightLowerDisplay = new LonLat(-175, -85);
		    leftUpperDisplay = new LonLat(175, 85);
		}
		
		rightLowerDisplay.transform(DEFAULT_PROJECTION.getProjectionCode(),map.getProjection()); 
	    leftUpperDisplay.transform(DEFAULT_PROJECTION.getProjectionCode(),map.getProjection());
	    
	    Bounds displayBounds = new Bounds();		    
	    displayBounds.extend(rightLowerDisplay);
	    displayBounds.extend(leftUpperDisplay);
	    map.zoomToExtent(displayBounds, true);
	}

	@Override
	public void onClick(ClickEvent event) {
		if (event.getSource() instanceof ToggleButton)
		{
			Iterator<ToggleButton> iterator = buttonList.iterator();
			while (iterator.hasNext()) 
			{
				ToggleButton toggleButton = (ToggleButton) iterator.next();
				toggleButton.setDown(false);
			}
			((ToggleButton) event.getSource()).setDown(true);
			
			
			deactivateAllControls();
			
			if (event.getSource()==drawDrainageBasinButton)
			{
				drawDrainageBasinControl.activate();
			}
			
			if (event.getSource()==drawSiteButton)
			{
				drawSiteControl.activate();
			}
			
			if (event.getSource()==eraseSiteButton)
			{
				hoverSiteSelectFeature.activate();
			}
			
			if (event.getSource()==eraseDrainageBasinButton)
			{
				hoverDrainageBasinSelectFeature.activate();
			}
			
		}
	}

	private void deactivateAllControls() 
	{
		drawDrainageBasinControl.deactivate();
		drawSiteControl.deactivate();
		hoverSiteSelectFeature.deactivate();
		hoverDrainageBasinSelectFeature.deactivate();
	}

	public void setSites(List<SiteDTO> sitesDTO) 
	{
		Iterator<SiteDTO> iterator = sitesDTO.iterator();
		while (iterator.hasNext()) {
			addSite(iterator.next());
		}
	}
	
	
	private void addSite(SiteDTO siteDTO)
	{
		Projection destProjection = new Projection(map.getProjection());
		Point point = new Point(NumberFormat.getDecimalFormat().parse(siteDTO.getLongitude()), NumberFormat.getDecimalFormat().parse(siteDTO.getLatitude()));
		point.transform(DEFAULT_PROJECTION, destProjection);
	    VectorFeature pointFeature = new VectorFeature(point, selectedSiteStyle);
	    Attributes attributes = pointFeature.getAttributes();
	    attributes.setAttribute(ID_ATTRIBUTE, siteDTO.getId());
	    pointFeature.setAttributes(attributes);
	    siteLayer.addFeature(pointFeature);
	}
	

	public void setSiteEventListener(SiteEventListener listener) 
	{
		siteEventListener = listener;
	}
	
	public void setSiteIdProvider(SiteIdProvider siteIdProvider) 
	{
		this.siteIdProvider = siteIdProvider;
	}

	@Override
	public void siteAdded(SiteDTO site) 
	{
		VectorFeature[] features = siteLayer.getFeatures();
		for (int i = 0; i < features.length; i++) {
			if (features[i].getAttributes().getAttributeAsFloat(ID_ATTRIBUTE)==site.getId())
			{
				features[i].destroy();
				addSite(site);
				return;
			}
		}
		
		//New site
		addSite(site);
	}

	@Override
	public void siteRemoved(Long id) {
		VectorFeature[] features = siteLayer.getFeatures();
		for (int i = 0; i < features.length; i++) {
			if (features[i].getAttributes().getAttributeAsFloat(ID_ATTRIBUTE)==id)
			{
				features[i].destroy();
				return;
			}
		}
	}
	
}
