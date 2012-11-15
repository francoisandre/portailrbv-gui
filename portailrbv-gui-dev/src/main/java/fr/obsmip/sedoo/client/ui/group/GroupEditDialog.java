package fr.obsmip.sedoo.client.ui.group;

import com.google.gwt.user.client.ui.DialogBox;

public class GroupEditDialog extends DialogBox
{
	public GroupEditDialog() {
		GroupEditPane pane = new GroupEditPane();
		setText("Ajout/Modification d'un observatoire");
		setWidget(pane);
	}
}
