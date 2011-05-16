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
import com.gwt2go.dev.client.place.CellTableSortingPlace;
import com.gwt2go.dev.client.ui.CellTableSortingView;
import com.gwt2go.dev.client.ClientFactory;

/**
 * Cell sortable table sorting activity
 * 
 * @author L.Pelov
 */
public class CellTableSortingActivity extends AbstractActivity implements
		CellTableSortingView.Presenter {

	// Used to obtain views, eventBus, placeController
	// Alternatively, could be injected via GIN
	private ClientFactory clientFactory;
	//private String name;

	public CellTableSortingActivity(CellTableSortingPlace place,
			ClientFactory clientFactory) {
		//this.name = place.getCellTableName();
		this.clientFactory = clientFactory;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {

		CellTableSortingView cellTableSortingView = this.clientFactory
				.getCellTableSortableView();

		
		//cellTableSortingView.setName(name);
		cellTableSortingView.setName(this.clientFactory.getCellTableView().getTextBox().getText());
		
		cellTableSortingView.setPresenter(this);
		panel.setWidget(cellTableSortingView);

	}

	@Override
	public void goTo(Place place) {		
	}

}
