package fr.obsmip.sedoo.client.domain.metadata;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.obsmip.sedoo.client.Constants;
import fr.obsmip.sedoo.client.domain.AbstractDTO;
import fr.obsmip.sedoo.client.domain.ValidationAlert;
import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.misc.DateTools;

public class TemporalExtentPart extends AbstractDTO{
	
	private String startDate;
	private String endDate;
	
	@Override
	public String getHash() {
		StringBuffer aux = new StringBuffer();
		aux.append("@"+AbstractDTO.protectNullString(getStartDate())+"|"+AbstractDTO.protectNullString(getEndDate())+"|");
		return aux.toString();
	}

	@Override
	public List<ValidationAlert> validate() {
		List<ValidationAlert> result = new ArrayList<ValidationAlert>();
		Date start = null;
		Date end = null;
		if(isEmpty(startDate))
		{
			result.add(new ValidationAlert(Message.INSTANCE.metadataEditingStartDate(), Message.INSTANCE.mandatoryData()));
		}
		else
		{
			try
			{
				start  = DateTools.getRBVDateFormat().parse(startDate);
			}
			catch (IllegalArgumentException e)
			{
				result.add(new ValidationAlert(Message.INSTANCE.metadataEditingStartDate(), Message.INSTANCE.dateData()));
			}
		}
		
		
		if(isEmpty(endDate))
		{
			result.add(new ValidationAlert(Message.INSTANCE.metadataEditingEndDate(), Message.INSTANCE.mandatoryData()));
			
		}
		else
		{
			if (endDate.compareTo(Constants.NOW) != 0)
			{
				try
				{
					end  = DateTools.getRBVDateFormat().parse(endDate);
				}
				catch (IllegalArgumentException e)
				{
					result.add(new ValidationAlert(Message.INSTANCE.metadataEditingEndDate(), Message.INSTANCE.dateData()));
				}
			}
		}
		
		
		if ((start != null) && (end != null)) 
		{
				if (DateTools.after(start, end) == false)
				{
					result.add(new ValidationAlert(Message.INSTANCE.metadataEditingStartDate().replace(":", "") +"-"+Message.INSTANCE.metadataEditingEndDate(), Message.INSTANCE.dateUnconsistency()));
				}
		}
		return result;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	
}
