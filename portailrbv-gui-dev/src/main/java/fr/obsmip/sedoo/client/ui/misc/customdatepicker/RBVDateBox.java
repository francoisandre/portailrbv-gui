package fr.obsmip.sedoo.client.ui.misc.customdatepicker;

import com.google.gwt.user.datepicker.client.DateBox;

import fr.obsmip.sedoo.client.misc.DateTools;

public class RBVDateBox extends DateBoxWithYearSelector{
	
	public RBVDateBox() {
		super(new DateBox.DefaultFormat(DateTools.getRBVDateFormat()));
	}

}
