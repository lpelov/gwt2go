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
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.SelectionCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;
import com.gwt2go.dev.client.data.ContactDatabase;
import com.gwt2go.dev.client.data.ContactDatabase.Category;
import com.gwt2go.dev.client.data.ContactDatabase.ContactInfo;
import com.gwt2go.dev.client.place.RootPlace;
import com.gwt2go.dev.client.ui.widget.DialogBoxExt;
import com.gwt2go.dev.client.ui.widget.SortHeader;
import com.gwt2go.dev.shared.DemoUser;

public class CellTableViewImpl extends Composite implements HasText,
		CellTableView {

	private static CellTableViewImplUiBinder uiBinder = GWT
			.create(CellTableViewImplUiBinder.class);

	interface CellTableViewImplUiBinder extends
			UiBinder<Widget, CellTableViewImpl> {
	}

	// public CellTableViewImpl() {
	// initWidget(uiBinder.createAndBindUi(this));
	// }

	@UiField
	HTMLPanel htmlPanel;

	@UiField
	Button button;

	@UiField
	Button button2;

	@UiField
	Button dialogBox;

	@UiField
	TextBox mytextbox;

	/**
	 * The main CellTable.
	 */
	@UiField(provided = true)
	CellTable<ContactInfo> cellTable;

	/**
	 * The pager used to change the range of data.
	 */
	@UiField(provided = true)
	SimplePager pager;

	// UI Binders
	private Presenter presenter;

	public CellTableViewImpl(/* String firstName */) {
		// Create a CellTable.

		// Set a key provider that provides a unique key for each contact. If
		// key is
		// used to identify contacts when fields (such as the name and address)
		// change.
		cellTable = new CellTable<ContactDatabase.ContactInfo>(
				ContactDatabase.ContactInfo.KEY_PROVIDER);

		// Create a Pager to control the table.
		SimplePager.Resources pagerResources = GWT
				.create(SimplePager.Resources.class);
		pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0,
				true);
		pager.setDisplay(cellTable);

		// Add a selection model so we can select cells.
		final SingleSelectionModel<ContactInfo> selectionModel = new SingleSelectionModel<ContactInfo>(
				ContactInfo.KEY_PROVIDER);
		cellTable.setSelectionModel(selectionModel);

		// Initialize the columns.
		initTableColumns(selectionModel);

		// Add the CellList to the adapter in the database.
		ContactDatabase.get().addDataDisplay(cellTable);

		initWidget(uiBinder.createAndBindUi(this));

		String firstname = "John";
		String lastname = "Familyguy";
		Date age = new Date(); // get's the current date/time

		DemoUser myuser = new DemoUser(firstname, lastname, age);

		CalendarUtil.addDaysToDate(age, 2);

		mytextbox.setText(myuser.getAge().toString());

		CalendarUtil.addDaysToDate(myuser.getAge(), 5);

		mytextbox.setText(myuser.getAge().toString());

		button.setText("Click me to go to the new sortable table example");
		button2.setText("CustomPlaces");
		dialogBox.setText("Open DialogBox");

	}

	@UiHandler("button2")
	void onButton2Click(ClickEvent e) {
		this.presenter.goTo(new RootPlace("rootview"));
	}

	@UiHandler("button")
	void onClick(ClickEvent e) {
		// experiments to give back to the presenter
		// Window.alert("Hello!");
		// this.presenter.goTo(new HelloPlace("after table"));
		presenter.onButtonClicked();
	}

	@UiHandler("dialogBox")
	void onDialogBoxClick(ClickEvent e) {
		// presenter.onDialogBoxBtnClicked();
		HTML close = new HTML("<b>X</b>");
		DialogBoxExt dialog = new DialogBoxExt(close);

		dialog.setAnimationEnabled(true);
		dialog.setAutoHideEnabled(true);

		HTML text2 = new HTML("Text 1 Text 1 Text 1 Text 1 Text 1");
		HTML text3 = new HTML("Text 2");
		HTML text4 = new HTML("Text 3");
		VerticalPanel vrPanel = new VerticalPanel();

		// dialog.setHTML("This is the header");
		dialog.setWidth("250px");

		vrPanel.add(text2);
		vrPanel.add(text3);
		vrPanel.add(text4);

		dialog.setWidget(vrPanel);

		dialog.center();
		dialog.show();
	}

	public void setText(String text) {
		// button.setText(text);
	}

	public String getText() {
		// return button.getText();
		return "";
	}

	public HasText getTextBox() {
		return mytextbox;
	}

	@Override
	public void setName(String helloName) {

	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	/**
	 * Add the columns to the table.
	 */
	private void initTableColumns(
			final SelectionModel<ContactInfo> selectionModel) {
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

		// First name.
		// Column<ContactInfo, String> firstNameColumn = new Column<ContactInfo,
		// String>(
		// new EditTextCell()) {
		// @Override
		// public String getValue(ContactInfo object) {
		// return object.getFirstName();
		// }
		// };

		// TextHeader firstNameHeader = new TextHeader("First name");
		// firstNameHeader.setUpdater(new ValueUpdater<String>() {
		// @Override
		// public void update(String value) {
		// Window.alert("Update the header");
		// }
		// });
		//
		// cellTable.addColumn(firstNameColumn, firstNameHeader);
		//
		// firstNameColumn
		// .setFieldUpdater(new FieldUpdater<ContactInfo, String>() {
		// public void update(int index, ContactInfo object,
		// String value) {
		// // Called when the user changes the value.
		// object.setFirstName(value);
		// ContactDatabase.get().refreshDisplays();
		// }
		// });

		addColumn("First name", new TextCell(),
				new GetValue<ContactInfo, String>() {
					public String getValue(ContactInfo object) {
						return object.getFirstName();
					}
				});

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
		addColumn("Address", new TextCell(),
				new GetValue<ContactInfo, String>() {
					public String getValue(ContactInfo object) {
						return object.getAddress();
					}
				});

		// cellTable.addColumn(new Column<ContactInfo, String>(new TextCell()) {
		// @Override
		// public String getValue(ContactInfo object) {
		// return object.getAddress();
		// }
		// }, "Address");
	}

	public interface GetValue<T, C> {
		C getValue(T object);
	}

	private final List<SortHeader> allHeaders = new ArrayList<SortHeader>();

	/**
	 * The field to sort by.
	 */
	// private String orderBy = "Address";

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
	 */
	private <C extends Comparable<C>> Column<ContactInfo, C> addColumn(
			final String text, final Cell<C> cell,
			final GetValue<ContactInfo, C> getter) {

		return addColumn(text, cell, getter,
				createColumnComparator(getter, false),
				createColumnComparator(getter, true));
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
	private <C> Column<ContactInfo, C> addColumn(final String text,
			final Cell<C> cell, final GetValue<ContactInfo, C> getter,
			final Comparator<ContactInfo> ascComparator,
			final Comparator<ContactInfo> descComparator) {

		// gets the cell value
		final Column<ContactInfo, C> column = new Column<ContactInfo, C>(cell) {
			@Override
			public C getValue(ContactInfo object) {
				return getter.getValue(object);
			}
		};

		final SortHeader header = new SortHeader(text);
		allHeaders.add(header);

		// call this everytime headers is clicked
		header.setUpdater(new ValueUpdater<String>() {
			public void update(String value) {
				header.setSorted(true);
				header.toggleReverseSort();

				for (SortHeader otherHeader : allHeaders) {
					if (otherHeader != header) {
						otherHeader.setSorted(false);
						otherHeader.setReverseSort(true);
					}
				}

				// sort the clicked column
				sortExpenses(ContactDatabase.get().getDataProvider().getList(),
						header.getReverseSort() ? descComparator
								: ascComparator);

				cellTable.redrawHeaders();

				// Go to the first page of the newly-sorted results, if wished
				// pager.firstPage();
			}
		});
		cellTable.addColumn(column, header);
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
	private void sortExpenses(List<ContactInfo> list,
			final Comparator<ContactInfo> comparator) {
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
	private <C extends Comparable<C>> Comparator<ContactInfo> createColumnComparator(
			final GetValue<ContactInfo, C> getter, final boolean descending) {
		return new Comparator<ContactInfo>() {
			public int compare(ContactInfo o1, ContactInfo o2) {
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

}
