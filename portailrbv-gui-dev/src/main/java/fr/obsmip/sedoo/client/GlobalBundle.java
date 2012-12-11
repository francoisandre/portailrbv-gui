package fr.obsmip.sedoo.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.NotStrict;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.cellview.client.CellTable;


public interface GlobalBundle extends ClientBundle {
	    @NotStrict
	    @Source("carnavello.css")
	    CssResource css();

	    @Source("flag-en.png")
	    ImageResource english();
	    
	    @Source("flag-fr.png")
	    ImageResource french();
	    
	    @Source("ajax-loader.gif")
	    ImageResource loading();
	    
	    @Source("help.png")
	    ImageResource help();
	    
	    @Source("logo.png")
	    ImageResource logo();
	    
	    @Source("bandeau.png")
	    ImageResource bandeau();
	    
	    @Source("delete.png")
	    ImageResource delete();
	    
	    @Source("edit.png")
	    ImageResource edit();
	    
	    @Source("add.png")
	    ImageResource add();
	    
	    @Source("ok.png")
	    ImageResource ok();
	    
	    @Source("ko.png")
	    ImageResource ko();
	    
	    @Source("minimize.png")
	    ImageResource minimize();
	    
	    @Source("maximize.png")
	    ImageResource maximize();
	    
	    @Source("puce_fleche_bleu.png")
	    ImageResource menuDot();
	    
	    @Source("plus.png")
	    ImageResource menuPlus();
	    
	    @Source("drag.png")
	    ImageResource drag();
	    
	    @Source("drainageBasinDraw.png")
	    ImageResource drainageBasinDraw();
	    
	    @Source("siteDraw.png")
	    ImageResource siteDraw();
	    
	    @Source("siteDelete.png")
	    ImageResource siteDelete();
	    
	    @Source("drainageBasinDelete.png")
	    ImageResource drainageBasinDelete();
	    
	    @Source("cellTable.css")
        CellTable.Style cellTableStyle();
	    
	    public static final GlobalBundle INSTANCE = GWT.create(GlobalBundle.class);
//	    
	  }