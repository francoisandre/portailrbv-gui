package fr.obsmip.sedoo.client.ui.table;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.cellview.client.Column;

import fr.obsmip.sedoo.client.domain.AbstractDTO;
import fr.obsmip.sedoo.client.domain.HasId;
import fr.obsmip.sedoo.client.domain.SiteDTO;
import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.ui.misc.SiteEventListener;
import fr.obsmip.sedoo.client.ui.misc.SiteIdProvider;
import fr.obsmip.sedoo.client.ui.misc.WaterMarkCell;


public class SiteTable extends AbstractTable implements SiteEventListener, SiteIdProvider {

	private SiteEventListener siteEventListener;
	
	public SiteTable() {
		super();
		setAskDeletionConfirmation(false);
		setAddButtonEnabled(true);
		localInitColumns();
	}

	@Override
	public void addItem() {
		addRow(null);
	}

	@Override
	public String getAddItemText() {
		return Message.INSTANCE.SiteTableAddItemText();	
	}

	@Override
	public void presenterDelete(Long id) {
		removeRow(id);	
		getSiteEventListener().siteRemoved(id);
	}

	@Override
	public void presenterEdit(Long id) {
		//presenter.editDrainageBasin(id);
		System.out.println("edit");
		
	}

	@Override
	protected void initColumns() {
		//Nothing is done there
	}
		
	protected void localInitColumns() {
		
		Column<HasId, String> nameColumn = new Column<HasId, String>(
				new WaterMarkCell( Message.INSTANCE.name(),"40","40")) {
			@Override
			public String getValue(HasId site) {
				return ((SiteDTO) site).getName();
			}
		};
		
		nameColumn.setFieldUpdater(new FieldUpdater<HasId, String>() {
			@Override
			public void update(int index, HasId site, String name) {
				((SiteDTO) site).setName(name);
			}
		});
		
		Column<HasId, String> longitudeColumn = new Column<HasId, String>(
				new WaterMarkCell( "Longitude","10","10")) {
			@Override
			public String getValue(HasId site) {
				return AbstractDTO.protectNullString(((SiteDTO) site).getLongitude());
			}
		};
		
		longitudeColumn.setFieldUpdater(new FieldUpdater<HasId, String>() {
			@Override
			public void update(int index, HasId site, String longitude) {
				((SiteDTO) site).setLongitude(longitude);
				updateSiteListener((SiteDTO) site);
			}
		});
		
		Column<HasId, String> latitudeColumn = new Column<HasId, String>(
				new WaterMarkCell( "Latitude","10","10")) {
			@Override
			public String getValue(HasId site) {
				return AbstractDTO.protectNullString(((SiteDTO) site).getLatitude());
			}
		};
		
		latitudeColumn.setFieldUpdater(new FieldUpdater<HasId, String>() {
			@Override
			public void update(int index, HasId site, String latitude) {
				((SiteDTO) site).setLatitude(latitude);
				updateSiteListener((SiteDTO) site);
			}
		});
		
		Column<HasId, String> altitudeColumn = new Column<HasId, String>(
				new WaterMarkCell( "Altitude","10","10")) {
			@Override
			public String getValue(HasId site) {
				return AbstractDTO.protectNullString(((SiteDTO) site).getAltitude());
			}
		};
		
		altitudeColumn.setFieldUpdater(new FieldUpdater<HasId, String>() {
			@Override
			public void update(int index, HasId site, String altitude) {
				((SiteDTO) site).setAltitude(altitude);
			}
		});
					
		itemTable.addColumn(nameColumn, Message.INSTANCE.name());
		itemTable.setColumnWidth(nameColumn, 100.0, Unit.PX);
		itemTable.addColumn(longitudeColumn, "Longitude");
		itemTable.setColumnWidth(longitudeColumn, 50.0, Unit.PX);
		itemTable.addColumn(latitudeColumn, "Latitude");
		itemTable.setColumnWidth(latitudeColumn, 50.0, Unit.PX);
		itemTable.addColumn(altitudeColumn, "Altitude");
		itemTable.setColumnWidth(altitudeColumn, 50.0, Unit.PX);
		itemTable.addColumn(deleteColumn);
		itemTable.setColumnWidth(deleteColumn, 30.0, Unit.PX);
		
	}

	@Override
	public void siteAdded(SiteDTO site) {
		addRow(site);
	}
	
	private void updateSiteListener(SiteDTO site)
	{
		if ((!AbstractDTO.isEmpty(site.getLatitude())) && (!AbstractDTO.isEmpty(site.getLongitude())))
		{
			if ((AbstractDTO.checkDouble(site.getLatitude())) && (AbstractDTO.checkDouble(site.getLongitude())))
			{
				siteEventListener.siteAdded(site);
			}
		}
	}

	private void addRow(SiteDTO site) 
	{
		List<SiteDTO> newValues = new ArrayList<SiteDTO>();
		Iterator<? extends HasId> iterator = model.iterator();
		while (iterator.hasNext()) 
		{
			SiteDTO aux = (SiteDTO) iterator.next();
			newValues.add(aux);
		}
	
		if (site != null)
		{
			newValues.add(site);
		}
		else
		{
			SiteDTO empty = new SiteDTO();
			empty.setId(getMaxId()+1);
			empty.setName("Site "+empty.getId());
			newValues.add(empty);
		}
		
		init(newValues);
		
	}

	@Override
	public void siteRemoved(Long id) {
		removeRow(id);	
	}

	@Override
	public Long getNewId() {
		return getMaxId()+1;
	}

	public SiteEventListener getSiteEventListener() {
		return siteEventListener;
	}

	public void setSiteEventListener(SiteEventListener siteEventListener) {
		this.siteEventListener = siteEventListener;
	}

	public List<SiteDTO> getSiteDTOs() {
		List<SiteDTO> result = new ArrayList<SiteDTO>();
		Iterator<? extends HasId> iterator = model.iterator();
		while (iterator.hasNext()) 
		{
			SiteDTO aux = (SiteDTO) iterator.next();
			result.add(aux);
		}
		return result;
	}

}
