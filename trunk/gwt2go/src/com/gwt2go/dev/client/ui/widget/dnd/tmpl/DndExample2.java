package com.gwt2go.dev.client.ui.widget.dnd.tmpl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

/**
 * TODO: take a look: http://google-web-toolkit.googlecode.com/svn/javadoc/latest/com/google/gwt/uibinder/client/LazyDomElement.html
 * TODO: https://developers.google.com/web-toolkit/doc/latest/DevGuideUiBinder
 * TODO: https://developers.google.com/web-toolkit/doc/latest/DevGuideSecuritySafeHtml
 * @author lpelov
 *
 */
public class DndExample2 extends Composite implements HasText {

	private static DndExample2UiBinder uiBinder = GWT
			.create(DndExample2UiBinder.class);

	interface DndExample2UiBinder extends UiBinder<Widget, DndExample2> {
	}

	public DndExample2() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	Button button;

	public DndExample2(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		button.setText(firstName);
	}

	@UiHandler("button")
	void onClick(ClickEvent e) {
		Window.alert("Hello!");
	}

	public void setText(String text) {
		button.setText(text);
	}

	public String getText() {
		return button.getText();
	}

}
