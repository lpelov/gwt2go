package com.gwt2go.dev.client.ui.widget.dnd;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LazyPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Try to use basic drag and drop techniques from DropBox widget to custom
 * widget
 * @author lpelov
 *
 */
public class DragDropWidget extends LazyPanel implements MouseDownHandler {

	private boolean dragging;
	private int dragStartX, dragStartY;
//	private int windowWidth;
//	private int clientLeft;
//	private int clientTop;

	private Element nameSpan;
	SimplePanel panel = new SimplePanel();
	
	public DragDropWidget(Element nameSpan) {
		this.nameSpan = nameSpan;

		MouseHandler mouseHandler = new MouseHandler();
		addDomHandler(mouseHandler, MouseDownEvent.getType());
		addDomHandler(mouseHandler, MouseUpEvent.getType());
		addDomHandler(mouseHandler, MouseMoveEvent.getType());
		addDomHandler(mouseHandler, MouseOverEvent.getType());
		addDomHandler(mouseHandler, MouseOutEvent.getType());
		
	}

	@Override
	protected Widget createWidget() {
		
		VerticalPanel vrPanel = new VerticalPanel();
		vrPanel.setBorderWidth(2);
		panel.add(vrPanel);

		final Label label = new Label("Complex widget drag example");

		vrPanel.getElement().appendChild(this.nameSpan);
		vrPanel.add(label);

		return panel;

	}

	// implement the drag and drop functionality

	private class MouseHandler implements MouseDownHandler, MouseUpHandler,
			MouseOutHandler, MouseOverHandler, MouseMoveHandler {

		public void onMouseDown(MouseDownEvent event) {
			System.out.println("Mouse down");
			dragging = true;
			// beginDragging(event);
//			if (DOM.getCaptureElement() == null) {
//				/*
//				 * Need to check to make sure that we aren't already capturing
//				 * an element otherwise events will not fire as expected. If
//				 * this check isn't here, any class which extends custom button
//				 * will not fire its click event for example.
//				 */
//				dragging = true;
//				DOM.setCapture(panel.getElement());
				dragStartX = event.getX();
				dragStartY = event.getY();
//			}

		}

		public void onMouseMove(MouseMoveEvent event) {
			if (dragging) {
				System.out.println("Mouse was moved");

				// continueDragging(event);
				int x = event.getX();
				int y = event.getY();

				int absX = x + getAbsoluteLeft();
				int absY = y + getAbsoluteTop();

				// if the mouse is off the screen to the left, right, or top,
				// don't
				// move the dialog box. This would let users lose dialog boxes,
				// which
				// would be bad for modal popups.
				/*if (absX < clientLeft || absX >= windowWidth
						|| absY < clientTop) {
					System.out.println("exit");
					return;
				}*/
				
				setPopupPosition(absX - dragStartX, absY - dragStartY);
			}

		}

		public void onMouseOut(MouseOutEvent event) {
			// DialogBox.this.onMouseLeave(caption.asWidget());
		}

		public void onMouseOver(MouseOverEvent event) {
			// DialogBox.this.onMouseEnter(caption.asWidget());
		}

		public void onMouseUp(MouseUpEvent event) {
			// endDragging(event);
			dragging = false;
			DOM.releaseCapture(panel.getElement());
		}
	}

	// private int leftPosition = -1;
	// The top style attribute in pixels
	// private int topPosition = -1;

	/**
	 * Sets the popup's position relative to the browser's client area. The
	 * popup's position may be set before calling {@link #show()}.
	 * 
	 * @param left
	 *            the left position, in pixels
	 * @param top
	 *            the top position, in pixels
	 */
	public void setPopupPosition(int left, int top) {
		System.out.println("Set possition: " + left + ":" + top );
		
		// Save the position of the popup
		// leftPosition = left;
		// topPosition = top;

		// Account for the difference between absolute position and the
		// body's positioning context.
		left -= Document.get().getBodyOffsetLeft();
		top -= Document.get().getBodyOffsetTop();

		// Set the popup's position manually, allowing setPopupPosition() to be
		// called before show() is called (so a popup can be positioned without
		// it
		// 'jumping' on the screen).
		Element elem = panel.getElement();
		elem.getStyle().setPropertyPx("left", left);
		elem.getStyle().setPropertyPx("top", top);
	}

	@Override
	public void onBrowserEvent(Event event) {
		// If we're not yet dragging, only trigger mouse events if the event
		// occurs
		// in the caption wrapper
		switch (event.getTypeInt()) {
		case Event.ONMOUSEDOWN:
		case Event.ONMOUSEUP:
		case Event.ONMOUSEMOVE:
		case Event.ONMOUSEOVER:
		case Event.ONMOUSEOUT:
			if (!dragging && !isCaptionEvent(event)) {
				return;
			}
		}

		super.onBrowserEvent(event);
	}

	private boolean isCaptionEvent(NativeEvent event) {
		EventTarget target = event.getEventTarget();
		if (Element.is(target)) {
			return getElement().getParentElement().isOrHasChild(
					Element.as(target));
		}
		return false;
	}

	@Override
	public void onMouseDown(MouseDownEvent event) {
		
		System.out.println("Mouse down - again");
		
	}

	// protected void onPreviewNativeEvent(NativePreviewEvent event) {
	// // We need to preventDefault() on mouseDown events (outside of the
	// // DialogBox content) to keep text from being selected when it
	// // is dragged.
	// NativeEvent nativeEvent = event.getNativeEvent();
	//
	// if (!event.isCanceled() && (event.getTypeInt() == Event.ONMOUSEDOWN)
	// && isCaptionEvent(nativeEvent)) {
	// nativeEvent.preventDefault();
	// }
	//
	//
	// if (event.isFirstHandler()
	// && !onEventPreview(Event.as(event.getNativeEvent()))) {
	// event.cancel();
	// }
	//
	// }

}
