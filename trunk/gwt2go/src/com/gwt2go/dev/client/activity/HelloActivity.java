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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.gwt2go.dev.client.place.GoodbyePlace;
import com.gwt2go.dev.client.place.HelloPlace;
import com.gwt2go.dev.client.ui.HelloView;
import com.gwt2go.dev.client.ClientFactory;

public class HelloActivity extends AbstractActivity implements
		HelloView.Presenter {

	// Used to obtain views, eventBus, placeController
	// Alternatively, could be injected via GIN
	private ClientFactory clientFactory;

	// Name that will be appended to "Hello,"
	private String name;

	public HelloActivity(HelloPlace place, ClientFactory clientFactory) {
		this.name = place.getHelloName();
		this.clientFactory = clientFactory;
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		HelloView helloView = clientFactory.getHelloView();
		Window.alert(clientFactory.getCellTableView().getTextBox().getText());
		helloView.setName(name);
		helloView.setPresenter(this);
		containerWidget.setWidget(helloView.asWidget());
	}

	@Override
	public String mayStop() {
		// return "Please hold on. This activity is stopping.";
		return super.mayStop();
	}

	@Override
	public void goTo(Place place) {
		this.clientFactory.getPlaceController().goTo(place);
	}

	@Override
	public void onLinkClicked() {
		this.clientFactory.getPlaceController().goTo(new GoodbyePlace(name));
	}

}
