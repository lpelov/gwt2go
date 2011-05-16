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

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import com.gwt2go.dev.client.place.CellTablePlace;
import com.gwt2go.dev.client.place.CellTableSortingPlace;
import com.gwt2go.dev.client.place.GoodbyePlace;
import com.gwt2go.dev.client.place.HelloPlace;
import com.gwt2go.dev.client.place.LeftSidePlace;
import com.gwt2go.dev.client.place.RightSidePlace;
import com.gwt2go.dev.client.place.RootPlace;

@WithTokenizers({ CellTablePlace.Tokenizer.class, HelloPlace.Tokenizer.class,
		GoodbyePlace.Tokenizer.class, CellTableSortingPlace.Tokenizer.class,
		RootPlace.Tokenizer.class, RightSidePlace.Tokenizer.class, LeftSidePlace.Tokenizer.class })
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {

	/*
	 * At GWT compile time, GWT generates (see PlaceHistoryMapperGenerator) a
	 * class based on your interface that extends AbstractPlaceHistoryMapper.
	 * PlaceHistoryMapper is the link between your PlaceTokenizers and GWT's
	 * PlaceHistoryHandler that synchronizes the browser URL with each Place.
	 * 
	 * For more control of the PlaceHistoryMapper, you can use the @Prefix
	 * annotation on a PlaceTokenizer to change the first part of the URL
	 * associated with the Place. For even more control, you can instead
	 * implement PlaceHistoryMapperWithFactory and provide a TokenizerFactory
	 * that, in turn, provides individual PlaceTokenizers.
	 */
}
