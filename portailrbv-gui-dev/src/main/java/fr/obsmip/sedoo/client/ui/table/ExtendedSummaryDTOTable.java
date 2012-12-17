package fr.obsmip.sedoo.client.ui.table;

import java.util.Iterator;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Image;

import fr.obsmip.sedoo.client.GlobalBundle;
import fr.obsmip.sedoo.client.domain.ExtendedSummaryDTO;
import fr.obsmip.sedoo.client.domain.HasId;
import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.ui.DrainageBasinChoiceView.Presenter;
import fr.obsmip.sedoo.client.ui.misc.DialogBoxTools;
import fr.obsmip.sedoo.client.ui.tabs.edit.ImagedActionCell;


public class ExtendedSummaryDTOTable extends AbstractTable {

	protected Column<HasId, String> viewColumn;
	protected Column<HasId, String> printColumn;
	
	private Presenter presenter;
	private Long drainageBasinId;

	public ExtendedSummaryDTOTable() {
		super();
		setAddButtonEnabled(true);
		
		Cell<HasId> viewActionCell = new ImagedActionCell<HasId>(viewAction,new Image(GlobalBundle.INSTANCE.view()), Message.INSTANCE.view());
		viewColumn = new Column(viewActionCell) {

			@Override
			public HasId getValue(Object object) {
				return (HasId) object;
			}
		};

		Cell<HasId> printActionCell = new ImagedActionCell<HasId>(printAction,new Image(GlobalBundle.INSTANCE.print()), Message.INSTANCE.print());
		printColumn = new Column(printActionCell) {

			@Override
			public HasId getValue(Object object) {
				return (HasId) object;
			}
		};
		
		localInitColumns();
	}
	
	@Override
	public void addItem() {
		if (getDrainageBasinId() != null)
		{
			getPresenter().createMetadata(getDrainageBasinId());
		}
		else
		{
			DialogBoxTools.modalAlert(Message.INSTANCE.error(), Message.INSTANCE.metadataListinNoDrainageBasinSelected());
		}
	}

	@Override
	public String getAddItemText() {
		return Message.INSTANCE.addMetadataEntry();
	}

	@Override
	public void presenterDelete(Long id) {
	}

	@Override
	public void presenterEdit(Long id) {
		
		presenter.editMetadata(getUuidFromId(id), drainageBasinId);
		
	}
	
private void presenterView(Long id) {
		presenter.viewMetadata(getUuidFromId(id));
	}
	
protected void initColumns() {
}

private void localInitColumns()
{
		TextColumn<HasId> titleColumn = new TextColumn<HasId>() {
			@Override
			public String getValue(HasId aux) {
				return ((ExtendedSummaryDTO) aux).getResourceTitle();
			}
		};
		itemTable.addColumn(titleColumn, Message.INSTANCE.metadataEditingTitle());
		itemTable.setColumnWidth(titleColumn, 100.0, Unit.PX);
		
		
		TextColumn<HasId> abstractColumn = new TextColumn<HasId>() {
			@Override
			public String getValue(HasId aux) {
				return ((ExtendedSummaryDTO) aux).getResourceAbstract();
			}
		};
		itemTable.addColumn(abstractColumn, Message.INSTANCE.metadataEditingAbstract());
		itemTable.setColumnWidth(abstractColumn, 100.0, Unit.PX);
		
		
		
		TextColumn<HasId> lastModificationDateColumn = new TextColumn<HasId>() {
			@Override
			public String getValue(HasId aux) {
				return ((ExtendedSummaryDTO) aux).getLastModificationDate();
			}
		};
		itemTable.addColumn(lastModificationDateColumn, Message.INSTANCE.metadataEditingLastModificationDate());
		itemTable.setColumnWidth(lastModificationDateColumn, 40.0, Unit.PX);
		
		
		
		TextColumn<HasId> stateColumn = new TextColumn<HasId>() {
			@Override
			public String getValue(HasId aux) {
				return ((ExtendedSummaryDTO) aux).getState();
			}
		};
		itemTable.addColumn(stateColumn, Message.INSTANCE.metadataEditingState());
		itemTable.setColumnWidth(stateColumn, 100.0, Unit.PX);
		
		
		itemTable.addColumn(viewColumn);
		itemTable.addColumn(editColumn);
		itemTable.addColumn(printColumn);
		itemTable.addColumn(deleteColumn);
		itemTable.setColumnWidth(viewColumn, 30.0, Unit.PX);
		itemTable.setColumnWidth(editColumn, 30.0, Unit.PX);
		itemTable.setColumnWidth(printColumn, 30.0, Unit.PX);
		itemTable.setColumnWidth(deleteColumn, 30.0, Unit.PX);
	}


public Long getDrainageBasinId() {
	return drainageBasinId;
}

public void setDrainageBasinId(Long drainageBasinId) {
	this.drainageBasinId = drainageBasinId;
}


public Presenter getPresenter() {
	return presenter;
}

public void setPresenter(Presenter presenter) {
	this.presenter = presenter;
}

private String getUuidFromId(Long id)
{
	Iterator<? extends HasId> iterator = model.iterator();
	while (iterator.hasNext()) {
		ExtendedSummaryDTO aux = (ExtendedSummaryDTO) iterator.next();
		if (aux.getId() == id)
		{
			return aux.getUuid();
		}
	}
	return null;
}

ImagedActionCell.Delegate<HasId> viewAction = new ImagedActionCell.Delegate<HasId>() {

	@Override
	public void execute(final HasId hasId) {

		presenterView(hasId.getId());
	}

	


};

ImagedActionCell.Delegate<HasId> printAction = new ImagedActionCell.Delegate<HasId>() {

	@Override
	public void execute(HasId hasCode) {

		presenterPrint(hasCode.getId());

	}

	private void presenterPrint(Long id) {
		
	}


};

}
