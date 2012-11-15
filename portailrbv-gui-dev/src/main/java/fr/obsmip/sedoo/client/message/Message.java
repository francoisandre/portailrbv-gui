package fr.obsmip.sedoo.client.message;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;
import com.google.gwt.i18n.client.LocalizableResource.Generate;
import com.google.gwt.i18n.client.Messages;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.cellview.client.Header;

import fr.obsmip.sedoo.client.GlobalBundle;

@Generate(
format = { "com.google.gwt.i18n.rebind.format.PropertiesFormat" }, 
fileName = "Message", 
locales = {"fr","en","default"})
@DefaultLocale("en")
public interface Message extends Messages {
	
	public static final Message INSTANCE = GWT.create(Message.class);
	
    @Key("metadataEditing.resourceTitle")
    public String metadataEditingResourceTitle();
    
    @Key("metadataEditing.resourceAbstract")
    public String metadataEditingResourceAbstract();
    
    @Key("metadataEditing.title")
    public String metadataEditingTitle();
    
    @Key("metadataEditing.resourceURL")
    public String metadataEditingResourceURL();
    
    @Key("headerView.notConnectedMessage")
    public String headerViewNotConnectedMessage();
    
    @Key("systemView.header")
    public String systemViewHeader();
    
    @Key("versionView.label")
    public String versionViewLabel();
    
    @Key("languageSwitching.title")
    public String languageSwitchingTitle();
    
    @Key("languageSwitching.message")
    public String languageSwitchingMessage();
    
    @Key("metadataListing.loading")
    public String metadataListingLoadingMessage();
    
    @Key("loginView.instructionMessage")
    public String loginViewInstructionMessage();
    
    @Key("loginView.login")
    public String loginViewLogin();
    
    @Key("loginView.password")
    public String loginViewPassword();
    
    @Key("loginView.connect")
    public String loginViewConnect();
    
    @Key("loginView.connectSuccessMessage")
    public String loginViewConnectSuccessMessage();
    
    @Key("loginView.connectFailureMessage")
    public String loginViewConnectFailureMessage();
    
    @Key("loginView.header")
    public String loginViewHeader();
    
    @Key("observatoryManagementView.header")
    public String observatoryManagementViewHeader();
    
    @Key("observatoryEditingView.creationHeader")
    public String observatoryEditingViewCreationHeader();
    
    @Key("observatoryEditingView.modificationHeader")
    public String observatoryEditingViewModificationHeader();
    
    @Key("observatoryEditingView.deletionConfirmationMessage")
	public String drainageBasinDeletionConfirmationMessage();
    
    @Key("observatoryManagementView.observatoryList")
    public String observatoryManagementObservatoryList();
    
    @Key("observatoryEditingView.drainageBasinList")
    public String observatoryEditingDrainageBasinList();
    
    @Key("observatoryEditingView.climateListText")
	public String climateListText();
    
    @Key("observatoryEditingView.climateItems")
	public String climateItems();
    
    @Key("observatoryEditingView.lithologyListText")
   	public String lithologyListText();
       
    @Key("observatoryEditingView.lithologyItems")
   	public String lithologyItems();
    
    @Key("drainageBasinEditingView.creationHeader")
    public String drainageBasinEditingViewCreationHeader();
    
    @Key("drainageBasinEditingView.modificationHeader")
    public String drainageBasinEditingViewModificationHeader();
    
    @Key("observatoryManagementView.deletionConfirmationMessage")
	public String observatoryDeletionConfirmationMessage();
    
    @Key("commons.emptyList")
    public String commonsEmptyList();

    @Key("commons.localization")
    public String localization();
    
    @Key("welcomeView.header")
	public String welcomeViewHeader();

    @Key("metadataSearchingView.header")
	public String metadataShearchingTitle();

    @Key("metadataListingView.header")
	public String listingViewTitle();

	@Key("yes")
	public String yes();
	
	@Key("no")
	public String no();
	
	@Key("delete")
	public String delete();
	
	@Key("label")
	public String label();
	
	@Key("edit")
	public String edit();
	
	@Key("confirm")
	public String confirm();
	
	@Key("error")
	public String error();
	
	@Key("anErrorHasHappened")
	public String anErrorHasHappened();
	
	@Key("observatory.shortLabel")
	public String observatoryShortLabel();
	
	@Key("observatory.longLabel")
	public String observatoryLongLabel();
	
	@Key("observatory.description")
	public String observatoryDescription();
	
	@Key("commons.name")
	public String name();
	
	@Key("commons.save")
    public String save();
	
	@Key("commons.saving")
    public String saving();

	@Key("mapSelector.northLatitude")
	public String mapSelectorNorthLatitude();
	
	@Key("mapSelector.southLatitude")
	public String mapSelectorSouthLatitude();
	
	@Key("mapSelector.eastLongitude")
	public String mapSelectorEastLongitude();
	
	@Key("mapSelector.westLongitude")
	public String mapSelectorWestLongitude();
    
	@Key("commons.generalDescription")
	public String generalDescription();

	@Key("drainageBasinTable.addItemText")
	public String DrainagaBasinTableAddItemText();
	
	@Key("siteTable.addItemText")
	public String SiteTableAddItemText();
	
    @Key("drainageBasinEditingView.SiteList")
    public String drainageBasinEditingSiteList();
	
    @Key("common.unsavedModificationsConfirmation")
    public String unsavedModificationsConfirmation();
    
	
}
