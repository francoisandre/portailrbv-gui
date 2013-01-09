package fr.obsmip.sedoo.client.ui.misc;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.StackLayoutPanel;

public class RBVStackLayoutPanel extends StackLayoutPanel
{
	private Integer previousWidgetIndex;
	@Override
	public void add(IsWidget widget, IsWidget header, double headerSize) {
		// TODO Auto-generated method stub
		super.add(widget, header, headerSize);
	}
	
	public RBVStackLayoutPanel(Unit unit) {
		super(unit);
	}
	
	@Override
	public void showWidget(int index) {
		Element elementById = DOM.getElementById("stackHeader-"+index);
		if (elementById != null)
		{
			elementById.getStyle().setProperty("visibility","hidden");
		}
		if (previousWidgetIndex != null)
		{
			elementById = DOM.getElementById("stackHeader-"+previousWidgetIndex);
			if (elementById != null)
			{
				elementById.getStyle().setProperty("visibility","visible");
			}
		}
		previousWidgetIndex=index;
		super.showWidget(index);
		
	}

}
