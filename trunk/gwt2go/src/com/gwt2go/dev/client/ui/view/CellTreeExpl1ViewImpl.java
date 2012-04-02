package com.gwt2go.dev.client.ui.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class CellTreeExpl1ViewImpl extends Composite implements CellTreeExpl1View {

	private static CellTreeExpl1ViewImplUiBinder uiBinder = GWT
			.create(CellTreeExpl1ViewImplUiBinder.class);

	interface CellTreeExpl1ViewImplUiBinder extends
			UiBinder<Widget, CellTreeExpl1ViewImpl> {
	}

	SimplePanel viewPanel = new SimplePanel();
	Element nameSpan = DOM.createSpan();
	Presenter presenter;

	public CellTreeExpl1ViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	Button button;

	public CellTreeExpl1ViewImpl(String firstName) {
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

	@Override
	public void setName(String tableName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPresenter(Presenter presenter) {
		// TODO Auto-generated method stub
		
	}

}
