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
package com.gwt2go.dev.client.data;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;

public class CustomTreeModel implements TreeViewModel {

	private final List<CellTreeModelComposer> composers;

	/**
	 * This selection model is shared across all leaf nodes. A selection model
	 * can also be shared across all nodes in the tree, or each set of child
	 * nodes can have its own instance. This gives you flexibility to determine
	 * how nodes are selected.
	 */
	private final SingleSelectionModel<String> selectionModel = new SingleSelectionModel<String>();

	public CustomTreeModel() {
		composers = new ArrayList<CellTreeModelComposer>();

		// Add compositions
		{
			CellTreeModelComposer level1 = new CellTreeModelComposer("Level 1");
			composers.add(level1);

			CellTreeDAOModel sublevel_1_1 = level1
					.addPlaylist(new CellTreeDAOModel("Sub Level 1.1"));
			sublevel_1_1.addSubLevels("Level 1.1.1");
			sublevel_1_1.addSubLevels("Level 1.1.2");
			sublevel_1_1.addSubLevels("Level 1.1.3");
			sublevel_1_1.addSubLevels("Level 1.1.4");
			sublevel_1_1.addSubLevels("Level 1.1.5");

			CellTreeDAOModel sublevel_1_2 = level1
					.addPlaylist(new CellTreeDAOModel("Sub Level 1.2"));
			sublevel_1_2.addSubLevels("Level 1.2.1");
			sublevel_1_2.addSubLevels("Level 1.2.2");
			sublevel_1_2.addSubLevels("Level 1.2.3");

			CellTreeDAOModel sublevel_1_3 = level1
					.addPlaylist(new CellTreeDAOModel("Sub Level 1.3"));
			sublevel_1_3.addSubLevels("Level 1.3.4");
			sublevel_1_3.addSubLevels("Level 1.3.5");

			CellTreeDAOModel sublevel_1_4 = level1
					.addPlaylist(new CellTreeDAOModel("Sub Level 1.4"));
			sublevel_1_4.addSubLevels("Level 1.4.1");
			sublevel_1_4.addSubLevels("Level 1.4.2");
			sublevel_1_4.addSubLevels("Level 1.4.3");
			sublevel_1_4.addSubLevels("Level 1.4.4");
			sublevel_1_4.addSubLevels("Level 1.4.5");
			sublevel_1_4.addSubLevels("Level 1.4.6");
		}

		// Add compositions
		{
			CellTreeModelComposer level_2 = new CellTreeModelComposer("Level 2");
			composers.add(level_2);
			CellTreeDAOModel sublevel_2_1 = level_2
					.addPlaylist(new CellTreeDAOModel("Sub Level 2_1"));
			sublevel_2_1.addSubLevels("Level 2_1_1");
			sublevel_2_1.addSubLevels("Level 2_1_2");
			sublevel_2_1.addSubLevels("Level 2_1_3");
			sublevel_2_1.addSubLevels("Level 2_1_4");

			CellTreeDAOModel sublevel_2_2 = level_2
					.addPlaylist(new CellTreeDAOModel("Sub Level 2_2"));
			sublevel_2_2.addSubLevels("Level 2_2");
			sublevel_2_2.addSubLevels("Level 2_2");
			sublevel_2_2.addSubLevels("Level 2_2");
			sublevel_2_2.addSubLevels("Level 2_2");

			CellTreeDAOModel sublevel_2_3 = level_2
					.addPlaylist(new CellTreeDAOModel("Sub Level 2_3"));
			sublevel_2_3.addSubLevels("Level 2_3_1");
			sublevel_2_3.addSubLevels("Level 2_3_2");

			CellTreeDAOModel sublevel_2_4 = level_2
					.addPlaylist(new CellTreeDAOModel("Sub Level 2_4"));
			sublevel_2_4.addSubLevels("Level 2_4_1");
			sublevel_2_4.addSubLevels("Level 2_4_2");
			sublevel_2_4.addSubLevels("Level 2_4_3");
			sublevel_2_4.addSubLevels("Level 2_4_4r");
		}

		// Add compositions
		{
			CellTreeModelComposer sublevel_3 = new CellTreeModelComposer(
					"Level 3");
			composers.add(sublevel_3);
			CellTreeDAOModel sublevel_3_1 = sublevel_3
					.addPlaylist(new CellTreeDAOModel("Sub Level 3_1"));
			sublevel_3_1.addSubLevels("Sub Level 3_1_1");
			sublevel_3_1.addSubLevels("Sub Level 3_1_2");
			sublevel_3_1.addSubLevels("Sub Level 3_1_3");
			sublevel_3_1.addSubLevels("Sub Level 3_1_4");
			sublevel_3_1.addSubLevels("Sub Level 3_1_5");
		}
	}

	@Override
	public <T> NodeInfo<?> getNodeInfo(T value) {
		if (value == null) {
			// Create a data provider that contains the list of composers
			ListDataProvider<CellTreeModelComposer> dataProvider = new ListDataProvider<CellTreeModelComposer>(
					composers);

			// Create a cell to display
			Cell<CellTreeModelComposer> cell = new AbstractCell<CellTreeModelComposer>() {
				@Override
				public void render(Context context,
						CellTreeModelComposer value, SafeHtmlBuilder sb) {
					if (value != null) {
						sb.appendEscaped(value.getName());
					}
				}
			};

			// Return a node info that pairs the data provider and the cell
			return new DefaultNodeInfo<CellTreeModelComposer>(dataProvider,
					cell);

		} else if (value instanceof CellTreeModelComposer) {
			// get the first sub level
			ListDataProvider<CellTreeDAOModel> dataProvider = new ListDataProvider<CellTreeDAOModel>(
					((CellTreeModelComposer) value).getSubLists());
			Cell<CellTreeDAOModel> cell = new AbstractCell<CellTreeDAOModel>() {
				@Override
				public void render(Context context, CellTreeDAOModel value,
						SafeHtmlBuilder sb) {
					if (value != null) {
						sb.appendEscaped(value.getName());
					}
				}
			};

			return new DefaultNodeInfo<CellTreeDAOModel>(dataProvider, cell);

		} else if (value instanceof CellTreeDAOModel) {
			// get the third sub level
			ListDataProvider<String> dataProvider = new ListDataProvider<String>(
					((CellTreeDAOModel) value).getSubLevels());

			// Use the shared selection model
			return new DefaultNodeInfo<String>(dataProvider, new TextCell(),
					selectionModel, null);
		}

		return null;
	}

	@Override
	public boolean isLeaf(Object value) {

		if (value instanceof String) {
			return true;
		}
		return false;

	}

}
