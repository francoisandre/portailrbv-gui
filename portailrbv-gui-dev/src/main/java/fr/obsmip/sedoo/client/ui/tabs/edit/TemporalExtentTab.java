package fr.obsmip.sedoo.client.ui.tabs.edit;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;

import fr.obsmip.sedoo.client.CellTableResources;
import fr.obsmip.sedoo.client.Constants;
import fr.obsmip.sedoo.client.domain.metadata.MetadataDTO;
import fr.obsmip.sedoo.client.misc.DateTools;
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
		reset();
	}

	public void reset() {
	
		startDate.setValue(null);
		endDate.setValue(null);
		explicitEndDate.setValue(true);
		endDate.setEnabled(true);
	}

	@Override
	public void edit(MetadataDTO metadata) 
	{
		reset();
		String aux = metadata.getTemporalExtentPart().getEndDate();
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
		public void display(MetadataDTO metadata) {
			
		}
	
}
