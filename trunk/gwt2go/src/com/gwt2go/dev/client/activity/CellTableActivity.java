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
import com.gwt2go.dev.client.place.CellTablePlace;
import com.gwt2go.dev.client.place.CellTableSortingPlace;
import com.gwt2go.dev.client.ui.CellTableView;
import com.gwt2go.dev.client.ClientFactory;

public class CellTableActivity extends AbstractActivity implements
		CellTableView.Presenter {

	// Used to obtain views, eventBus, placeController
	// Alternatively, could be injected via GIN
	private ClientFactory clientFactory;

	// Name that will be appended to "Hello,"
	private String name;

	public CellTableActivity(CellTablePlace place, ClientFactory clientFactory) {
		this.name = place.getCellTableName();
		this.clientFactory = clientFactory;

		// Window.alert("You can Do RPC Call Here");
	}

	public CellTableActivity(String place, ClientFactory clientFactory) {
		this.name = place;
		this.clientFactory = clientFactory;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		// you can use here every view you want
		CellTableView cellTableView = clientFactory.getCellTableView();
		cellTableView.setName(name);
		cellTableView.setPresenter(this);
		panel.setWidget(cellTableView);		
	}

	@Override
	public void goTo(Place place) {		
		clientFactory.getPlaceController().goTo(place);
	}

	@Override
	public void onButtonClicked() {
		// Window.alert("This is now the activity here");
		// clientFactory.getPlaceController().goTo(new
		// HelloPlace("after table"));
		clientFactory.getPlaceController().goTo(
				new CellTableSortingPlace("sortingtable"));
	}

	@Override
	public void onDialogBoxBtnClicked() {
	}

}
