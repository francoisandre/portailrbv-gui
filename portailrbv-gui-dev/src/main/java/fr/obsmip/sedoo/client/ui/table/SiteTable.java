package fr.obsmip.sedoo.client.ui.table;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.cellview.client.TextColumn;

import fr.obsmip.sedoo.client.domain.HasId;
import fr.obsmip.sedoo.client.domain.SiteDTO;
import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.ui.DrainageBasinEditingView.Presenter;


public class SiteTable extends AbstractTable {

	private Presenter presenter;
	

	@Override
	public void addItem() {
		//presenter.createDrainageBasin(observatoryDTO);
	}

	@Override
	public String getAddItemText() {
		return Message.INSTANCE.SiteTableAddItemText();
	}

	@Override
	public void presenterDelete(Long id) {
		presenter.deleteSite(id);	
	}

	@Override
	public void presenterEdit(Long id) {
		//presenter.editDrainageBasin(id);
		System.out.println("edit");
		
	}

	@Override
	protected void initColumns() {
		
		TextColumn<HasId> nameColumn = new TextColumn<HasId>() {
			@Override
			public String getValue(HasId aux) {
				return ((SiteDTO) aux).getName();
			}
		};
		itemTable.addColumn(nameColumn, Message.INSTANCE.name());
		itemTable.setColumnWidth(nameColumn, 100.0, Unit.PX);
		super.initColumns();
	}

	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

}
