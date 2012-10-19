/*
 * Copyright 2012, L.Pelov
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
package com.gwt2go.dev.client.ui.widget.dnd;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;

public class DndView2Impl extends Composite implements DndView {


	private final String name = "DndView2Impl";
	private DragDropWidget viewPanel;
	private Element nameSpan = DOM.createSpan();

	public DndView2Impl() {
		nameSpan.setInnerText("2 - Default text, again");
		viewPanel = new DragDropWidget(nameSpan);
		viewPanel.setVisible(true);
		initWidget(viewPanel);

	}

	@Override
	public void setName(String name) {
		if (name != null && name.length() > 0) {
			nameSpan.setInnerText("DND 2 Example name: " + name);
		} else {
			nameSpan.setInnerText("DND 2 Example name: " + this.name);
		}
	}

	@Override
	public void setPresenter(Presenter presenter) {
	}
}
