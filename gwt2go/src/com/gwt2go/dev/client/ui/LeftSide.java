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
package com.gwt2go.dev.client.ui;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TreeNode;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;
import com.gwt2go.dev.client.ClientFactory;
import com.gwt2go.dev.client.place.RootPlace;
import com.gwt2go.dev.client.ui.widget.celltree.CellTreeRooterModel;
import com.gwt2go.dev.client.ui.widget.celltree.CustomTreeModel;
import com.gwt2go.dev.client.ui.widget.celltree.GotoPlacesModel;

/**
 * Left side panel
 * 
 * @author L.Pelov
 */
public class LeftSide extends Composite {

	private static LeftSideUiBinder uiBinder = GWT
			.create(LeftSideUiBinder.class);

	interface LeftSideUiBinder extends UiBinder<Widget, LeftSide> {
	}

	// use this to build new menu structure
//	@UiField(provided = true)
//	CellTree cellTreeMenu;

//	@UiField
//	Button button1;
//
//	@UiField
//	Button button2;
//
//	@UiField
//	Button button3;
//
//	@UiField
//	Button btnEditor;

	@UiField(provided = true)
	CellTree cellTree = new CellTree(new TreeViewModel() {
		// final AbstractDataProvider<String> dataProvider = new
		// ListDataProvider<String>();
		// final AbstractSelectionModel<String> selectionModel = new
		// NoSelectionModel<String>();
		@Override
		public <T> NodeInfo<?> getNodeInfo(T value) {
			/*
			 * Create some data in a data provider. Use the parent value as a
			 * prefix for the next level.
			 */
			ListDataProvider<String> dataProvider = new ListDataProvider<String>();
			for (int i = 0; i < 2; i++) {
				dataProvider.getList().add(value + "." + String.valueOf(i));
			}

			// Return a node info that pairs the data with a cell.
			return new DefaultNodeInfo<String>(dataProvider, new TextCell());
			// return new DefaultNodeInfo<String>(dataProvider, new TextCell(),
			// selectionModel, null);
		}

		@Override
		public boolean isLeaf(Object value) {
			return value.toString().length() > 10;
			// return true;
		}
	}, "Item 1");

	@UiField(provided = true)
	CellTree cellTree2;

	@UiField(provided = true)
	CellTree cellTree3;

	ClientFactory clientFactory;

	private final SingleSelectionModel<String> selectionModel = new SingleSelectionModel<String>();

	private final SingleSelectionModel<GotoPlacesModel> selectionModelPlaces = new SingleSelectionModel<GotoPlacesModel>();

	public LeftSide(final ClientFactory clientFactory) {

		TreeViewModel model = new CustomTreeModel(selectionModel);
		TreeViewModel treeRooterModel = new CellTreeRooterModel(
				selectionModelPlaces);

		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						String selected = selectionModel.getSelectedObject();
						if (selected != null) {
							Window.alert("You selected: " + selected);
						}
					}
				});

		selectionModelPlaces
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {

					@Override
					public void onSelectionChange(SelectionChangeEvent event) {
						GotoPlacesModel selected = selectionModelPlaces
								.getSelectedObject();
						if (selected != null) {
							// Window.alert("You selected to go: "
							// + selected.getGotoPlace());
							clientFactory.getPlaceController().goTo(new RootPlace(selected.getGotoPlace()));
						}
					}
				});
		
		// TODO: add selection with Enter keyboard

		// add the new tree
		cellTree2 = new CellTree(model, null);
		cellTree2.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		//
		TreeNode rootNode = cellTree2.getRootTreeNode();
		TreeNode firstPlaylist = rootNode.setChildOpen(0, true);
		firstPlaylist.setChildOpen(0, true);

		cellTree3 = new CellTree(treeRooterModel, null);
		cellTree3.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		initWidget(uiBinder.createAndBindUi(this));
//		button1.setText("SortingTable1");
//		button2.setText("SortingTable2");
//		button3.setText("SortingTable_GWT2.3");
//		btnEditor.setText("EditorWS");

		this.clientFactory = clientFactory;
		this.cellTree.setAnimationEnabled(true);
	}

//	@UiHandler("button1")
//	void onButton1Click(ClickEvent e) {
//		this.clientFactory.getPlaceController().goTo(new RootPlace("table"));
//	}
//
//	@UiHandler("button2")
//	void onButton2Click(ClickEvent e) {
//		this.clientFactory.getPlaceController().goTo(new RootPlace("table2"));
//	}
//
//	@UiHandler("button3")
//	void onButton3Click(ClickEvent e) {
//		this.clientFactory.getPlaceController().goTo(
//				new RootPlace("sortingtable23"));
//	}
//
//	@UiHandler("btnEditor")
//	void onbtnEditorClick(ClickEvent e) {
//		this.clientFactory.getPlaceController().goTo(new RootPlace("editor"));
//	}

}
