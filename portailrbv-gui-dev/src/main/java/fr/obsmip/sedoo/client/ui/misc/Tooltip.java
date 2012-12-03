package fr.obsmip.sedoo.client.ui.misc;

import java.util.Iterator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class Tooltip extends Composite implements HasWidgets {

	private static TooltipUiBinder uiBinder = GWT.create(TooltipUiBinder.class);
	
	@UiField SpanElement tooltipContent;

	interface TooltipUiBinder extends UiBinder<Widget, Tooltip> {
	}

	public Tooltip() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void add(Widget w) {
		if (w instanceof HasText)
		{
			tooltipContent.setInnerHTML(((HasText) w).getText());
		}
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterator<Widget> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Widget w) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	/*@Override
	public String getText() {
		 throw new UnsupportedOperationException();
	}

	@Override
	public void setText(String text) {
		 throw new UnsupportedOperationException();
		
	}

	@Override
	public String getHTML() {
		 throw new UnsupportedOperationException();
	}

	@Override
	public void setHTML(String html) {
		
		tooltipContent.setInnerHTML(html);
		
	}*/




}
