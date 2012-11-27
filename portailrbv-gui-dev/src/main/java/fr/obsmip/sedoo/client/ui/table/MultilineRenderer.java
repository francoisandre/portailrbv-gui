package fr.obsmip.sedoo.client.ui.table;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.text.shared.SafeHtmlRenderer;

public class MultilineRenderer implements SafeHtmlRenderer<String>{

	 private static MultilineRenderer instance;

	  public static MultilineRenderer getInstance() {
	    if (instance == null) {
	      instance = new MultilineRenderer();
	    }
	    return instance;
	  }

	  private MultilineRenderer() {
	  }

	  public SafeHtml render(String object) {
		  if (object == null)
		  {
			  return SafeHtmlUtils.EMPTY_SAFE_HTML;
		  }
		  else
		  {
			  String asString = SafeHtmlUtils.fromString(object).asString();
			  return SafeHtmlUtils.fromTrustedString(asString.replace("|","<br/>"));
		  }
	  }

	  public void render(String object, SafeHtmlBuilder appendable) {
	    
		  if (object == null)
		  {
			  appendable.append(SafeHtmlUtils.fromString(object));
		  }
		  else
		  {
			  String asString = SafeHtmlUtils.fromString(object).asString();
			  appendable.append(SafeHtmlUtils.fromTrustedString(asString.replace("|","<br/>")));
		  }
	  }

}
