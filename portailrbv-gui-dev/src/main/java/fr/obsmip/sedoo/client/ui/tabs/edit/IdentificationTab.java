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
	VerticalPanel resourceURLPanel;
	
	@UiField 
	Image addResourceURLImage;
	
	private CellTable<URL> resourceURLTable;
	
	protected List<URL> urlList;
	
	protected Image supprimer=new Image(GlobalBundle.INSTANCE.delete());

	public IdentificationTab() {
		initWidget(uiBinder.createAndBindUi(this));
		CellTableResources.INSTANCE.cellTableStyle().ensureInjected();
		resourceURLTable = new CellTable<URL>(Integer.MAX_VALUE,CellTableResources.INSTANCE);
		initResourceURLTable();
		resourceURLPanel.add(resourceURLTable);
		reset();
	}

	private void initResourceURLTable() {
		resourceURLTable.setWidth("80%", false);	
		
//		TextColumn<URL> urlColumn = new TextColumn<URL>() {
//			@Override
//			public String getValue(URL url) {
//				return url.getValue();
//			}
//		};
		
		Column<URL, String> urlColumn = new Column<URL, String>(
				new WaterMarkCell(URL.DEFAULT_VALUE,"120","60")) {
			@Override
			public String getValue(URL url) {
				return url.getValue();
			}
		};
		
		urlColumn.setFieldUpdater(new FieldUpdater<URL, String>() {
			@Override
			public void update(int index, URL url, String value) {
				url.setValue(value);
			}
		});
		
		urlColumn.setCellStyleNames("url");

		Cell<URL> actionCell = new ImagedActionCell<URL>(del,this.supprimer, "Supprimer");
		Column<URL, String> deleteColumn = new Column(actionCell) {

			@Override
			public URL getValue(Object object) {
				return (URL) object;
			}
		};
		
		
		resourceURLTable.addColumn(urlColumn);
		resourceURLTable.addColumn(deleteColumn);
		
		resourceURLTable.setColumnWidth(urlColumn, 10.0, Unit.PX);
		resourceURLTable.setColumnWidth(deleteColumn, 10.0, Unit.PX);
		ListDataProvider<URL> dataProvider = new ListDataProvider<URL>();
		dataProvider.addDataDisplay(resourceURLTable);
		urlList = dataProvider.getList();
	}

	public void reset() {
//		creation = true;
//		getBoutonAjouterPrestation().setText("Ajouter la prestation");
//		getBoutonSupprimerPrestation().setVisible(false);
//		getRemarque().setText("");
		
		urlList.clear();
		urlList.add(getDefaultURL());
		urlList.add(getDefaultURL());
		urlList.add(getDefaultURL());
		resourceURLTable.setRowData(urlList);
		resourceURLTable.redraw();
	}
	
	protected URL getDefaultURL() {

		URL url = new URL();
		url.setValue(URL.DEFAULT_VALUE);
		return url;
	}

	 @UiHandler("addResourceURLImage")
	  void onAddResourceURLImageClicked(ClickEvent event) {
		 
		 URL url = getDefaultURL();
		 urlList.add(url);
		 resourceURLTable.setRowData(urlList);
		 resourceURLTable.redraw();

	  }
	
	
	ImagedActionCell.Delegate<URL> del = new ImagedActionCell.Delegate<URL>() {

		@Override
		public void execute(URL url) {
			if (urlList.size() == 1) {
				urlList.remove(0) ;
				urlList.add(getDefaultURL()); 
			} else {
				String value = url.getValue();
				
				ListIterator<URL> listIterator = urlList.listIterator();
				while (listIterator.hasNext()) 
				{
					URL aux = (URL) listIterator.next();
					if (value.compareTo(aux.getValue())==0)
					{
						listIterator.remove();
						break;
					}
				}
				
				resourceURLTable.setRowData(urlList);
				resourceURLTable.redraw();
			}
		}
	};
	
}
