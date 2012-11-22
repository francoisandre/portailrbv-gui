package fr.obsmip.sedoo.client.ui;

import fr.obsmip.sedoo.client.domain.ObservatoryContactDTO;

public interface ObservatoryContactEditingView extends DTOEditingView {
	
	void setPresenter(Presenter presenter);
	
	public interface Presenter 
	 {
		void save(ObservatoryContactDTO flush);
		void back();
	 }

	ObservatoryContactDTO flush();


}
