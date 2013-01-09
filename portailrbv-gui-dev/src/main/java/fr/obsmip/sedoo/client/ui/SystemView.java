package fr.obsmip.sedoo.client.ui;

import com.google.gwt.user.client.ui.IsWidget;

public interface SystemView extends IsWidget {

	void setApplicationVersion(String applicationVersion);
	void setJavaVersion(String javaVersion);
}
