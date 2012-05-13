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
package com.gwt2go.dev.client.ui.view;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TreeNode;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;
import com.gwt2go.dev.client.ClientFactory;
import com.gwt2go.dev.client.ui.widget.celltree.CustomTreeModel;

/**
 * Cell Tree example 1 view
 * 
 * @author L.Pelov
 */
public class CellTreeExpl1ViewImpl extends Composite implements
		CellTreeExpl1View {

	private static CellTreeExpl1ViewImplUiBinder uiBinder = GWT
			.create(CellTreeExpl1ViewImplUiBinder.class);

	interface CellTreeExpl1ViewImplUiBinder extends
			UiBinder<Widget, CellTreeExpl1ViewImpl> {
	}

	Presenter presenter;

	@UiField
	HTMLPanel htmlPanel;
			
	@UiField(provided = true)
	CellTree cellTree1 = new CellTree(new TreeViewModel() {
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

	private final SingleSelectionModel<String> selectionModel = new SingleSelectionModel<String>();
	
	public CellTreeExpl1ViewImpl(final ClientFactory clientFactory) {

		TreeViewModel model = new CustomTreeModel(selectionModel);

		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						String selected = selectionModel.getSelectedObject();
						if (selected != null) {
							Window.alert("You selected: " + selected);
						}
					}
				});

		// add the new tree
		cellTree2 = new CellTree(model, null);
		cellTree2.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		//
		TreeNode rootNode = cellTree2.getRootTreeNode();
		TreeNode firstPlaylist = rootNode.setChildOpen(0, true);
		firstPlaylist.setChildOpen(0, true);
				
		initWidget(uiBinder.createAndBindUi(this));			
	}

	@Override
	public void setName(String tableName) {
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
}
