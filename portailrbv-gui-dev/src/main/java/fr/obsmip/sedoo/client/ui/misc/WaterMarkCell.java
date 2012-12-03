package fr.obsmip.sedoo.client.ui.misc;

import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public class WaterMarkCell extends TextInputCell {
	protected String size;
	protected String maxLength;
	protected String className;
	protected String placeHolder="";
	   
	private static Template template;

	interface Template extends SafeHtmlTemplates {
	    @Template("<input type=\"text\" value=\"{0}\" placeholder=\"{1}\" size=\"{2}\" maxlength=\"{3}\" class=\"{4}\" tabindex=\"-1\"></input>")
	    SafeHtml input(String value, String placeHolder, String size, String maxlength, String className);
	  }

	public WaterMarkCell(String placeHolder, String size, String maxlength, String className) {
		super();
		this.size = size;
		this.maxLength = maxlength;
		this.className = className;
		if (placeHolder != null)
		{
			this.placeHolder = placeHolder;
		}
		 if (template == null) {
		      template = GWT.create(Template.class);
		    }
	}
	
	
	public WaterMarkCell(String defaultValue, String size, String maxlength) {
		this(defaultValue, size, maxlength, "gwt-TextBox");
	}

//	public WaterMarkCell(String defaultValue, TreeMap<String, String> parametres) {
//		this.defaultValue = defaultValue;
//		this.parametres = parametres;
//	}

	@Override
	public void onBrowserEvent(Context context, Element parent, String value,
			NativeEvent event, ValueUpdater<String> valueUpdater) {
		super.onBrowserEvent(context, parent, value, event, valueUpdater); // super.onBrowserEvent()
																			// appelé
																			// 2
																			// fois
																			// ?

		// Ignore events that don't target the input.
		InputElement input = getInputElement(parent);
		Element target = event.getEventTarget().cast();
		if (!input.isOrHasChild(target)) {
			return;
		}
		super.onBrowserEvent(context, parent, value, event, valueUpdater);

	}
	
	
	@Override
	  public void render(Context context, String value, SafeHtmlBuilder sb) {
	    // Get the view data.
	    Object key = context.getKey();
	    ViewData viewData = getViewData(key);
	    if (viewData != null && viewData.getCurrentValue().equals(value)) {
	      clearViewData(key);
	      viewData = null;
	    }

	    String s = (viewData != null) ? viewData.getCurrentValue() : value;
	    if (s != null) {
	      sb.append(template.input(s, placeHolder,size, maxLength, className));
	    } else {
	      sb.append(template.input("", placeHolder,size, maxLength, className));
	    }
	  }

//	

}
