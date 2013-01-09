package fr.obsmip.sedoo.client.message;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;
import com.google.gwt.i18n.client.LocalizableResource.Generate;
import com.google.gwt.i18n.client.Messages;

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
    
    @Key("metadataEditing.header")
    public String metadataEditingHeader();
    
    @Key("metadataCreating.title")
    public String metadataCreatingTitle();
    
    @Key("metadataDisplaying.title")
	public String metadataDisplayingTitle();
    
    @Key("metadataEditing.resourceURL")
    public String metadataEditingResourceURL();
    
    @Key("metadataEditing.URLDefaultValue")
    public String metadataEditingURLDefaultValue();
    
    @Key("metadataEditing.URLTableAddItemText")
    public String metadataEditingURLTableAddItemText();
    
    @Key("metadataEditing.URLDescriptionDefaultValue")
    public String metadataEditingURLDescriptionDefaultValue();
    
    @Key("metadataEditing.URLTableURLHeader")
    public String metadataEditingURLTableURLHeader();
    
    @Key("metadataEditing.URLTableDescriptionHeader")
    public String metadataEditingURLTableDescriptionHeader();
        
    @Key("metadataEditing.resourceIdentifier")
    public String metadataEditingResourceIdentifier();
    
    @Key("metadataEditing.resourceIdentifierAddItemText")
    public String metadataEditingResourceIdentifierAddItemText();
    
    @Key("metadataEditing.resourceIdentifierWaterMark")
    public String metadataEditingResourceIdentifierWaterMark();
    
    @Key("metadataEditing.identificationTabHeader")
    public String  metadataEditingIdentificationTabHeader();
    
    @Key("metadataEditing.geographicalLocationTabHeader")
    public String  metadataEditingGeographicalLocationTabHeader();
    
    @Key("metadataEditing.constraintTabHeader")
    public String  metadataEditingConstraintTabHeader();
    
    @Key("metadataEditing.temporalExtentHeader")
    public String  metadataEditingTemporalExtentHeader();
    
    @Key("metadataEditing.metadataTabHeader")
    public String  metadataEditingMetadataTabHeader();
    
    @Key("metadataEditing.metatadataLanguage")
    public String  metadataEditingMetatadataLanguage();
  
    @Key("metadataEditing.metatadaEditingMetadataLastModificationDate")
    public String  metatadaEditingMetadataLastModificationDate();
    
    @Key("metadataEditing.useConditions")
    public String  metadataEditingUseConditions();
    
    @Key("metadataEditing.publicAccessLimitations")
    public String  metadataEditingPublicAccessLimitations();
    
    @Key("metadataEtiting.metadataContactTable.addItemText")
	public String metadataContactTableAddItemText();
    
    @Key("metadataEtiting.metadataContactList")
   	public String metadataContactList();
    
    @Key("metadataEtiting.resourceContactList")
    public String resourceContactList();
    
    @Key("metadataEditing.startDate")
    public String  metadataEditingStartDate();
    
    @Key("metadataEditing.endDate")
    public String  metadataEditingEndDate();
    
    @Key("metadataEditing.lastModificationDate")
    public String  metadataEditingLastModificationDate();
    
    @Key("metadataEditing.state")
    public String metadataEditingState();
    
    @Key("metadataEditing.title")
    public String metadataEditingTitle();
    
    @Key("metadataEditing.abstract")
    public String metadataEditingAbstract();
    
    @Key("metadataEditing.linkWithEndDate")
    public String  metadataEditingLinkWithEndDate();
    
    @Key("headerView.notConnectedMessage")
    public String headerViewNotConnectedMessage();
    
    @Key("systemView.header")
    public String systemViewHeader();
    
    @Key("systemView.applicationVersion")
    public String systemViewApplicationVersion();
    
    @Key("systemView.javaVersion")
    public String systemViewJavaVersion();
    
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
    
    @Key("observatoryEditingView.contactList")
    public String observatoryEditingContactList();
    
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
   
    @Key("drainageBasinEditingView.siteName")
    public String drainageBasinEditingViewSiteName();
   
    @Key("drainageBasinEditingView.siteLongitude")
    public String drainageBasinEditingViewSiteLongitude();
    
    @Key("drainageBasinEditingView.siteLatitude")
    public String drainageBasinEditingViewSiteLatitude();
    
    @Key("drainageBasinEditingView.siteAltitude")
    public String drainageBasinEditingViewSiteAltitude();
    
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
	
	@Key("ok")
	public String ok();
	
	@Key("cancel")
	public String cancel();
	
	@Key("delete")
	public String delete();
	
	@Key("label")
	public String label();
	
	@Key("edit")
	public String edit();
	
	@Key("print")
	public String print();
	
	@Key("view")
	public String view();
	
	@Key("confirm")
	public String confirm();
	
	@Key("error")
	public String error();
	
	@Key("loading")
	public String loading();
	
	@Key("now")
	public String now();
	
	@Key("anErrorHasHappened")
	public String anErrorHasHappened();
	
	@Key("observatory.shortLabel")
	public String observatoryShortLabel();
	
	@Key("observatory.longLabel")
	public String observatoryLongLabel();
	
	@Key("observatory.description")
	public String observatoryDescription();
	
	@Key("commons.save")
    public String save();
	
	@Key("commons.back")
    public String back();
	
	@Key("commons.saving")
    public String saving();
	
	@Key("commons.name")
    public String name();
	
	@Key("commons.iso8601Format")
    public String iso8601Format();
	
	@Key("commons.deletionConfirmMessage")
    public String deletionConfirmMessage();

	@Key("commons.mandatoryData")
    public String mandatoryData();

	@Key("commons.numericalData")
	public String numericalData();
	
	@Key("commons.emailData")
	public String emailData();
	
	@Key("commons.dateData")
	public String dateData();
	
	@Key("commons.dateUnconsistency")
	public String dateUnconsistency();
	
	@Key("commons.atLeastOneElementNeeded")
	public String atLeastOneElementNeeded();
	
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
	
	@Key("observatoryContactTable.addItemText")
	public String observatoryContactTableAddItemText();
	
    @Key("drainageBasinEditingView.SiteList")
    public String drainageBasinEditingSiteList();
	
    @Key("common.unsavedModificationsConfirmation")
    public String unsavedModificationsConfirmation();

    @Key("common.unsavedCreationConfirmation")
    public String unsavedCreationConfirmation();
    
    @Key("common.savedModifications")
    public String savedModifications();
    
    @Key("common.addedElement")
    public String addedElement();
    
    @Key("person.personName")
    public String personPersonName();
    
    @Key("person.organisationName")
    public String personOrganisationName();
    
    @Key("person.email")
    public String personEmail();
    
	@Key("person.roles")
	public String personRoles();
	
	@Key("person.roleItems")
	public String personRoleItems();
	
	@Key("observatoryContactEditingView.creationHeader")
    public String observatoryContactEditingViewCreationHeader();
    
    @Key("observatoryContactEditingView.modificationHeader")
    public String observatoryContactEditingViewModificationHeader();

    @Key("commons.observatory")
	public String observatory();
    
    @Key("commons.drainageBasin")
	public String drainageBasin();
    
    @Key("metadataListing.addMetadataEntry")
	public String addMetadataEntry();
    
    @Key("metadataEditing.addContactFromDirectory")
    public String metadataEditingAddContactFromDirectory();
    
    @Key("metadataEditing.selectContactFromList")
    public String metadataEditingSelectContactFromList();

    @Key("mapSelector.drawDrainageBasinButtonTooltip")
	public String drawDrainageBasinButtonTooltip();
    
    @Key("mapSelector.eraseDrainageBasinButtonTooltip")
	public String eraseDrainageBasinButtonTooltip();
    
    @Key("mapSelector.eraseSiteButtonTooltip")
	public String eraseSiteButtonTooltip();

    @Key("mapSelector.drawSiteButtonTooltip")
	public String drawSiteButtonTooltip();
    
    @Key("mapSelector.dragPanButtonTooltip")
   	public String dragPanButtonTooltip();

	@Key("commons.filter")
	public String filter();
	
	@Key("metadataListing.entryList")
	public String metadataListingEntryList();

	@Key("metadataListing.noDrainageBasinSelected")
	public String metadataListinNoDrainageBasinSelected();
	
	@Key("commons.selectItem")
	public String selectItem();

	@Key("metadataProviderMenu.manageMetadata")
	public String metadataProviderMenuManageMetadata();
	
	@Key("metadataUserMenu")
	public String metadataUserMenu();
	
	@Key("metadataProviderMenu")
	public String metadataProviderMenu();
	
	@Key("administrationMenu")
	public String administrationMenu();

	
}
