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
package com.gwt2go.dev.client.activity;


import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.gwt2go.dev.client.ClientFactory;
import com.gwt2go.dev.client.place.CellTableSortingPlace;
import com.gwt2go.dev.client.ui.CellTableSortingView23;

/**
 * Cell table sorting GWT 2.3 way.
 * 
 * @author L.Pelov
 */
public class CellTableSorting23Activity extends AbstractActivity implements
		CellTableSortingView23.Presenter {

	// Used to obtain views, eventBus, placeController
	// Alternatively, could be injected via GIN
	private ClientFactory clientFactory;

	public CellTableSorting23Activity(CellTableSortingPlace place,
			ClientFactory clientFactory) {
		//this.name = place.getCellTableName();
		this.clientFactory = clientFactory;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {

		CellTableSortingView23 cellTableSortingView23 = this.clientFactory
				.getCellTableSortingView23();

		
		//cellTableSortingView.setName(name);
		cellTableSortingView23.setName("Cell table sorting GWT2.3 way");
		
		cellTableSortingView23.setPresenter(this);
		panel.setWidget(cellTableSortingView23);

	}

	@Override
	public void goTo(Place place) {		
	}

}
