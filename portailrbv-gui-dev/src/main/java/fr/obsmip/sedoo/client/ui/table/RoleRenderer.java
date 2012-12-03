package fr.obsmip.sedoo.client.ui.table;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.text.shared.SafeHtmlRenderer;

import fr.obsmip.sedoo.client.ui.misc.RoleLabelProvider;

/**
 * @author francois
 *
 */
public class RoleRenderer implements SafeHtmlRenderer<String>{

	 private static RoleRenderer instance;

	  public static RoleRenderer getInstance() {
	    if (instance == null) {
	      instance = new RoleRenderer();
	    }
	    return instance;
	  }

	  private RoleRenderer() {
	  }

	  public SafeHtml render(String object) {
		  if (object == null)
		  {
			  return SafeHtmlUtils.EMPTY_SAFE_HTML;
		  }
		  else
		  {
			  String asString = SafeHtmlUtils.fromString(object).asString();
			 return safeHtmlFromString(asString);
		  }
	  }

	  /**
	   * On remplace les valeurs par leur libellés
	   * Les valeurs non reconnues sont ignorées
	   * un saut de ligne est rajouté
	   * @param aux
	   * @return
	   */
	  private SafeHtml safeHtmlFromString(String aux) 
	  {
		  StringBuilder sb = new StringBuilder(); 
		  String[] split = aux.split("\\|");
		  boolean first = true;
		  for (int i = 0; i < split.length; i++) 
		  {
			String value = RoleLabelProvider.getLabel(split[i]);
			if (value != null)
			{
				if (first)
				{
					first = false;
				}
				else
				{
					sb.append("<br>");
				}
				sb.append(value);
			}
		  }
		  return SafeHtmlUtils.fromTrustedString(sb.toString());
	  }

	public void render(String object, SafeHtmlBuilder appendable) {
	    
		  if (object == null)
		  {
			  appendable.append(SafeHtmlUtils.fromString(object));
		  }
		  else
		  {
			  String asString = SafeHtmlUtils.fromString(object).asString();
			  appendable.append(safeHtmlFromString(asString));
		  }
	  }

}
