package fr.obsmip.sedoo.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import fr.obsmip.sedoo.client.event.ActivityChangeEvent;
import fr.obsmip.sedoo.client.mvp.Presenter;

public class SectionHeaderViewImpl extends Composite implements SectionHeaderView {

	private static SectionHeaderViewImplUiBinder uiBinder = GWT
			.create(SectionHeaderViewImplUiBinder.class);

	interface SectionHeaderViewImplUiBinder extends UiBinder<Widget, SectionHeaderViewImpl> {
	}

	 @UiField Label sectionTitle;


	  public SectionHeaderViewImpl() {
	    initWidget(uiBinder.createAndBindUi(this));
	    sectionTitle.setText("");
	  }


	@Override
	public void onNotification(ActivityChangeEvent event) {
		sectionTitle.setText(event.getActivityTitle());
		
	}

//	  @UiHandler("welcomeLink")
//	  void onWelcomeClicked(ClickEvent event) {
//		  
//		  getPresenter().goTo(new WelcomePlace());
//	  }
	  
	
}
