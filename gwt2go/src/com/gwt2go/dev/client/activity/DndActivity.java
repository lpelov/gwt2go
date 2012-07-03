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
package com.gwt2go.dev.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.gwt2go.dev.client.ClientFactory;
import com.gwt2go.dev.client.place.Dnd2Place;
import com.gwt2go.dev.client.ui.widget.dnd.DndView;

/**
 * 
 * @author L.Pelov
 */
public class DndActivity extends AbstractActivity implements DndView.Presenter {

	private ClientFactory clientFactory;
	private Place place;

	public DndActivity(Place place, ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		this.place = place;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {

		DndView dndView;

		if (place instanceof Dnd2Place) {
			dndView = this.clientFactory.getDndView();
		} else {
			dndView = this.clientFactory.getDndView();
		}

		dndView.setPresenter(this);
		panel.setWidget(dndView);

	}

	@Override
	public void goTo(Place place) {
	}

}
