package fr.obsmip.sedoo.client.ui.table;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.Column;

public abstract class MultiLineTextColumn<T> extends Column<T, String> {

	  /**
	   * Construct a new TextColumn.
	   */
	  public MultiLineTextColumn() {
	    super(new TextCell(MultilineRenderer.getInstance()));
	  }
	}

	
