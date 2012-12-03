package fr.obsmip.sedoo.client.misc;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

public class DateTools {
	
	private static DateTimeFormat rbvFormater = DateTimeFormat.getFormat("yyyy-MM-dd");

	private DateTools()
	{
		
	}
	
	public static boolean after(final Date baseDate, final Date afterDate) {
        final long baseTime = baseDate.getTime() / 1000;
        final long afterTime = afterDate.getTime() / 1000;
        return baseTime < afterTime;
    }

	public static DateTimeFormat getRBVDateFormat() {
		return rbvFormater;
	}
	
}
