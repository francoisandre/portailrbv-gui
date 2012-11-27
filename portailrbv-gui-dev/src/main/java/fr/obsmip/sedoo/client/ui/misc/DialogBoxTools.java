package fr.obsmip.sedoo.client.ui.misc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.obsmip.sedoo.client.message.Message;

public class DialogBoxTools 
{

	private static final String DEFAULT_TITLE = "Informations";
	public static final String HTML_MODE = "HTML_MODE";
	public static final String TEXT_MODE = "TEXT_MODE";
	
	public static void popUp(String text) 
	{
		popUp(DEFAULT_TITLE, text, TEXT_MODE);
	}
	
	public static void popUp(String title, String text, String mode) 
	{
		String formattedText = text;
		if (mode.compareTo(TEXT_MODE)==0)
		{
			formattedText = SafeHtmlUtils.htmlEscape(text);
		}
		final DialogBox dialogBox = new DialogBox();
	    dialogBox.setText(title);
	    
	    VerticalPanel dialogContents = new VerticalPanel();
	    dialogContents.setSpacing(4);
	    HTML details = new HTML(formattedText);
	    ScrollPanel scrollPanel = new ScrollPanel(details);
	      scrollPanel.setSize("800px", "300px");

	      
	      
	      DecoratorPanel decoratorPanel = new DecoratorPanel();

	      decoratorPanel.add(scrollPanel);
	      
	      Button closeButton = new Button(
		            " Ok ", new ClickHandler() {
		              public void onClick(ClickEvent event) {
		                dialogBox.hide();
		              }
		            });
	      
	      
	      dialogContents.add(decoratorPanel);
	      dialogContents.add(closeButton);
	      dialogContents.setCellHorizontalAlignment(closeButton, HasHorizontalAlignment.ALIGN_CENTER);
	      
	      dialogBox.setWidget(dialogContents);
	    dialogBox.setGlassEnabled(true);
	    dialogBox.setAutoHideEnabled(true);
	    dialogBox.center();
	    dialogBox.show();
		
		
	}
	
	
	public static void popUp(String title, DialogBoxContent content) 
	{
		final DialogBox dialogBox = new DialogBox();
	    dialogBox.setText(title);
        dialogBox.setWidget(content);
        content.setDialogBox(dialogBox);
	    dialogBox.setGlassEnabled(true);
	    dialogBox.setAutoHideEnabled(true);
	    dialogBox.center();
	    dialogBox.show();
		
		
	}
	
	public static void modalAlert(final String header, final String content) {
        final DialogBox box = new DialogBox();
        final VerticalPanel panel = new VerticalPanel();
        box.setText(header);
        panel.add(new Label(content));
        final Button buttonClose = new Button("Close",new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                box.hide();
            }
        });
        // few empty labels to make widget larger
        final Label emptyLabel = new Label("");
        emptyLabel.setSize("auto","25px");
        panel.add(emptyLabel);
        panel.add(emptyLabel);
        buttonClose.setWidth("90px");
        panel.add(buttonClose);
        panel.setCellHorizontalAlignment(buttonClose, HasAlignment.ALIGN_RIGHT);
        box.add(panel);
        box.center();
        box.show();
    }
	
	public static DialogBox modalConfirm(final String header, final String content, final ConfirmCallBack callback) {
		
		Message message =  GWT.create(Message.class);
		
        final DialogBox box = new DialogBox();
        final VerticalPanel panel = new VerticalPanel();
        box.setText(header);
        panel.add(new Label(content));
        final Button buttonYes = new Button(message.yes(),new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                box.hide();
                callback.confirm(true);
            }
        });
        
        final Button buttonNo = new Button(message.no(),new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                box.hide();
                callback.confirm(false);
            }
        });
        // few empty labels to make widget larger
        final Label emptyLabel = new Label("");
        emptyLabel.setSize("auto","25px");
        panel.add(emptyLabel);
        panel.add(emptyLabel);
        panel.setWidth("100%");
        buttonYes.setWidth("90px");
        buttonNo.setWidth("90px");
        HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.setSpacing(5);
        horizontalPanel.setHorizontalAlignment(HasAlignment.ALIGN_CENTER);
        horizontalPanel.add(buttonYes);
        horizontalPanel.add(buttonNo);
        panel.add(horizontalPanel);
        box.setGlassEnabled(true);
	    box.setAutoHideEnabled(false);
        panel.setCellHorizontalAlignment(horizontalPanel, HasAlignment.ALIGN_CENTER);
        box.add(panel);
        return box;
    }
	
	

	
}
