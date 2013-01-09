package fr.obsmip.sedoo.client.ui.misc;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.safehtml.shared.UriUtils;

import fr.obsmip.sedoo.client.domain.IdentifiedDescribedString;

public class AnchorCell extends AbstractCell<IdentifiedDescribedString>
{
	private static Template template;

	interface Template extends SafeHtmlTemplates {
	    @Template("- <a href=\"{0}\" target=\"_blank\">{1}</a> ({2})")
	    SafeHtml hyperText(SafeUri link, String text, SafeHtml link2);
	  }

	public AnchorCell() 
	{
		super();
        if (template == null) 
        {
        	template = GWT.create(Template.class);
		}
	}

	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context,
			IdentifiedDescribedString s, SafeHtmlBuilder sb)
	{
		sb.append(template.hyperText(UriUtils.fromString(s.getValue()), s.getDescription(), SafeHtmlUtils.fromString(s.getValue())));
	}

}