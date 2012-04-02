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
package com.gwt2go.dev.client.ui.widget.celltree;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;

/**
 * TreeVievModel defines how to render the cell tree navigation.
 * 
 * @author L.Pelov
 */
public class CellTreeRooterModel implements TreeViewModel {

	private final List<CellTreeRooterDaoComposer> composers;
	private final SingleSelectionModel<GotoPlacesModel> selectionModel;

	public CellTreeRooterModel(
			SingleSelectionModel<GotoPlacesModel> selectionModel) {

		composers = new ArrayList<CellTreeRooterDaoComposer>();
		this.selectionModel = selectionModel;

		{ // First section

			CellTreeRooterDaoComposer level1 = new CellTreeRooterDaoComposer(
					"Widgets");
			composers.add(level1);

			CellTreeRooterDaoModel sublevel_1_1 = level1
					.addNode(new CellTreeRooterDaoModel("Tables"));

			sublevel_1_1.addNode("Sorting table - example 1", "table");
			sublevel_1_1.addNode("Sorting table - example 1", "table2");
			sublevel_1_1.addNode("Sorting table - GWT2.3", "sortingtable23");

			CellTreeRooterDaoModel sublevel_1_2 = level1
					.addNode(new CellTreeRooterDaoModel("Editor"));

			sublevel_1_2.addNode("Editor - WYSIWYG", "editor");

		}

	}

	@Override
	public <T> NodeInfo<?> getNodeInfo(T value) {
		if (value == null) {
			// Create a data provider that contains the list of composers
			ListDataProvider<CellTreeRooterDaoComposer> dataProvider = new ListDataProvider<CellTreeRooterDaoComposer>(
					composers);

			// Create a cell to display
			Cell<CellTreeRooterDaoComposer> cell = new AbstractCell<CellTreeRooterDaoComposer>() {
				@Override
				public void render(Context context,
						CellTreeRooterDaoComposer value, SafeHtmlBuilder sb) {
					if (value != null) {
						sb.appendEscaped(value.getName());
					}
				}
			};

			// Return a node info that pairs the data provider and the cell
			return new DefaultNodeInfo<CellTreeRooterDaoComposer>(dataProvider,
					cell);

		} else if (value instanceof CellTreeRooterDaoComposer) {

			// get the first sub level
			ListDataProvider<CellTreeRooterDaoModel> dataProvider = new ListDataProvider<CellTreeRooterDaoModel>(
					((CellTreeRooterDaoComposer) value).getSubNodes());

			Cell<CellTreeRooterDaoModel> cell = new AbstractCell<CellTreeRooterDaoModel>() {
				@Override
				public void render(Context context,
						CellTreeRooterDaoModel value, SafeHtmlBuilder sb) {
					if (value != null) {
						sb.appendEscaped(value.getName());
					}
				}
			};

			return new DefaultNodeInfo<CellTreeRooterDaoModel>(dataProvider,
					cell);

		} else if (value instanceof CellTreeRooterDaoModel) {

			// get the third sub level
			ListDataProvider<GotoPlacesModel> dataProvider = new ListDataProvider<GotoPlacesModel>(
					((CellTreeRooterDaoModel) value).getNodes());

			// Use the shared selection model
			return new DefaultNodeInfo<GotoPlacesModel>(dataProvider,
					new CellTreeRooterCell(), selectionModel, null);

		}

		return null;

	}

	@Override
	public boolean isLeaf(Object value) {
		// you have to put this here otherwise you will get icon on the front of the latest
		// three element and looks like you have also under-levels, which is not nice
		if (value instanceof GotoPlacesModel) {
			return true;
		}
		return false;

	}

}
