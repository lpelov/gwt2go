package com.gwt2go.dev.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class RightSide extends Composite {

	private static RightSideUiBinder uiBinder = GWT
			.create(RightSideUiBinder.class);

	interface RightSideUiBinder extends UiBinder<Widget, RightSide> {
	}

	@UiField
	Button button;

	public RightSide() {
		initWidget(uiBinder.createAndBindUi(this));
		button.setText("rightside");
	}

	@UiHandler("button")
	void onClick(ClickEvent e) {
		Window.alert("Hello!");
	}
}
