package fr.obsmip.sedoo.client.activity;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.thirdparty.javascript.jscomp.MessageBundle;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.obsmip.sedoo.client.ClientFactory;
import fr.obsmip.sedoo.client.domain.DrainageBasinDTO;
import fr.obsmip.sedoo.client.domain.ObservatoryDTO;
import fr.obsmip.sedoo.client.message.Message;
import fr.obsmip.sedoo.client.place.DrainageBasinEditingPlace;
import fr.obsmip.sedoo.client.service.ObservatoryService;
import fr.obsmip.sedoo.client.service.ObservatoryServiceAsync;
import fr.obsmip.sedoo.client.ui.DrainageBasinEditingView;
import fr.obsmip.sedoo.client.ui.DrainageBasinEditingView.Presenter;
import fr.obsmip.sedoo.client.ui.misc.DialogBoxTools;

public class DrainageBasinEditingActivity extends RBVAbstractActivity implements Presenter{

	private Long id;
	private String mode;
	private DrainageBasinEditingView drainageBasinEditingView;

	public DrainageBasinEditingActivity(DrainageBasinEditingPlace place, ClientFactory clientFactory) {
		super(clientFactory);
		
		if (place.getId() != null)
		{
			id = place.getId();
		}
		if (place.getMode() != null)
		{
			mode = place.getMode();
		}
	}


	private final ObservatoryServiceAsync observatoryService = GWT.create(ObservatoryService.class);
	DrainageBasinEditingView dr;


	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		drainageBasinEditingView = clientFactory.getDrainageBasinEditingView();
		drainageBasinEditingView.setPresenter(this);
		containerWidget.setWidget(drainageBasinEditingView.asWidget());
		if (mode.compareTo(DrainageBasinEditingPlace.MODIFY) == 0)
		{
			observatoryService.getDrainageBasinById(id, new AsyncCallback<DrainageBasinDTO>() {

				@Override
				public void onSuccess(DrainageBasinDTO result) {
					broadcastActivityTitle(Message.INSTANCE.drainageBasinEditingViewModificationHeader() +" ("+result.getLabel()+")");
					drainageBasinEditingView.edit(result);

				}

				@Override
				public void onFailure(Throwable caught) {
					DialogBoxTools.modalAlert(Message.INSTANCE.error(), Message.INSTANCE.anErrorHasHappened()+" : "+caught.getMessage());

				}
			});
			
			
		}
		else
		{
			drainageBasinEditingView.edit(new DrainageBasinDTO());
			broadcastActivityTitle(Message.INSTANCE.drainageBasinEditingViewCreationHeader());
		}
	}





}
