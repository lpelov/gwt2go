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
package com.gwt2go.dev.client.ui.table;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

public class DataGridImpl1 extends ResizeComposite implements DataGridView {

	LayoutPanel viewPanel = new LayoutPanel();
	Element nameSpan = DOM.createSpan();
	Presenter presenter;
	SimplePager pager;

	// A simple data type that represents a contact.
	private static class Contact {
		private final String address;
		private final String name;
		private final Date birthday;

		public Contact(String name, Date birthday, String address, String color) {
			this.name = name;
			this.address = address;
			this.birthday = birthday;
		}
	}

	// TODO: This is not going to work like this, see CellTableSortingViewImpl
	// TODO: check to see the layouts, should use LayoutPanel, which means you
	// have to use RootLayoutPanel
	
	// The list of data to display.
	@SuppressWarnings("deprecation")
	private static List<Contact> CONTACTS = Arrays
			.asList(new Contact("John", new Date(80, 4, 12), "123 Fourth Road",
					"red;"), new Contact("Mary", new Date(85, 2, 22),
					"222 Lancer Lane", "green;"), new Contact("Zander",
					new Date(46, 6, 6), "94 Road Street", "blue;"));

	public DataGridImpl1() {

		// Create a CellTable.
		DataGrid<Contact> table = new DataGrid<Contact>();
		table.setWidth("100%");
		table.setHeight("100px");
		
//		table.setRowStyles(new RowStyles<DataGridImpl1.Contact>() {			
//			@Override
//			public String getStyleNames(Contact row, int rowIndex) {
//				return "headcol";
//			}
//		});
		
		table.setEmptyTableWidget(new Label("No Information to show"));
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		// Add a text column to show the name.
		TextColumn<Contact> nameColumn = new TextColumn<Contact>() {
			@Override
			public String getValue(Contact object) {
				return object.name;
			}
		};
		table.addColumn(nameColumn, "Name");

		// Add a date column to show the birthday.
		DateCell dateCell = new DateCell();
		Column<Contact, Date> dateColumn = new Column<Contact, Date>(dateCell) {
			@Override
			public Date getValue(Contact object) {
				return object.birthday;
			}
		};

		table.addColumn(dateColumn, "Birthday");

		// Add a text column to show the address.
		TextColumn<Contact> addressColumn = new TextColumn<Contact>() {
			@Override
			public String getValue(Contact object) {
				return object.address;
			}
		};
		table.addColumn(addressColumn, "Address");

		// Add a selection model to handle user selection.
		final SingleSelectionModel<Contact> selectionModel = new SingleSelectionModel<Contact>();
		table.setSelectionModel(selectionModel);
		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						Contact selected = selectionModel.getSelectedObject();
						if (selected != null) {
							Window.alert("You selected: " + selected.name);
						}
					}
				});

		// Set the total row count. This isn't strictly necessary, but it
		// affects
		// paging calculations, so its good habit to keep the row count up to
		// date.
		table.setRowCount(CONTACTS.size(), true);

		// Push the data into the widget.
		table.setRowData(0, CONTACTS);
		

		// -- END TABLE

		// viewPanel.getElement().appendChild(nameSpan);

		viewPanel.add(table);
		viewPanel.setSize("30em", "10em");
		
		initWidget(viewPanel);

	}

	@Override
	public void setName(String tableName) {
		nameSpan.setInnerText("Table name: " + tableName);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;

	}

}
