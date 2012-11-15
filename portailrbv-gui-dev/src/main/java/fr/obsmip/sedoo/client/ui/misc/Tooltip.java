package fr.obsmip.sedoo.client.ui.misc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.Widget;

public class Tooltip extends Composite implements HasHTML {

	private static TooltipUiBinder uiBinder = GWT.create(TooltipUiBinder.class);
	
	@UiField SpanElement tooltipContent;

	interface TooltipUiBinder extends UiBinder<Widget, Tooltip> {
	}

	public Tooltip() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	
	@Override
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
		
	}




}
