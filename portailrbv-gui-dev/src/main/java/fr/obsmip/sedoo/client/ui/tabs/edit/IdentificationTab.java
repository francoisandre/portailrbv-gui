package fr.obsmip.sedoo.client.ui.tabs.edit;

import java.util.List;
import java.util.ListIterator;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

import fr.obsmip.sedoo.client.CellTableResources;
import fr.obsmip.sedoo.client.GlobalBundle;
import fr.obsmip.sedoo.client.domain.URL;
import fr.obsmip.sedoo.client.ui.misc.WaterMarkCell;
import fr.obsmip.sedoo.client.ui.table.StringTable;
import fr.obsmip.sedoo.client.ui.table.URLTable;

public class IdentificationTab extends Composite {

	private static IdentificationTabUiBinder uiBinder = GWT
			.create(IdentificationTabUiBinder.class);

	interface IdentificationTabUiBinder extends
			UiBinder<Widget, IdentificationTab> {
	}
	
	@UiField
	TextBox resourceTitle;
	
	@UiField 
	TextArea resourceAbstract;
	
	@UiField 
	URLTable resourceURLTable;
	
	@UiField 
	StringTable resourceIdentifiantTable;
	
	public IdentificationTab() {
		initWidget(uiBinder.createAndBindUi(this));
		CellTableResources.INSTANCE.cellTableStyle().ensureInjected();
		reset();
	}

	public void reset() {
		resourceTitle.setText("");
		resourceAbstract.setText("");
		resourceURLTable.reset();
		resourceIdentifiantTable.reset();
	}
	
	
}
