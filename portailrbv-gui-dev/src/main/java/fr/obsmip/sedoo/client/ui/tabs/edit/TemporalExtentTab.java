package fr.obsmip.sedoo.client.ui.tabs.edit;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;

import fr.obsmip.sedoo.client.CellTableResources;
import fr.obsmip.sedoo.client.Constants;
import fr.obsmip.sedoo.client.domain.metadata.MetadataDTO;
import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.misc.DateTools;
import fr.obsmip.sedoo.client.ui.misc.Tooltip;
import fr.obsmip.sedoo.client.ui.misc.customdatepicker.RBVDateBox;

public class TemporalExtentTab extends AbstractTab{

	private static TemporalExtentTabUiBinder uiBinder = GWT
			.create(TemporalExtentTabUiBinder.class);

	interface TemporalExtentTabUiBinder extends
			UiBinder<Widget, TemporalExtentTab> {
	}

	@UiField
	RBVDateBox startDate;
	
	@UiField
	RBVDateBox endDate;
	
	@UiField
	Label startDateDisplay;
	
	@UiField
	Label endDateDisplay;
	
	@UiField
	Tooltip startDateTooltip;
	
	@UiField
	Tooltip endDateTooltip;
	
//	@UiField
	CheckBox isLinked;
	
//	@UiField
//	RBVDateBox lastModificationDate;
	
	@UiField
	RadioButton nowEndDate;
	
	@UiField
	RadioButton explicitEndDate;
	
	public TemporalExtentTab() {
		initWidget(uiBinder.createAndBindUi(this));
		CellTableResources.INSTANCE.cellTableStyle().ensureInjected();
		editWidgets.add(startDate);
		editWidgets.add(endDate);
		editWidgets.add(nowEndDate);
		editWidgets.add(startDateTooltip);
		editWidgets.add(endDateTooltip	);
		editWidgets.add(explicitEndDate);
		displayWidgets.add(startDateDisplay);
		displayWidgets.add(endDateDisplay);
		reset();
	}

	public void reset() {
	
		startDate.setValue(null);
		endDate.setValue(null);
		explicitEndDate.setValue(true);
		endDate.setEnabled(true);
		startDateDisplay.setText("");
		endDateDisplay.setText("");
	}

	@Override
	public void edit(MetadataDTO metadata) 
	{
		reset();
		enableEditMode();
		String aux = metadata.getTemporalExtentPart().getStartDate();
		if (aux != null)
		{
			try
			{
				startDate.setValue(DateTools.getRBVDateFormat().parse(metadata.getTemporalExtentPart().getStartDate()));
			}
			catch (IllegalArgumentException e)
			{
				startDate.setValue(null);
			}
		}
		aux = metadata.getTemporalExtentPart().getEndDate();
		if (aux != null)
		{
			if (aux.compareTo(Constants.NOW)==0)
			{
				endDate.setEnabled(false);
				nowEndDate.setValue(true);
			}
			else
			{
				try
				{
					endDate.setValue(DateTools.getRBVDateFormat().parse(metadata.getTemporalExtentPart().getEndDate()));
				}
				catch (IllegalArgumentException e)
				{
					endDate.setValue(null);
				}
			}
		}
	}

	@Override
	public MetadataDTO flush(MetadataDTO metadataDTO) 
	{
		if (nowEndDate.getValue() == true)
		{
			metadataDTO.getTemporalExtentPart().setEndDate(Constants.NOW);
		}
		else
		{
			if (endDate.getValue() == null)
			{
				metadataDTO.getTemporalExtentPart().setEndDate(null);
			}
			else
			{
				metadataDTO.getTemporalExtentPart().setEndDate(DateTools.getRBVDateFormat().format(endDate.getValue()));
			}
		}
		if (startDate.getValue() == null)
		{
			metadataDTO.getTemporalExtentPart().setStartDate(null);
		}
		else
		{
			metadataDTO.getTemporalExtentPart().setStartDate(DateTools.getRBVDateFormat().format(startDate.getValue()));
		}
		return metadataDTO;
	}
	
	 @UiHandler("nowEndDate")
	  void onNowEndDateClicked(ClickEvent event) {
		 endDate.setEnabled(false);
	  }
	 
	 @UiHandler("explicitEndDate")
	  void onExplicitEndDateClicked(ClickEvent event) {
		 endDate.setEnabled(true);
	  }
	 
	 @Override
	 public void display(MetadataDTO metadata) 
	 {
		 reset();
		 enableDisplayMode();
		 String aux = metadata.getTemporalExtentPart().getStartDate();
		 if (aux != null)
		 {
			 startDateDisplay.setText(metadata.getTemporalExtentPart().getStartDate());	 
		 }
		 aux = metadata.getTemporalExtentPart().getEndDate();
		 if (aux != null)
		 {
			 if (aux.compareTo(Constants.NOW)==0)
			 {
				 endDateDisplay.setText(Message.INSTANCE.now());
			 }
			 else
			 {
				 endDateDisplay.setText(metadata.getTemporalExtentPart().getEndDate());
			 }
		 }
	 }
}

