/*
 * Copyright 2010, L.Pelov
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

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.cellview.client.TreeNode;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;
import com.gwt2go.dev.client.ClientFactory;
import com.gwt2go.dev.client.place.RootPlace;
import com.gwt2go.dev.client.ui.widget.celltree.CellTreeRooterModel;
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

	@UiField(provided = true)
	CellTree cellTree3;

	ClientFactory clientFactory;

	private final SingleSelectionModel<GotoPlacesModel> selectionModelPlaces = new SingleSelectionModel<GotoPlacesModel>();

	public LeftSide(final ClientFactory clientFactory) {

		TreeViewModel treeRooterModel = new CellTreeRooterModel(
				selectionModelPlaces);

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

		cellTree3 = new CellTree(treeRooterModel, null);
		cellTree3.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		//
		TreeNode rootNode = cellTree3.getRootTreeNode();
		TreeNode firstPlaylist = rootNode.setChildOpen(0, true);
		firstPlaylist.setChildOpen(0, true);
		
		initWidget(uiBinder.createAndBindUi(this));

		this.clientFactory = clientFactory;
	}

//	@UiHandler("button1")
//	void onButton1Click(ClickEvent e) {
//		this.clientFactory.getPlaceController().goTo(new RootPlace("table"));
//	}
}
