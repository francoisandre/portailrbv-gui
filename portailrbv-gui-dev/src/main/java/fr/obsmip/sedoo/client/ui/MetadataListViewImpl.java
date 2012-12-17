package fr.obsmip.sedoo.client.ui;

import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;

import fr.obsmip.sedoo.client.domain.SummaryDTO;

public class MetadataListViewImpl extends AbstractSection implements MetadataListView {

	private static MetadataListViewImplUiBinder uiBinder = GWT
			.create(MetadataListViewImplUiBinder.class);

	@UiField VerticalPanel dataPanel;
	@UiField VerticalPanel pagerPanel;
	
	private final static String SEE_METADATA ="SEE";
	private final static String PDF_METADATA ="PDF";
	private final static String DIPLAY_METADATA_LINKS ="LINKS";
	private final static String DIPLAY_METADATA_QUICKLOOKS ="QUICKLOOKS";

	private static final int DEFAULT_RANGE = 10;
	
	private CellList<SummaryDTO> summaryList;
	private Presenter presenter;
	
	@UiField ListBox returnedRowNumber;
	
	private int displayRange = DEFAULT_RANGE;
	
    
	
	interface MetadataListViewImplUiBinder extends UiBinder<Widget, MetadataListViewImpl> {
	}

	public MetadataListViewImpl() {
		super();
		initWidget(uiBinder.createAndBindUi(this));
		applyCommonStyle();
		SummaryCell summaryCell = new SummaryCell(this);
		summaryList = new CellList<SummaryDTO>(summaryCell);
		returnedRowNumber.addItem(""+DEFAULT_RANGE);
		returnedRowNumber.addItem("20");
		returnedRowNumber.addItem("50");
		
		
//		summaryList.setPageSize(30);
//		summaryList.setKeyboardPagingPolicy(KeyboardPagingPolicy.INCREASE_RANGE);
//		summaryList.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.BOUND_TO_SELECTION);
//		dataPanel.add(summaryList);
	}


	/**
	   * The Cell used to render a {@link ContactInfo}.
	   */
	  static class SummaryCell extends AbstractCell<SummaryDTO> {
		  
		  Presenter presenter;
		  
		  public SummaryCell(MetadataListViewImpl view) {
			  // On ecoute seulement les click éventuels sur les boutons
			  super("click");
			 presenter = view.getPresenter();
		}

	    @Override
	    public void render(Context context, SummaryDTO value, SafeHtmlBuilder sb) {
	      if (value == null) {
	        return;
	      }

	      sb.appendHtmlConstant("<table style=\"padding:5px;text-align:justify;\">");
	      sb.appendHtmlConstant("<tr><td><span style=\"font-weight:bold;\">");
	      sb.appendHtmlConstant(value.getResourceTitle());
	      sb.appendHtmlConstant("</b></td></tr>");
	      sb.appendHtmlConstant("<tr><td>");
	      sb.appendHtmlConstant(value.getResourceAbstract());
	      sb.appendHtmlConstant("</td></tr>");
	      sb.appendHtmlConstant("<tr><td><span id=\""+SEE_METADATA+"-"+value.getUuid()+"\" class=\"small-button\">VOIR</span><span class=\"small-button\" id=\""+PDF_METADATA+"-"+value.getUuid()+"\">PDF</span><span class=\"small-button\" id=\""+DIPLAY_METADATA_QUICKLOOKS+"-"+value.getUuid()+"\">APERCUS</span><span class=\"small-button\" id=\""+DIPLAY_METADATA_LINKS+"-"+value.getUuid()+"\">LIENS</span></td></tr>");
	      sb.appendHtmlConstant("</table>");
	    }
	    
	    @Override
	    public void onBrowserEvent(
	    		com.google.gwt.cell.client.Cell.Context context,
	    		Element parent, SummaryDTO value, NativeEvent event,
	    		ValueUpdater<SummaryDTO> valueUpdater) {
	    	super.onBrowserEvent(context, parent, value, event, valueUpdater);
	    	EventTarget eventTarget = event.getEventTarget();
	    	if (Element.is(eventTarget))
	    	{
	    		Element src  = Element.as(eventTarget);
	    		String id = src.getId();
	    		if (id.startsWith(SEE_METADATA))
	    		{
	    			presenter.displayMetadata(value.getUuid());
	    		}
	    		if (id.startsWith(PDF_METADATA))
	    		{
	    			presenter.displayMetadataPDF(value.getUuid());
	    		}
	    	}
	    }
	  }

	
	
//	@Override
//	public void displayLoadingMessage() {
//		loadingPanel.setVisible(true);
//	}

	@Override
	public void setSummaryDTOList(final List<SummaryDTO> summaries) {
		

		/*ProvidesKey<SummaryDTO> keyProvider = new ProvidesKey<SummaryDTO>() 
		{
		      public Object getKey(SummaryDTO item) {
		        // Always do a null check.
		        return (item == null) ? null : item.getIdentifier();
		      }
		    };
		
		SimplePager pager = new SimplePager();
		SummaryCell summaryCell = new SummaryCell(this);
		summaryList = new CellList<SummaryDTO>(summaryCell, keyProvider);
		summaryList.setVisibleRange(0, displayRange);
		
		AsyncDataProvider<SummaryDTO> dataProvider = new AsyncDataProvider<SummaryDTO>()
				{

					@Override
					protected void onRangeChanged(HasData<SummaryDTO> display) 
					{
						Range range = display.getVisibleRange();
						int start = range.getStart();
				        int end = start + range.getLength();
				        //pager.startLoading();
				        List<SummaryDTO> redux = summaries.subList(start, end);
				        
				        summaryList.setRowData(start, redux);
				        
						
					}};
		dataProvider.addDataDisplay(summaryList);
		pager.setDisplay(summaryList);
		
//		pager.setPageSize(4);
		
		
		loadingPanel.setVisible(false);
		
		*/
		
		
		ProvidesKey<SummaryDTO> keyProvider = new ProvidesKey<SummaryDTO>() 
				{
				      public Object getKey(SummaryDTO item) {
				        // Always do a null check.
				        return (item == null) ? null : item.getUuid();
				      }
				    };
		
		SimplePager pager = new SimplePager();
		SummaryCell summaryCell = new SummaryCell(this);
		summaryList = new CellList<SummaryDTO>(summaryCell, keyProvider);
		ListDataProvider<SummaryDTO> dataProvider = new ListDataProvider<SummaryDTO>();
		dataProvider.setList(summaries);
		dataProvider.addDataDisplay(summaryList);
		pager.setDisplay(summaryList);
		pagerPanel.clear();
		pagerPanel.add(pager);
		dataPanel.clear();
		dataPanel.add(summaryList);
		pager.setPageSize(displayRange);
		
		
		
		
		
	}



	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
	
	public Presenter getPresenter() {
		return presenter;
	}

}
