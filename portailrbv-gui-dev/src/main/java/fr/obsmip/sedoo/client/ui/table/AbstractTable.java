package fr.obsmip.sedoo.client.ui.table;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;

import fr.obsmip.sedoo.client.CellTableResources;
import fr.obsmip.sedoo.client.GlobalBundle;
import fr.obsmip.sedoo.client.domain.HasId;
import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.ui.misc.ConfirmCallBack;
import fr.obsmip.sedoo.client.ui.misc.DialogBoxTools;
import fr.obsmip.sedoo.client.ui.tabs.edit.ImagedActionCell;

public abstract class AbstractTable extends Composite implements ClickHandler{

	protected List<? extends HasId> model;
	protected FormattedCellTable<HasId> itemTable;
	VerticalPanel tablePanel;
	HorizontalPanel toolBarPanel;
	private Image addImage;
	protected Label addItemLabel;
	private Image deleteImage=new Image(GlobalBundle.INSTANCE.delete());
	private Image editImage=new Image(GlobalBundle.INSTANCE.edit());
	
	protected Column<HasId, String> deleteColumn;
	protected Column<HasId, String> editColumn;
	
	private ListDataProvider<HasId> dataProvider = new ListDataProvider<HasId>();
	
	private HorizontalPanel emptyListPanel;
	
	private boolean addButtonEnabled = false;

	
	private boolean askDeletionConfirmation = true;
	
	public AbstractTable() {
		super();
		
		
		if (tablePanel == null)
		{
			tablePanel = new VerticalPanel();
		}
		else
		{
			tablePanel.clear();
		}

		emptyListPanel = new HorizontalPanel();
		emptyListPanel.add(new Label(Message.INSTANCE.commonsEmptyList()));
		
		checkEmptyPanelVisibility();

		tablePanel.add(emptyListPanel);
		
		itemTable = new FormattedCellTable<HasId>(Integer.MAX_VALUE,CellTableResources.INSTANCE);
		itemTable.setWidth("100%", false);

		Cell<HasId> deleteActionCell = new ImagedActionCell<HasId>(deleteAction,this.deleteImage, Message.INSTANCE.delete());
		deleteColumn = new Column(deleteActionCell) {

			@Override
			public HasId getValue(Object object) {
				return (HasId) object;
			}
		};

		Cell<HasId> editActionCell = new ImagedActionCell<HasId>(editAction,this.editImage, Message.INSTANCE.edit());
		editColumn = new Column(editActionCell) {

			@Override
			public HasId getValue(Object object) {
				return (HasId) object;
			}
		};

		toolBarPanel = new HorizontalPanel();
		toolBarPanel.setSpacing(5);
		//addItemPanel.setWidth("100%");
		addImage = new Image(GlobalBundle.INSTANCE.add());
		addImage.setStyleName("clickableImage");
		toolBarPanel.add(addImage);
		addItemLabel = new Label(getAddItemText());
		addItemLabel.setStyleName("clickableImage");
		toolBarPanel.add(addItemLabel);
		tablePanel.add(itemTable);
		tablePanel.add(toolBarPanel);
		tablePanel.setWidth("100%");
		addImage.addClickHandler(this);
		addItemLabel.addClickHandler(this);
		initColumns();
		initWidget(tablePanel);
		
	
	}

	private void checkEmptyPanelVisibility() {
		if ((this.model == null) || (this.model.size() == 0))
		{
			emptyListPanel.setVisible(true);
			if (itemTable != null)
			{
				itemTable.setVisible(false);
			}
		}
		else
		{
			emptyListPanel.setVisible(false);
			if (itemTable != null)
			{
				itemTable.setVisible(true);
			}
		}
		
	}

	protected void initColumns() {
		itemTable.addColumn(editColumn);
		itemTable.addColumn(deleteColumn);
		itemTable.setColumnWidth(editColumn, 30.0, Unit.PX);
		itemTable.setColumnWidth(deleteColumn, 30.0, Unit.PX);
	}

	public List<? extends HasId> getModel() {
		return model;
	}

	public void setModel(List<? extends HasId> model) {
		this.model = model;
	}

	public abstract void addItem();
	
	public abstract void presenterDelete(Long id);
	public abstract void presenterEdit(Long id);
	
	
	public void init(List<? extends HasId> model)
	{
		setModel(model);
		checkEmptyPanelVisibility();
		//TODO : Tester si on la pas déjà fait
		Set<HasData<HasId>> dataDisplays = dataProvider.getDataDisplays();
		if (dataDisplays.size() != 0)
		{
			dataProvider.removeDataDisplay(itemTable);
		}
		dataProvider.addDataDisplay(itemTable);
		List<HasId> list = dataProvider.getList();
		list.clear();
		list.addAll(this.model);
		itemTable.setRowData(dataProvider.getList());
	}
	

	public void onClick(ClickEvent event)
	{
		if ((event.getSource() == addImage) || (event.getSource() == addItemLabel))
		{
			if (addButtonEnabled == true)
			{
				addItem();
			}
			else
			{
				DialogBoxTools.modalAlert("Not activated", "Not activated in this mode");
			}
		}
	}

	public abstract String getAddItemText();
	
	public String getDeleteItemConfirmationText() 
	{
		return Message.INSTANCE.deletionConfirmMessage();
	}

	ImagedActionCell.Delegate<HasId> deleteAction = new ImagedActionCell.Delegate<HasId>() {

		@Override
		public void execute(final HasId hasId) {

			if (isAskDeletionConfirmation()== false)
			{
				presenterDelete(hasId.getId());
			}
			else
			{
			ConfirmCallBack callBack = new ConfirmCallBack() {

				@Override
				public void confirm(boolean choice) {
					if (choice == true)
					{
						presenterDelete(hasId.getId());
					}

				}
			};

			DialogBoxTools.modalConfirm(Message.INSTANCE.confirm(),getDeleteItemConfirmationText(), callBack).center();
			}
		}


	};

	ImagedActionCell.Delegate<HasId> editAction = new ImagedActionCell.Delegate<HasId>() {

		@Override
		public void execute(HasId hasCode) {

			presenterEdit(hasCode.getId());

		}


	};
	
	public void removeRow(Long id) {
		
		ListIterator<? extends HasId> listIterator = model.listIterator();
		while (listIterator.hasNext()) {
			HasId hasId = listIterator.next();
			if (hasId.getId().compareTo(id)==0)
			{
				listIterator.remove();
				break;
			}
		}
		init(model);
	}
	
	public HasId getItemById(Long id)
	{
		ListIterator<? extends HasId> listIterator = model.listIterator();
		while (listIterator.hasNext()) {
			HasId hasId = listIterator.next();
			if (hasId.getId().compareTo(id)==0)
			{
				return hasId;
			}
		}
		return null;
	}
	
	public void setAddButtonEnabled(boolean activated)
	{
		this.addButtonEnabled = activated;
	}

	public boolean isAskDeletionConfirmation() {
		return askDeletionConfirmation;
	}

	public void setAskDeletionConfirmation(boolean askDeletionConfirmation) {
		this.askDeletionConfirmation = askDeletionConfirmation;
	}
	
	public Long getMaxId()
	{
		Long maxId = 0L;
		Iterator<? extends HasId> iterator = model.iterator();
		while (iterator.hasNext()) 
		{
			HasId aux = (HasId) iterator.next();
			if (aux.getId()> maxId)
			{
				maxId = aux.getId();
			}
		}
		return maxId;
	}

}
