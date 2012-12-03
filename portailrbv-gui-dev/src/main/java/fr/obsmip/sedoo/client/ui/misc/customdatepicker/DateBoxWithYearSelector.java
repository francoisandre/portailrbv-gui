package fr.obsmip.sedoo.client.ui.misc.customdatepicker;

import com.google.gwt.user.datepicker.client.DateBox;

public class DateBoxWithYearSelector extends DateBox{
	
	public DateBoxWithYearSelector(Format format) {
		super(new DatePickerWithYearSelector(), null, 	format);
	}

}
