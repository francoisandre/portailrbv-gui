package fr.obsmip.sedoo.client.ui.table;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;

import fr.obsmip.sedoo.client.CellTableResources;

public class FormattedCellTable<T> extends CellTable<T> {

	public FormattedCellTable(int maxValue, CellTableResources instance) {
		super(maxValue, instance);
	}

	@Override
	public void addColumn(Column<T, ?> col, String headerString) {
		// TODO Auto-generated method stub
		String aux = headerString.replace(":", "").trim();
		super.addColumn(col, aux);
	}
	
}
