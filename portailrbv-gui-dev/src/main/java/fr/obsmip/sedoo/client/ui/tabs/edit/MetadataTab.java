package fr.obsmip.sedoo.client.ui.tabs.edit;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

import fr.obsmip.sedoo.client.Constants;
import fr.obsmip.sedoo.client.domain.metadata.MetadataDTO;
import fr.obsmip.sedoo.client.domain.metadata.MetadataPart;
import fr.obsmip.sedoo.client.ui.MetadataEditingView.Presenter;
import fr.obsmip.sedoo.client.ui.table.MetadataContactTable;

public class MetadataTab extends AbstractTab {

	private static MetadataTabUiBinder uiBinder = GWT
			.create(MetadataTabUiBinder.class);

	interface MetadataTabUiBinder extends UiBinder<Widget, MetadataTab> {
	}

	public MetadataTab() {
		initWidget(uiBinder.createAndBindUi(this));
		language.addItem("English", Constants.ENGLISH);
		language.addItem("Français", Constants.FRENCH);
	}

	@UiField
	ListBox language;
	
	@UiField 
	Element lastModificationDate;
	
	@UiField
	MetadataContactTable contactTable;

	private Presenter presenter;
	
	public void setLastModificationDate(String value)
	{
		lastModificationDate.setInnerText(value);
	}

	public void edit(MetadataDTO metadata) 
	{
		reset();
		//Default positionning
		language.setSelectedIndex(0);
		if (metadata.getMetadataPart().getMetadataLanguage().compareTo(Constants.FRENCH)==0)
		{
			language.setSelectedIndex(1);
		}
		lastModificationDate.setInnerText(metadata.getMetadataPart().getMetadataLastModificationDate());
		contactTable.init(metadata.getMetadataPart().getMetadataContacts());
	}
	
	public MetadataDTO flush(MetadataDTO metadataDTO)
	{
		MetadataPart part = new MetadataPart();
		part.setMetadataLanguage(language.getValue(language.getSelectedIndex()));
		part.setMetadataLastModificationDate(lastModificationDate.getInnerText());
		metadataDTO.setMetadataPart(part);
		return metadataDTO;
	}

	public void reset() {
		contactTable.reset();
	}

	public Presenter getPresenter() {
		return presenter;
	}

	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
		contactTable.setPresenter(getPresenter());
	}


}
