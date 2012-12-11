package fr.obsmip.sedoo.client.domain;

import java.util.ArrayList;
import java.util.List;

import fr.obsmip.sedoo.client.message.Message;

public class SiteDTO extends AbstractDTO implements HasId {
	
	private Long id;
	private String name;
	private String latitude;
	private String longitude;
	private String altitude;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getAltitude() {
		return altitude;
	}
	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}
	
	public String getHash() {
		return "@"+protectNullString(getName())+"|"+protectNullString(getLongitude())+"|"+protectNullString(getLatitude())+"|"+protectNullString(getAltitude());
	}
	
	@Override
	public List<ValidationAlert> validate() {
		List<ValidationAlert> result = new ArrayList<ValidationAlert>();
		if (isEmpty(getName()))
		{
			result.add(new ValidationAlert(Message.INSTANCE.drainageBasinEditingViewSiteName(), Message.INSTANCE.mandatoryData()));
		}
		if (isEmpty(getLatitude()))
		{
			result.add(new ValidationAlert(Message.INSTANCE.drainageBasinEditingViewSiteLatitude(), Message.INSTANCE.mandatoryData()));
		}
		else
		{
			if (checkDouble(getLatitude())==false)
			{
				result.add(new ValidationAlert(Message.INSTANCE.drainageBasinEditingViewSiteLatitude(), Message.INSTANCE.numericalData()));
			}
		}
		if (isEmpty(getLongitude()))
		{
			result.add(new ValidationAlert(Message.INSTANCE.drainageBasinEditingViewSiteLongitude(), Message.INSTANCE.mandatoryData()));
		}
		else
		{
			if (checkDouble(getLongitude())==false)
			{
				result.add(new ValidationAlert(Message.INSTANCE.drainageBasinEditingViewSiteLongitude(), Message.INSTANCE.numericalData()));
			}
		}
		if (!isEmpty(getAltitude()))
		{
			if (checkDouble(getAltitude())==false)
			{
				result.add(new ValidationAlert(Message.INSTANCE.drainageBasinEditingViewSiteAltitude(), Message.INSTANCE.numericalData()));
			}
		}
		return result;
	}
	
}
