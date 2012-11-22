package fr.obsmip.sedoo.client.ui;

import java.util.List;
import java.util.ListIterator;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

import fr.obsmip.sedoo.client.CellTableResources;
import fr.obsmip.sedoo.client.GlobalBundle;
import fr.obsmip.sedoo.client.domain.ObservatoryDTO;
import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.ui.misc.ConfirmCallBack;
import fr.obsmip.sedoo.client.ui.misc.DialogBoxTools;
import fr.obsmip.sedoo.client.ui.tabs.edit.ImagedActionCell;

public class ObservatoryManagementViewImpl extends AbstractSection implements ObservatoryManagementView {



	private static ObservatoryManagementViewImplUiBinder uiBinder = GWT
			.create(ObservatoryManagementViewImplUiBinder.class);

	interface ObservatoryManagementViewImplUiBinder extends UiBinder<Widget, ObservatoryManagementViewImpl> {
	}

	protected List<ObservatoryDTO> observatories;

	private CellTable<ObservatoryDTO> observatoryTable;

	private Presenter presenter;

	protected Image deleteImage=new Image(GlobalBundle.INSTANCE.delete());
	protected Image editImage=new Image(GlobalBundle.INSTANCE.edit());

	@UiField
	Image addObservatoryImage;

	@UiField 
	VerticalPanel observatoryPanel;

	@UiField 
	VerticalPanel emptyList;

	public ObservatoryManagementViewImpl() {
		super();
		initWidget(uiBinder.createAndBindUi(this));
		applyCommonStyle();
		CellTableResources.INSTANCE.cellTableStyle().ensureInjected();

	}

	ListDataProvider<ObservatoryDTO> dataProvider = new ListDataProvider<ObservatoryDTO>();

	@Override
	public void init(List<ObservatoryDTO> observatories) {
		this.observatories = observatories;
		observatoryPanel.clear();
		observatoryTable = new CellTable<ObservatoryDTO>(Integer.MAX_VALUE,CellTableResources.INSTANCE);
		if ((observatories == null) || (observatories.size()==0))
		{
			emptyList.setVisible(true);
		}
		else
		{
			emptyList.setVisible(false);
		}
		observatoryTable.setWidth("100%", false);

		Cell<ObservatoryDTO> deleteActionCell = new ImagedActionCell<ObservatoryDTO>(deleteAction,this.deleteImage, "Supprimer");
		Column<ObservatoryDTO, String> deleteColumn = new Column(deleteActionCell) {

			@Override
			public ObservatoryDTO getValue(Object object) {
				return (ObservatoryDTO) object;
			}
		};

		Cell<ObservatoryDTO> editActionCell = new ImagedActionCell<ObservatoryDTO>(editAction,this.editImage, "Editer");
		Column<ObservatoryDTO, String> editColumn = new Column(editActionCell) {

			@Override
			public ObservatoryDTO getValue(Object object) {
				return (ObservatoryDTO) object;
			}
		};

		TextColumn<ObservatoryDTO> nameColumn = new TextColumn<ObservatoryDTO>() {
			@Override
			public String getValue(ObservatoryDTO observatory) {
				return observatory.getShortLabel();
			}
		};

		TextColumn<ObservatoryDTO> observationColumn = new TextColumn<ObservatoryDTO>() {
			@Override
			public String getValue(ObservatoryDTO observatory) {
				String aux = observatory.getDescription();
//				aux = aux.replace("\n", "<br />");
				if (aux.length()>200)
				{
					return aux.substring(0, 200)+"...";
				}
				else
				{
					return aux;
				}
			}
		};

		observatoryTable.addColumn(nameColumn, "Name");
		observatoryTable.addColumn(observationColumn, "Description");
		observatoryTable.addColumn(editColumn);
		observatoryTable.addColumn(deleteColumn);

		observatoryTable.setColumnWidth(nameColumn, 100.0, Unit.PX);
		observatoryTable.setColumnWidth(observationColumn, 200.0, Unit.PX);
		observatoryTable.setColumnWidth(editColumn, 30.0, Unit.PX);
		observatoryTable.setColumnWidth(deleteColumn, 30.0, Unit.PX);

		dataProvider.addDataDisplay(observatoryTable);
		List<ObservatoryDTO> list = dataProvider.getList();
		list.clear();
		list.addAll(observatories);
		observatoryTable.setRowData(dataProvider.getList());
		observatoryPanel.add(observatoryTable);

	}

	ImagedActionCell.Delegate<ObservatoryDTO> deleteAction = new ImagedActionCell.Delegate<ObservatoryDTO>() {

		@Override
		public void execute(final ObservatoryDTO observatoryDTO) {

			Message message =  GWT.create(Message.class);
			ConfirmCallBack callBack = new ConfirmCallBack() {
				
				@Override
				public void confirm(boolean choice) {
					if (choice == true)
					{
						Long id = observatoryDTO.getId();
						ListIterator<ObservatoryDTO> listIterator = observatories.listIterator();
						while (listIterator.hasNext()) 
						{
							ObservatoryDTO aux = (ObservatoryDTO) listIterator.next();
							if (id.compareTo(aux.getId())==0)
							{
								presenter.deleteObservatory(aux.getId());
								break;
							}
						}			
					}
					
				}
			};
			
			DialogBoxTools.modalConfirm(message.confirm(),
	                message.observatoryDeletionConfirmationMessage(), callBack).center();
			
		}


	};

	ImagedActionCell.Delegate<ObservatoryDTO> editAction = new ImagedActionCell.Delegate<ObservatoryDTO>() {

		@Override
		public void execute(ObservatoryDTO observatoryDTO) {
			
			presenter.goToEditObservatoryActivity(observatoryDTO);

		}


	};

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;

	}
	
	public void broadcastObservatoryDeletion(Long id, boolean success)
	{
		if (success)
		{
			ListIterator<ObservatoryDTO> listIterator = observatories.listIterator();
			while (listIterator.hasNext()) 
			{
				ObservatoryDTO aux = (ObservatoryDTO) listIterator.next();
				if (id.compareTo(aux.getId())==0)
				{
					listIterator.remove();
					break;
				}

			}
			List<ObservatoryDTO> list = dataProvider.getList();
			list.clear();
			list.addAll(observatories);
			observatoryTable.redraw();
			observatoryPanel.add(observatoryTable);
		}
		else
		{
			DialogBoxTools.modalAlert("A problem has appeared while deleting the observatory",
	                "A problem has appeared while deleting the observatory.");
		}
	}

	@UiHandler("addObservatoryImage")
	void onAddObservatoryImageClicked(ClickEvent event) {
		/*GroupEditDialog dialogBox = new GroupEditDialog();
		dialogBox.setGlassEnabled(true);
		dialogBox.setAutoHideEnabled(true);
		dialogBox.center();
		dialogBox.show();*/
		presenter.goToCreateObservatoryActivity();

	}



}
