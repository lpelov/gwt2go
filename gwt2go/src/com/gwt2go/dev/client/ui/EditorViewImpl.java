/*
 * Copyright 2010
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.gwt2go.dev.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DragOverEvent;
import com.google.gwt.event.dom.client.DragOverHandler;
import com.google.gwt.event.dom.client.DragStartEvent;
import com.google.gwt.event.dom.client.DragStartHandler;
import com.google.gwt.event.dom.client.DropEvent;
import com.google.gwt.event.dom.client.DropHandler;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.SimplePanel;
import com.gwt2go.dev.client.ui.editor.RichTextToolbar;
import com.google.gwt.user.client.Element;

/**
 * Editor view implementation.
 * 
 * @author L.Pelov
 */
public class EditorViewImpl extends Composite implements EditorView {

	SimplePanel viewPanel = new SimplePanel();
	Element nameSpan = DOM.createSpan();
	Presenter presenter;
	SimplePager pager;
	// / ADD WYSIWYG Editor here now
	final RichTextArea area = new RichTextArea();
	
	final Label lbDrag = new Label("Drag me");
	final Label lbTarget = new Label("Drag on me");
	
	RichTextToolbar toolbar = new RichTextToolbar(area);
	Button btnSelectedText = new Button("Get Selected Text");

	public EditorViewImpl() {

		area.ensureDebugId("cwRichText-area");
		area.setSize("100%", "14em");

		btnSelectedText.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// Window.alert(area.getElement().toString());
				com.google.gwt.user.client.Element iFrame = area.getElement();

				String txt = getSelectedElement(iFrame).toString();
				// String txt = EditorUtils.getSelectedElement(iFrame);
				Window.alert(txt);
				// String name = flipName("lyudminame");
				// Window.alert(name);
			}
		});

		// Add the components to a panel
		Grid grid = new Grid(5, 1);
		grid.setStyleName("cw-RichText");
		grid.setWidget(0, 0, toolbar);
		grid.setWidget(1, 0, area);
		grid.setWidget(2, 0, btnSelectedText);
		grid.setWidget(3, 0, lbDrag);
		grid.setWidget(4, 0, lbTarget);
		grid.setBorderWidth(1);

		toolbar.ensureDebugId("cwRichText-toolbar");
		toolbar.setWidth("100%");

		viewPanel.getElement().appendChild(nameSpan);

		viewPanel.add(grid);

		lbDrag.getElement().setDraggable(Element.DRAGGABLE_TRUE);
		lbDrag.addDragStartHandler(new DragStartHandler() {
			@Override
			public void onDragStart(DragStartEvent event) {
				// Required set data
				event.setData("text", "hello drag me");
				
				event.getDataTransfer().setDragImage(lbDrag.getElement(), 10, 10);
			}
		});
		
		btnSelectedText.getElement().setDraggable(Element.DRAGGABLE_TRUE);
		btnSelectedText.addDragStartHandler(new DragStartHandler() {
			
			@Override
			public void onDragStart(DragStartEvent event) {
				// Required set data
				event.setData("text", "button drag");
				
				event.getDataTransfer().setDragImage(btnSelectedText.getElement(), 10, 10);				
			}
		});
		
		// alternative way to start drag and drop
//		lbDrag.addDomHandler(new DragStartHandler() {
//			
//			@Override
//			public void onDragStart(DragStartEvent event) {
//				// Required set data
//				event.setData("text", "hello drag me");
//				
//				event.getDataTransfer().setDragImage(lbDrag.getElement(), 10, 10);
//				
//			}
//		}, DragStartEvent.getType());

		
		lbTarget.addDragOverHandler(new DragOverHandler() {
			
			@Override
			public void onDragOver(DragOverEvent event) {
				lbTarget.getElement().getStyle().setBackgroundColor("#ffa");				
			}
		});
		
		lbTarget.addDropHandler(new DropHandler() {
			
			@Override
			public void onDrop(DropEvent event) {
				event.preventDefault();
				
				String data = event.getData("text");
				lbTarget.setText(data);
			}
		});
			
		
		initWidget(viewPanel);

	}

	@Override
	public void setName(String tableName) {
		nameSpan.setInnerText("Table name: " + tableName);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;

	}

	public native String getSelectedElement(Element elm) /*-{
		var txt = "no text";
		txt = elm.contentWindow.getSelection();
		//$wnd.alert(txt);
		return txt.toString();
	}-*/;

	public native String getSelection()/*-{
		var txt = "bla";
		if ($wnd.getSelection) {
			txt = $wnd.getSelection();
		} else if ($wnd.document.getSelection) {
			txt = $wnd.document.getSelection();
		} else if ($wnd.document.selection) {
			txt = $wnd.document.selection.createRange().text;
		} else {
			txt = "nix";
		}
		$wnd.alert(txt);
		return txt;
	}-*/;

	// Java method declaration...
	native String flipName(String name) /*-{

		// ...implemented with JavaScript
		var re = /(\w+)\s(\w+)/;
		return name.replace(re, '$2, $1');

	}-*/;

}
