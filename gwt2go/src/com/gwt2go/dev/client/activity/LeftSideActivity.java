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
import com.gwt2go.dev.client.place.LeftSidePlace;
import com.gwt2go.dev.client.ui.LeftSide;
import com.gwt2go.dev.client.ui.MainView;
import com.gwt2go.dev.client.ClientFactory;

/**
 * Activity represents the root.
 * 
 * @author L.Pelov
 */
public class LeftSideActivity extends AbstractActivity implements
		MainView.Presenter {

	// Used to obtain views, eventBus, placeController
	// Alternatively, could be injected via GIN
	private ClientFactory clientFactory;

	public LeftSideActivity(LeftSidePlace place, ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	public LeftSideActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
//		MainView rootView = this.clientFactory.getRootView();		
//		rootView.setPresenter(this);
//		panel.setWidget(rootView);
		
		LeftSide leftSide = this.clientFactory.getLeftSide();
		panel.setWidget(leftSide);
	}

	@Override
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}

}
