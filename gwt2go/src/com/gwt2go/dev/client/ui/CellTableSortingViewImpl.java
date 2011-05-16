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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.SelectionCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;
import com.gwt2go.dev.client.data.ContactDatabase;
import com.gwt2go.dev.client.data.ContactDatabase.Category;
import com.gwt2go.dev.client.data.ContactDatabase.ContactInfo;
import com.gwt2go.dev.client.ui.CellTableViewImpl.GetValue;
import com.gwt2go.dev.client.ui.widget.CellTableSorting;
import com.gwt2go.dev.client.ui.widget.SortHeader;

/**
 * Cell table sorting view implementation
 * 
 * @author L.Pelov
 */
public class CellTableSortingViewImpl extends Composite implements
		CellTableSortingView {

	SimplePanel viewPanel = new SimplePanel();
	Element nameSpan = DOM.createSpan();
	Presenter presenter;
	SimplePager pager;
	Anchor noClickAnchor = new Anchor("Click Me", "#");
	CellTableSorting<ContactDatabase.ContactInfo> cellTable = new CellTableSorting<ContactDatabase.ContactInfo>(
			ContactDatabase.ContactInfo.KEY_PROVIDER);
	
	final SingleSelectionModel<ContactInfo> selectionModel = new SingleSelectionModel<ContactInfo>(
			ContactInfo.KEY_PROVIDER);

	public CellTableSortingViewImpl() {

		viewPanel.getElement().appendChild(nameSpan);


		// Create a Pager to control the table.
		SimplePager.Resources pagerResources = GWT
				.create(SimplePager.Resources.class);
		pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0,
				true);
		pager.setDisplay(cellTable);

		// Add a selection model so we can select cells.
		cellTable.setSelectionModel(selectionModel);

		// Initialize the columns.
		initTableColumns(selectionModel, cellTable);

		// Add the CellList to the adapter in the database.
		// ContactDatabase.get().addDataDisplay(cellTable);
		List<ContactInfo> data = ContactDatabase.get().getDataProvider()
				.getList();
		cellTable.setData(data);

		viewPanel.add(cellTable);
		viewPanel.getElement().appendChild(noClickAnchor.getElement());
		
		initWidget(viewPanel);
		
		noClickAnchor.unsinkEvents(Event.MOUSEEVENTS);
		//noClickAnchor.setHref("javascript:function(){return false;}");
		
		
		
	}

	@Override
	public void setName(String tableName) {
		nameSpan.setInnerText("Table name: " + tableName);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;

	}

	private void initTableColumns(
			final SelectionModel<ContactInfo> selectionModel,
			CellTableSorting<ContactInfo> cellTable) {

		// This table will uses a checkbox column for selection.
		// Alternatively, you can call cellTable.setSelectionEnabled(true) to
		// enable mouse selection.
		Column<ContactInfo, Boolean> checkColumn = new Column<ContactInfo, Boolean>(
				new CheckboxCell(true, true)) {
			@Override
			public Boolean getValue(ContactInfo object) {
				// Get the value from the selection model.
				return selectionModel.isSelected(object);
			}
		};
		checkColumn.setFieldUpdater(new FieldUpdater<ContactInfo, Boolean>() {
			public void update(int index, ContactInfo object, Boolean value) {
				// Called when the user clicks on a checkbox.
				selectionModel.setSelected(object, value);
			}
		});
		cellTable
				.addColumn(checkColumn, SafeHtmlUtils.fromSafeConstant("<br>"));

		cellTable.addColumn("First name", new TextCell(),
				new GetValue<ContactInfo, String>() {
					public String getValue(ContactInfo object) {
						return object.getFirstName();
					}
				}, new SortHeader("First name"));

		// Last name.
		Column<ContactInfo, String> lastNameColumn = new Column<ContactInfo, String>(
				new EditTextCell()) {
			@Override
			public String getValue(ContactInfo object) {
				return object.getLastName();
			}
		};

		cellTable.addColumn(lastNameColumn, "Last name");
		lastNameColumn.setFieldUpdater(new FieldUpdater<ContactInfo, String>() {
			public void update(int index, ContactInfo object, String value) {
				// Called when the user changes the value.
				object.setLastName(value);
				ContactDatabase.get().refreshDisplays();
			}
		});

		// Category.
		final Category[] categories = ContactDatabase.get().queryCategories();
		List<String> categoryNames = new ArrayList<String>();
		for (Category category : categories) {
			categoryNames.add(category.getDisplayName());
		}
		SelectionCell categoryCell = new SelectionCell(categoryNames);
		Column<ContactInfo, String> categoryColumn = new Column<ContactInfo, String>(
				categoryCell) {
			@Override
			public String getValue(ContactInfo object) {
				return object.getCategory().getDisplayName();
			}
		};
		cellTable.addColumn(categoryColumn, "Category");
		categoryColumn.setFieldUpdater(new FieldUpdater<ContactInfo, String>() {
			public void update(int index, ContactInfo object, String value) {
				for (Category category : categories) {
					if (category.getDisplayName().equals(value)) {
						object.setCategory(category);
					}
				}
				ContactDatabase.get().refreshDisplays();
			}
		});

		// Address.
		cellTable.addColumn("Address", new TextCell(),
				new GetValue<ContactInfo, String>() {
					public String getValue(ContactInfo object) {
						return object.getAddress();
					}
				}, new SortHeader("Address"));

		cellTable.addColumn("Birthday", new DateCell(),
				new GetValue<ContactInfo, Date>() {
					public Date getValue(ContactInfo object) {
						return object.getBirthday();
					}
				}, new SortHeader("Birthday"));
	}

}
