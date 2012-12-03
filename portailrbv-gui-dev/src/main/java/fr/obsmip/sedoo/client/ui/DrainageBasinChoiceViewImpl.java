package fr.obsmip.sedoo.client.ui;

import java.util.Iterator;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

import fr.obsmip.sedoo.client.domain.DrainageBasinDTO;
import fr.obsmip.sedoo.client.domain.ObservatoryDTO;

public class DrainageBasinChoiceViewImpl extends AbstractSection implements DrainageBasinChoiceView {
 	
	Presenter presenter;
	
	@UiField
	ListBox observatories;
	
	@UiField
	ListBox drainageBasins;
	
	@UiField 
	Button createButton;
	
	private static DrainageBasinChoiceViewImplUiBinder uiBinder = GWT
			.create(DrainageBasinChoiceViewImplUiBinder.class);

	interface DrainageBasinChoiceViewImplUiBinder extends UiBinder<Widget, DrainageBasinChoiceViewImpl> {
	}

	public DrainageBasinChoiceViewImpl() {
		super();
		initWidget(uiBinder.createAndBindUi(this));
		applyCommonStyle();
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
		
	}
	
	@UiHandler("observatories")
	void onObservatoriesChanged(ChangeEvent event) {
		loadDrainageBasins(new Long(observatories.getValue(observatories.getSelectedIndex())));
	}
	
	@UiHandler("createButton")
	  void onCreateButtonClicked(ClickEvent event) {
	  presenter.createMetadata(new Long(drainageBasins.getValue(drainageBasins.getSelectedIndex())));
	}

	@Override
	public void reset()
	{
		drainageBasins.clear();
		drainageBasins.clear();
		drainageBasins.setEnabled(false);
		createButton.setEnabled(false);
	}

	@Override
	public void setObservatories(List<ObservatoryDTO> observatoriesList) 
	{
		observatories.clear();
		Iterator<ObservatoryDTO> iterator = observatoriesList.iterator();
		while (iterator.hasNext()) 
		{
			ObservatoryDTO aux = iterator.next();
			observatories.addItem(aux.getShortLabel(),""+aux.getId());
		}
		if (observatoriesList.isEmpty() == false)
		{
			loadDrainageBasins(new Long(observatories.getValue(0)));
		}
	}

	private void loadDrainageBasins(Long index) {

		drainageBasins.clear();
		drainageBasins.setEnabled(false);
		createButton.setEnabled(false);
		presenter.getDrainageBasins(index);
		
	}

	@Override
	public void setDrainageBasins(List<DrainageBasinDTO> drainageBasinsList) 
	{
		if (drainageBasinsList.isEmpty() == false)
		{
			drainageBasins.setEnabled(true);
			createButton.setEnabled(true);
			drainageBasins.clear();
			Iterator<DrainageBasinDTO> iterator = drainageBasinsList.iterator();
			while (iterator.hasNext()) 
			{
				DrainageBasinDTO aux = iterator.next();
				drainageBasins.addItem(aux.getLabel(), ""+aux.getId());
			}
		}
		
	}

}

