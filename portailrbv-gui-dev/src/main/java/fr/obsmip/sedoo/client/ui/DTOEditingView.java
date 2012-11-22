package fr.obsmip.sedoo.client.ui;

import com.google.gwt.user.client.ui.IsWidget;

import fr.obsmip.sedoo.client.domain.AbstractDTO;

public interface DTOEditingView  extends  IsWidget{

	AbstractDTO flush();
	void edit(AbstractDTO dto);
	void setMode(String mode);

}
