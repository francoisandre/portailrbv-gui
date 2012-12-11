package fr.obsmip.sedoo.client.ui.table;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.MultiSelectionModel;

import fr.obsmip.sedoo.client.domain.AbstractDTO;
import fr.obsmip.sedoo.client.domain.HasId;
import fr.obsmip.sedoo.client.domain.MetadataContactDTO;
import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.ui.MetadataEditingView.Presenter;

public class MetadataContactSelectionTable extends MetadataContactTable {

	MultiSelectionModel<HasId> selectionModel;
	Presenter presenter;
	
	public MetadataContactSelectionTable()
	{
		super();
		toolBarPanel.setVisible(false);
	}
	
	
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
	
	public void refresh()
	{
		itemTable.setVisibleRangeAndClearData(itemTable.getVisibleRange(), true);
		presenter.getObservatoryContacts(this);
	}
	
	@Override
	protected void initColumns() {
		
		selectionModel = new MultiSelectionModel<HasId>(AbstractDTO.KEY_PROVIDER);
		itemTable.setSelectionModel(selectionModel, DefaultSelectionEventManager.<HasId> createCheckboxManager());
		
		 Column<HasId, Boolean> checkColumn = new Column<HasId, Boolean>(
			        new CheckboxCell(true, false)) {
			      @Override
			      public Boolean getValue(HasId object) {
			        // Get the value from the selection model.
			        return selectionModel.isSelected(object);
			      }
			    };
		
		itemTable.addColumn(checkColumn, SafeHtmlUtils.fromSafeConstant("<br/>"));
		itemTable.setColumnWidth(checkColumn, 40, Unit.PX);
		
		TextColumn<HasId> nameColumn = new TextColumn<HasId>() {
			@Override
			public String getValue(HasId aux) {
				return ((MetadataContactDTO) aux).getPersonName();
			}
		};
		itemTable.addColumn(nameColumn, Message.INSTANCE.personPersonName());
		itemTable.setColumnWidth(nameColumn, 100.0, Unit.PX);
		
		TextColumn<HasId> emailColumn = new TextColumn<HasId>() {
			@Override
			public String getValue(HasId aux) {
				return ((MetadataContactDTO) aux).getEmail();
			}
		};
		itemTable.addColumn(emailColumn, Message.INSTANCE.personEmail());
		itemTable.setColumnWidth(emailColumn, 100.0, Unit.PX);
		
		TextColumn<HasId> organisationColumn = new TextColumn<HasId>() {
			@Override
			public String getValue(HasId aux) {
				return ((MetadataContactDTO) aux).getOrganisationName();
			}
		};
		itemTable.addColumn(organisationColumn, Message.INSTANCE.personOrganisationName());
		itemTable.setColumnWidth(organisationColumn, 100.0, Unit.PX);
		
		MultiLineTextColumn<HasId> roleColumn = new MultiLineTextColumn<HasId>(RoleRenderer.getInstance()) {
			@Override
			public String getValue(HasId aux) {
				return ((MetadataContactDTO) aux).getRoles();
			}
		};
		itemTable.addColumn(roleColumn, Message.INSTANCE.personRoles());
		itemTable.setColumnWidth(roleColumn, 100.0, Unit.PX);
		
	}
	
	
	public void reset() {
			if (selectionModel != null)
			{
				selectionModel.clear();
			}
			List<MetadataContactDTO> aux = new ArrayList<MetadataContactDTO>();
			init(aux);
	}
	
	public List<MetadataContactDTO> getSelectedItems()
	{
		Set<HasId> selectedSet = selectionModel.getSelectedSet();
		List<MetadataContactDTO> selection = new ArrayList<MetadataContactDTO>();
		Iterator<HasId> iterator = selectedSet.iterator();
		while (iterator.hasNext()) 
		{
			selection.add((MetadataContactDTO) iterator.next());
		}
		return selection;
	}

}
