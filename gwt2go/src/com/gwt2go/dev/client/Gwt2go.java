/*
 * Copyright 2010 - Lyudmil Pelov
 * Gwt2Go - Google Web Toolkit Examples
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

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.gwt2go.dev.client.mapper.AppActivityMapper;
import com.gwt2go.dev.client.mapper.AppPlaceHistoryMapper;
import com.gwt2go.dev.client.place.RootPlace;
import com.gwt2go.dev.client.ui.widget.SimpleWidgetPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 * 
 * @author L.Pelov
 */
public class Gwt2go implements EntryPoint {
	// private Place defaultPlace = new CellTablePlace("Sortable celltable view");
	private Place defaultPlace = new RootPlace("rootview");
	
	private SimpleWidgetPanel appWidget = new SimpleWidgetPanel();

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		// SimpleGinjector ginjector = GWT.create(SimpleGinjector.class);

		ClientFactory clientFactory = GWT.create(ClientFactory.class);
		EventBus eventBus = clientFactory.getEventBus();
		PlaceController placeController = clientFactory.getPlaceController();

		// Start ActivityManager for the main widget with our ActivityMapper
		ActivityMapper activityMapper = new AppActivityMapper(clientFactory);
		ActivityManager activityManager = new ActivityManager(activityMapper,
				eventBus);
		activityManager.setDisplay(appWidget);

		// Start PlaceHistoryHandler with our PlaceHistoryMapper
		AppPlaceHistoryMapper historyMapper = GWT
				.create(AppPlaceHistoryMapper.class);
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(
				historyMapper);
		historyHandler.register(placeController, eventBus, defaultPlace);

		RootLayoutPanel.get().add(appWidget);
		// RootPanel.get().add(appWidget);

		// Goes to the place represented on URL else default place
		historyHandler.handleCurrentHistory();

	}
}
