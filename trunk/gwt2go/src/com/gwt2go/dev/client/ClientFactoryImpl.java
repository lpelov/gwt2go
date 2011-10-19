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
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.gwt2go.dev.client.ui.CellTableSortingView;
import com.gwt2go.dev.client.ui.CellTableSortingView23;
import com.gwt2go.dev.client.ui.CellTableSortingView23Impl;
import com.gwt2go.dev.client.ui.CellTableSortingViewImpl;
import com.gwt2go.dev.client.ui.CellTableView;
import com.gwt2go.dev.client.ui.CellTableViewImpl;
import com.gwt2go.dev.client.ui.EditorView;
import com.gwt2go.dev.client.ui.EditorViewImpl;
import com.gwt2go.dev.client.ui.GoodbyeView;
import com.gwt2go.dev.client.ui.GoodbyeViewImpl;
import com.gwt2go.dev.client.ui.HelloView;
import com.gwt2go.dev.client.ui.HelloViewImpl;
import com.gwt2go.dev.client.ui.LeftSide;
import com.gwt2go.dev.client.ui.MainView;
import com.gwt2go.dev.client.ui.RightSide;
import com.gwt2go.dev.client.ui.RootView;

public class ClientFactoryImpl implements ClientFactory {	
	private final EventBus eventBus = new SimpleEventBus();	
	private final PlaceController placeController = new PlaceController(
			eventBus);	
	private final HelloView helloView = new HelloViewImpl();
	private final GoodbyeView goodbyeView = new GoodbyeViewImpl();
	private final CellTableView cellTableView = new CellTableViewImpl();
	private final CellTableSortingView cellTableSortingView = new CellTableSortingViewImpl();
	private final CellTableSortingView23 celTableSortingView23 = new CellTableSortingView23Impl();
	private final MainView rootView = new RootView(this);
	private final LeftSide leftSide = new LeftSide(this);
	private final RightSide rightSide = new RightSide();
	private final EditorView editorView = new EditorViewImpl();

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public HelloView getHelloView() {
		return helloView;
	}

	@Override
	public GoodbyeView getGoodbyeView() {
		return goodbyeView;
	}

	@Override
	public CellTableView getCellTableView() {
		return cellTableView;
	}

	@Override
	public CellTableSortingView getCellTableSortableView() {
		return cellTableSortingView;
	}

	@Override
	public MainView getRootView() {
		return rootView;
	}

	@Override
	public LeftSide getLeftSide() {
		return leftSide;
	}

	@Override
	public RightSide getRightSide() {
		return rightSide;
	}

	@Override
	public CellTableSortingView23 getCellTableSortingView23() {
		return celTableSortingView23;
	}

	@Override
	public EditorView getEditorView() {
		return editorView;
	}

}
