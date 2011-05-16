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
package com.gwt2go.dev.client.ui.widget;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Event;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.gwt2go.dev.client.ui.CellTableViewImpl.GetValue;

/**
 * Provide table with sorting
 * 
 * @author L.Pelov
 * 
 * @param <T>
 *            - custom object you want to expose to the table
 */
public class CellTableSorting<T> extends CellTable<T> {

	private final List<SortableHeader> allHeaders = new ArrayList<SortableHeader>();
	private ListDataProvider<T> dataProvider = new ListDataProvider<T>();

	public CellTableSorting(ProvidesKey<T> keyProvider) {
		super(keyProvider);
		
	    // Setup a history handler to reselect the associate menu item.
//	    final ValueChangeHandler<String> historyHandler = new ValueChangeHandler<
//	        String>() {
//	      public void onValueChange(ValueChangeEvent<String> event) {

// later check how you can work with the event's here	    	  
//	    	  Window.alert("Event value: " + event.getValue());
	    	  
//				// sort the clicked column
//				sortExpenses(dataProvider.getList(),
//						header.getReverseSort() ? descComparator
//								: ascComparator);

				// cellTable.redrawHeaders();
//				redrawHeaders();
	    	  
	    	  
/*	        // Get the content widget associated with the history token.
	        ContentWidget contentWidget = treeModel.getContentWidgetForToken(
	            event.getValue());
	        if (contentWidget == null) {
	          return;
	        }

	        // Expand the tree node associated with the content.
	        Category category = treeModel.getCategoryForContentWidget(
	            contentWidget);
	        TreeNode node = mainMenu.getRootTreeNode();
	        int childCount = node.getChildCount();
	        for (int i = 0; i < childCount; i++) {
	          if (node.getChildValue(i) == category) {
	            node.setChildOpen(i, true, true);
	            break;
	          }
	        }

	        // Select the node in the tree.
	        selectionModel.setSelected(contentWidget, true);

	        // Display the content widget.
	        displayContentWidget(contentWidget);*/
//	      }
//	    };
	    
	    // History.addValueChangeHandler(historyHandler);
		
	}

	/**
	 * Add a column of a {@link Comparable} type using default comparators.
	 * 
	 * @param <C>
	 *            the column type
	 * @param table
	 *            the table
	 * @param text
	 *            the header text
	 * @param cell
	 *            the cell used to render values
	 * @param getter
	 *            the {@link GetValue} used to retrieve cell values
	 * @return the new column
	 * @deprecated - use {@link #CellTableSorting(final String text, final
	 *             Cell<C> cell, final GetValue<T, C> getter, final
	 *             SortableHeader header)}
	 */
	public <C extends Comparable<C>> Column<T, C> addColumn(final String text,
			final Cell<C> cell, final GetValue<T, C> getter) {

		return addColumn(text, cell, getter,
				createColumnComparator(getter, false),
				createColumnComparator(getter, true), new SortHeader(text));
	}

	/**
	 * Add a column of a {@link Comparable} type using default comparators.
	 * 
	 * @param <C>
	 *            the column type
	 * @param table
	 *            the table
	 * @param text
	 *            the header text
	 * @param cell
	 *            the cell used to render values
	 * @param getter
	 *            the {@link GetValue} used to retrieve cell values
	 * @param header
	 *            - custom implemented header which extends
	 *            {@link SortableHeader}
	 * @return the new column
	 */
	public <C extends Comparable<C>> Column<T, C> addColumn(final String text,
			final Cell<C> cell, final GetValue<T, C> getter,
			final SortableHeader header) {

		return addColumn(text, cell, getter,
				createColumnComparator(getter, false),
				createColumnComparator(getter, true), header);
	}

