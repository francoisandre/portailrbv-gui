package fr.obsmip.sedoo.client.ui.tabs.edit;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Image;

public class ImagedActionCell<C> extends AbstractCell<C> {

	/**
	 * The delegate that will handle events from the cell.
	 * 
	 * @param <T>
	 *            the type that this delegate acts on
	 */
	public static interface Delegate<T> {
		/**
		 * Perform the desired action on the given object.
		 * 
		 * @param object
		 *            the object to be acted upon
		 */
		void execute(T object);
	}

	private final SafeHtml html;
	private final Delegate<C> delegate;
	private final Image img;

	/**
	 * Construct a new {@link ImagedActionCell}.
	 * 
	 * @param message
	 *            the message to display on the button
	 * @param delegate
	 *            the delegate that will handle events
	 */
	public ImagedActionCell(Delegate<C> del, Image i, String title) {
		super("click", "keydown");
		this.img = i;
		img.getUrl();
		this.delegate = del;
		this.html = new SafeHtmlBuilder().
				appendHtmlConstant("<img src=\"" + img.getUrl()	+ "\" tabindex=\"-1\" title=\""+title+"\" class=\"poubelle clickableImage\" />")
				.toSafeHtml();
	}

	
	@Override
	public void onBrowserEvent(Context context, Element parent, C value,
			NativeEvent event, ValueUpdater<C> valueUpdater) {
		super.onBrowserEvent(context, parent, value, event, valueUpdater);
		if ("click".equals(event.getType())) {
			EventTarget eventTarget = event.getEventTarget();
			if (!Element.is(eventTarget)) {
				return;
			}
			if (parent.getFirstChildElement().isOrHasChild(
					Element.as(eventTarget))) {
				// Ignore clicks that occur outside of the main element.
				onEnterKeyDown(context, parent, value, event, valueUpdater);
			}
		}
	}

	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context,
			C value, SafeHtmlBuilder sb) {
		sb.append(html);
	}
	
	@Override
	  protected void onEnterKeyDown(Context context, Element parent, C value,
	      NativeEvent event, ValueUpdater<C> valueUpdater) {
	    delegate.execute(value);
	  }

}
