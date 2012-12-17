package fr.obsmip.sedoo.client.ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

import fr.obsmip.sedoo.client.domain.DrainageBasinDTO;
import fr.obsmip.sedoo.client.domain.ExtendedSummaryDTO;
import fr.obsmip.sedoo.client.domain.ObservatoryDTO;
import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.ui.table.ExtendedSummaryDTOTable;

public class DrainageBasinChoiceViewImpl extends AbstractSection implements DrainageBasinChoiceView {
 	
	private static final String UNSELECTED_ID = "NONE";

	Presenter presenter;
	
	@UiField
	ListBox observatories;
	
	@UiField
	ListBox drainageBasins;
	
	@UiField 
	ExtendedSummaryDTOTable table;
	
	private static DrainageBasinChoiceViewImplUiBinder uiBinder = GWT
			.create(DrainageBasinChoiceViewImplUiBinder.class);

	interface DrainageBasinChoiceViewImplUiBinder extends UiBinder<Widget, DrainageBasinChoiceViewImpl> {
	}

	public DrainageBasinChoiceViewImpl() {
		super();
		initWidget(uiBinder.createAndBindUi(this));
		applyCommonStyle();
		reset();
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
		table.setPresenter(presenter);
		
	}
	
	@UiHandler("observatories")
	void onObservatoriesChanged(ChangeEvent event) {
		String aux = observatories.getValue(observatories.getSelectedIndex());
		if (aux.compareTo(UNSELECTED_ID)==0)
		{
			reset();
		}
		else
		{
			loadDrainageBasins(new Long(aux));
		}
	}
	
	@UiHandler("drainageBasins")
	void onDrainageBasinsChanged(ChangeEvent event) {
		String aux = drainageBasins.getValue(drainageBasins.getSelectedIndex());
		if (aux.compareTo(UNSELECTED_ID)==0)
		{
			table.setDrainageBasinId(null);
			table.init(new ArrayList<ExtendedSummaryDTO>());
			
		}
		else
		{
			table.setDrainageBasinId(new Long(aux));
			presenter.getEntries(new Long(aux));
		}
	}
	
	/*@UiHandler("createButton")
	  void onCreateButtonClicked(ClickEvent event) {
	  presenter.createMetadata(new Long(drainageBasins.getValue(drainageBasins.getSelectedIndex())));
	}*/

	@Override
	public void reset()
	{
		drainageBasins.clear();
		drainageBasins.setEnabled(false);
		table.init(new ArrayList<ExtendedSummaryDTO>());
		table.setDrainageBasinId(null);
	}

	@Override
	public void setObservatories(List<ObservatoryDTO> observatoriesList) 
	{
		observatories.clear();
		observatories.addItem(Message.INSTANCE.selectItem(), UNSELECTED_ID);
		table.setDrainageBasinId(null);
		Iterator<ObservatoryDTO> iterator = observatoriesList.iterator();
		while (iterator.hasNext()) 
		{
			ObservatoryDTO aux = iterator.next();
			observatories.addItem(aux.getShortLabel(),""+aux.getId());
		}
		reset();
	}

	private void loadDrainageBasins(Long index) {

		drainageBasins.clear();
		drainageBasins.setEnabled(false);
		presenter.getDrainageBasins(index);
		
	}

	@Override
	public void setDrainageBasins(List<DrainageBasinDTO> drainageBasinsList) 
	{
		if (drainageBasinsList.isEmpty() == false)
		{
			drainageBasins.setEnabled(true);
			drainageBasins.clear();
			drainageBasins.addItem(Message.INSTANCE.selectItem(), UNSELECTED_ID);
			table.setDrainageBasinId(null);
			Iterator<DrainageBasinDTO> iterator = drainageBasinsList.iterator();
			while (iterator.hasNext()) 
			{
				DrainageBasinDTO aux = iterator.next();
				drainageBasins.addItem(aux.getLabel(), ""+aux.getId());
			}
		}
		
	}

	@Override
	public void setEntries(List<ExtendedSummaryDTO> summaries) {
		table.init(summaries);
	}

}

