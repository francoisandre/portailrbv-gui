package fr.obsmip.sedoo.client.ui.tabs.edit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.UIObject;

import fr.obsmip.sedoo.client.domain.metadata.MetadataDTO;

public abstract class AbstractTab extends Composite
{
	public abstract void reset();
	public abstract void edit(MetadataDTO metadata);
	public abstract void display(MetadataDTO metadata);
	public abstract MetadataDTO flush(MetadataDTO metadataDTO);
	
	protected List<UIObject> editWidgets = new ArrayList<UIObject>();
	protected List<UIObject> displayWidgets = new ArrayList<UIObject>();
	
	protected void enableEditMode() {
		changeVisibility(displayWidgets, false);
		changeVisibility(editWidgets, true);
	}
	
	protected void enableDisplayMode() {
		changeVisibility(displayWidgets, true);
		changeVisibility(editWidgets, false);
	}
	
	
	private void changeVisibility(List<UIObject> widgets, boolean value)
	{
		Iterator<UIObject> iterator = widgets.iterator();
		while (iterator.hasNext()) {
			iterator.next().setVisible(value);
		}
	}

}
