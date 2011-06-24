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
import com.gwt2go.dev.client.activity.LeftSideActivity;
import com.gwt2go.dev.client.ClientFactory;

/**
 * Activity mapper handles the clicks on of the left side panel.
 * 
 * @author L.Pelov
 * 
 */
public class RootActivityMapper implements ActivityMapper {

	private ClientFactory clientFactory;

	public RootActivityMapper(ClientFactory clientFactory) {
		super();
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		// The activity you will get here, is the one from the RootView!!!

		return new LeftSideActivity(clientFactory);

	}

}
