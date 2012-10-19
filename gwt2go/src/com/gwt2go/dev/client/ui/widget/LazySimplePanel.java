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

import com.google.gwt.event.dom.client.DragOverEvent;
import com.google.gwt.event.dom.client.DragOverHandler;
import com.google.gwt.event.dom.client.DragStartEvent;
import com.google.gwt.event.dom.client.DragStartHandler;
import com.google.gwt.event.dom.client.DropEvent;
import com.google.gwt.event.dom.client.DropHandler;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LazyPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class LazySimplePanel extends LazyPanel {

	private Element nameSpan;	
	
	public LazySimplePanel(Element nameSpan) {
		this.nameSpan = nameSpan;
	}

	@Override
	protected Widget createWidget() {
		SimplePanel panel = new SimplePanel();
		VerticalPanel vrPanel = new VerticalPanel();

		final Label label = new Label("Drag me");
		label.getElement().setDraggable(Element.DRAGGABLE_TRUE);

		label.addDragStartHandler(new DragStartHandler() {

			@Override
			public void onDragStart(DragStartEvent event) {
				// required
				event.setData("text", "Hello World");
				
				// optinal: show copy of the image
				event.getDataTransfer()
						.setDragImage(label.getElement(), 10, 10);
			}
		});

		// some widgets do not implement drag handles
		// for that reason you can use addDomHandler()
		/* label.addDomHandler(new DragStartHandler() {
			@Override
			public void onDragStart(DragStartEvent event) {
				// required
				event.setData("text", "Hello World again;)");
				
				// optional
				event.getDataTransfer().setDragImage(label.getElement(), 10, 10);
			}
		}, DragStartEvent.getType()); */
		
		
		final Label target = new Label("Drag onto me");
		// required: you must add dragoverhandler to create a target
		target.addDragOverHandler(new DragOverHandler() {
			@Override
			public void onDragOver(DragOverEvent event) {
				target.getElement().getStyle().setBackgroundColor("#ffa");
			}
		});

		// add drop hanlder
		target.addDropHandler(new DropHandler() {
			@Override
			public void onDrop(DropEvent event) {
				// prevent the native text drop
				event.preventDefault();

				// get the data out of the event
				String data = event.getData("text");
				target.setText(data);
			}
		});

		panel.add(vrPanel);
		vrPanel.getElement().appendChild(this.nameSpan);
		vrPanel.add(label);
		vrPanel.add(target);
		return panel;
	}

}
