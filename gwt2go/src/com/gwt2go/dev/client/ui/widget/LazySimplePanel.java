/*
 * Copyright 2012
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
package com.gwt2go.dev.client.ui.widget;

import com.google.gwt.event.dom.client.DragStartEvent;
import com.google.gwt.event.dom.client.DragStartHandler;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LazyPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class LazySimplePanel extends LazyPanel {

	private Element nameSpan;
	
	public LazySimplePanel(Element nameSpan) {
		this.nameSpan = nameSpan;
	}
	
	@Override
	protected Widget createWidget() {
		SimplePanel panel = new SimplePanel();
		
		final Label label = new Label("Drag me");
		label.getElement().setDraggable(Element.DRAGGABLE_TRUE);
		
		label.addDragStartHandler(new DragStartHandler() {
			
			@Override
			public void onDragStart(DragStartEvent event) {
				// required
				event.setData("text", "Hello World");
				
				// optinal: show copy of the image
				event.getDataTransfer().setDragImage(label.getElement(), 10, 10);
			}
		});
		
		panel.getElement().appendChild(this.nameSpan);
		return panel;
	}

}
