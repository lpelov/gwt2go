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
package com.gwt2go.dev.client.mapper;


import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.gwt2go.dev.client.activity.CellTableActivity;
import com.gwt2go.dev.client.activity.CellTableSortingActivity;
import com.gwt2go.dev.client.activity.GoodbyeActivity;
import com.gwt2go.dev.client.activity.HelloActivity;
import com.gwt2go.dev.client.activity.RootActivity;
import com.gwt2go.dev.client.place.CellTablePlace;
import com.gwt2go.dev.client.place.CellTableSortingPlace;
import com.gwt2go.dev.client.place.GoodbyePlace;
import com.gwt2go.dev.client.place.HelloPlace;
import com.gwt2go.dev.client.place.RootPlace;
import com.gwt2go.dev.client.ClientFactory;

public class AppActivityMapper implements ActivityMapper {

	private ClientFactory clientFactory;

	public AppActivityMapper(ClientFactory clientFactory) {
		super();
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof HelloPlace)
			return new HelloActivity((HelloPlace) place, clientFactory);
		else if (place instanceof GoodbyePlace)
			return new GoodbyeActivity((GoodbyePlace) place, clientFactory);
		else if (place instanceof CellTablePlace)
			return new CellTableActivity((CellTablePlace) place, clientFactory);
		else if (place instanceof CellTableSortingPlace)
			return new CellTableSortingActivity((CellTableSortingPlace) place,
					clientFactory);
		else if (place instanceof RootPlace)
			return new RootActivity((RootPlace) place,
					clientFactory);

		return null;

	}

}
