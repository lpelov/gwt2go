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

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.view.client.ListDataProvider;
import com.gwt2go.dev.client.ui.table.ImagesColumn;

/**
 * Cell table sorting view implementation for GWT2.3
 * 
 * @author L.Pelov
 */
public class CellTableSortingView23Impl extends Composite implements
		CellTableSortingView23 {

	SimplePanel viewPanel = new SimplePanel();
	Element nameSpan = DOM.createSpan();
	Presenter presenter;
	SimplePager pager;
	
	// A simple data type that represents a contact.
	private static class Contact {
		private final String address;
		private final String name;
		private final String color;

		public Contact(String name, String address, String color) {
			this.name = name;
			this.address = address;
			this.color = color;
		}
	}

	// The list of data to display.
	private static List<Contact> CONTACTS = Arrays.asList(new Contact("John",
			"123 Fourth Road", "red;"), new Contact("Mary", "222 Lancer Lane", "green;"),
			new Contact("Zander", "94 Road Street", "blue;"));

	public CellTableSortingView23Impl() {
		// -- START TABLE
	    // Create a CellTable.
	    CellTable<Contact> table = new CellTable<Contact>();

	    // Create name column.
	    TextColumn<Contact> nameColumn = new TextColumn<Contact>() {
	      @Override
	      public String getValue(Contact contact) {
	        return contact.name;
	      }
	    };

	    // Make the name column sortable.
	    nameColumn.setSortable(true);

	    // Create address column.
	    TextColumn<Contact> addressColumn = new TextColumn<Contact>() {
	      @Override
	      public String getValue(Contact contact) {
	        return contact.address;
	      }
	    };

	    ImagesColumn<Contact> imagesColumn = new ImagesColumn<Contact>(){
	    	@Override
	    	public String getValue(Contact object) {	    		
	    		return object.color;
	    	}
	    };
	    
	    
	    // Add the columns.
	    table.addColumn(nameColumn, "Name");
	    table.addColumn(addressColumn, "Address");
	    table.addColumn(imagesColumn, "color");
	    

	    // Create a data provider.
	    ListDataProvider<Contact> dataProvider = new ListDataProvider<Contact>();

	    // Connect the table to the data provider.
	    dataProvider.addDataDisplay(table);

	    // Add the data to the data provider, which automatically pushes it to the
	    // widget.
	    List<Contact> list = dataProvider.getList();
	    for (Contact contact : CONTACTS) {
	      list.add(contact);
	    }

	    // Add a ColumnSortEvent.ListHandler to connect sorting to the
	    // java.util.List.
	    ListHandler<Contact> columnSortHandler = new ListHandler<Contact>(
	        list);
	    columnSortHandler.setComparator(nameColumn,
	        new Comparator<Contact>() {
	          public int compare(Contact o1, Contact o2) {
	            if (o1 == o2) {
	              return 0;
	            }

	            // Compare the name columns.
	            if (o1 != null) {
	              return (o2 != null) ? o1.name.compareTo(o2.name) : 1;
	            }
	            return -1;
	          }
	        });
	    table.addColumnSortHandler(columnSortHandler);

	    // We know that the data is sorted alphabetically by default.
	    table.getColumnSortList().push(nameColumn);

		// -- END TABLE

		viewPanel.getElement().appendChild(nameSpan);

		viewPanel.add(table);
		
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
