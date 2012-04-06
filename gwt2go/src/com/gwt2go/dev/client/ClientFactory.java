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
package com.gwt2go.dev.client;


import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.gwt2go.dev.client.ui.CellTableSortingView;
import com.gwt2go.dev.client.ui.CellTableSortingView23;
import com.gwt2go.dev.client.ui.CellTableView;
import com.gwt2go.dev.client.ui.EditorView;
import com.gwt2go.dev.client.ui.GoodbyeView;
import com.gwt2go.dev.client.ui.HelloView;
import com.gwt2go.dev.client.ui.LeftSide;
import com.gwt2go.dev.client.ui.MainView;
import com.gwt2go.dev.client.ui.RightSide;
import com.gwt2go.dev.client.ui.view.CellTreeExpl1View;
import com.gwt2go.dev.client.ui.widget.dnd.DndView;

/**
 * Client Factory Interface
 * 
 * @author L.Pelov
 */
public interface ClientFactory {
	EventBus getEventBus();

	PlaceController getPlaceController();

	HelloView getHelloView();

	GoodbyeView getGoodbyeView();

	CellTableView getCellTableView();

	CellTableSortingView getCellTableSortableView();
	
	CellTableSortingView23 getCellTableSortingView23();

	MainView getRootView();

	LeftSide getLeftSide();

	RightSide getRightSide();
	
	EditorView getEditorView();
	
	DndView getDndView();
	
	CellTreeExpl1View getCellTreeViewExpl1();
}
