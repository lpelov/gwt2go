/*
 * Copyright 2012, L.Pelov
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
package com.gwt2go.dev.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class CellTreeExpl1Place extends Place {
	
	private String editorName;

	public CellTreeExpl1Place(String token) {
		this.editorName = token;
	}

	public String getEditorName() {
		return editorName;
	}

	public static class Tokenizer implements PlaceTokenizer<CellTreeExpl1Place> {
		@Override
		public String getToken(CellTreeExpl1Place place) {
			return place.getEditorName();
		}

		@Override
		public CellTreeExpl1Place getPlace(String token) {
			return new CellTreeExpl1Place(token);
		}
	}
	

}