	/**
	 * Add a sortable column to the table.
	 * 
	 * @param <C>
	 *            the data type for the column
	 * @param text
	 *            the header text
	 * @param cell
	 *            the cell used to render the column
	 * @param getter
	 *            the getter to retrieve the value for the column
	 * @param property
	 *            the property to sort by
	 * @param ascComparator
	 *            ascendent comparator
	 * @param descComparator
	 *            descendant comparator
	 * @return the column
	 */
	private <C> Column<T, C> addColumn(final String text, final Cell<C> cell,
			final GetValue<T, C> getter, final Comparator<T> ascComparator,
			final Comparator<T> descComparator, final SortableHeader header) {

		// gets the cell value
		final Column<T, C> column = new Column<T, C>(cell) {
			@Override
			public C getValue(T object) {
				return getter.getValue(object);
			}
		};

		// TODO: make this external to be able to add separated headers
		// final SortHeader header = new SortHeader(text);
		allHeaders.add(header);

		header.setUpdater(new ValueUpdater<String>() {
			@Override
			public void update(String value) {
				// Window.alert("History changer " + History.getToken());
				
				// TODO:
				// every time you click here, you can put the vent into a hashmap
				// and then onHistoryChange you can repeat the action!!!
				
				// put history tocken every time you click here
//				String historyTocken = History.getToken();
				
//				if (!historyTocken.endsWith(":"+header.getValue()+":")) {
//					History.newItem(historyTocken+":"+header.getValue()+":", true);
//				}				
				
				header.setSorted(true);
				header.toggleReverseSort();

				for (SortableHeader otherHeader : allHeaders) {
					if (otherHeader != header) {
						otherHeader.setSorted(false);
						otherHeader.setReverseSort(true);
					}
				}

				// sort the clicked column
				sortExpenses(dataProvider.getList(),
						header.getReverseSort() ? descComparator
								: ascComparator);

				// cellTable.redrawHeaders();
				redrawHeaders();

				// Go to the first page of the newly-sorted results, if wished
				// pager.firstPage();
			}
		});

		addColumn(column, header);
		return column;
	}

	/**
	 * Proceed with the sorting operation
	 * 
	 * @param list
	 *            data to sort
	 * @param comparator
	 *            the {@link Comparator} used for the sorting
	 */
	private void sortExpenses(List<T> list, final Comparator<T> comparator) {
		Collections.sort(list, comparator);
	}

	/**
	 * Implements the comparator for our ContactInfo object.
	 * 
	 * @param <C>
	 *            the data type from the column
	 * @param getter
	 *            the {@link GetValue} used to retrieve cell values
	 * @param descending
	 *            way of sorting
	 * @return
	 */
	private <C extends Comparable<C>> Comparator<T> createColumnComparator(
			final GetValue<T, C> getter, final boolean descending) {

		return new Comparator<T>() {
			public int compare(T o1, T o2) {
				// Null check the row object.
				if (o1 == null && o2 == null) {
					return 0;
				} else if (o1 == null) {
					return descending ? 1 : -1;
				} else if (o2 == null) {
					return descending ? -1 : 1;
				}

				// Compare the column value.
				C c1 = getter.getValue(o1);
				C c2 = getter.getValue(o2);
				if (c1 == null && c2 == null) {
					return 0;
				} else if (c1 == null) {
					return descending ? 1 : -1;
				} else if (c2 == null) {
					return descending ? -1 : 1;
				}
				int comparison = c1.compareTo(c2);
				return descending ? -comparison : comparison;
			}
		};
	}

	public void setData(List<T> data) {

		if (dataProvider.getDataDisplays() != null
				&& dataProvider.getDataDisplays().size() > 0) {
			dataProvider.removeDataDisplay(this);
			dataProvider.setList(data);

			// cellTable.setRowData(0, items);
			dataProvider.addDataDisplay(this);
		} else {
			dataProvider.setList(data);

			// cellTable.setRowData(0, items);
			dataProvider.addDataDisplay(this);
		}
	}

	public ListDataProvider<T> getDataProvider() {
		return dataProvider;
	}

	@Override
	protected void onBrowserEvent2(Event event) {
		// here we can try to get the current event and hang some special
		// functionality

		// IMPORTANT: Do not remove this otherwise all clicks on the table will
		// be disabled.
		super.onBrowserEvent2(event);
	}

}
