package com.gwt2go.dev.client.ui.widget;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.user.cellview.client.Header;

/**
 * Clickable cell is always string, so our sortable header is getting only
 * string
 * 
 * @author L.Pelov
 */
public abstract class SortableHeader extends Header<String> {

	public SortableHeader(Cell<String> cell) {
		super(cell);
	}

	private boolean reverseSort = false;
	protected boolean sorted = false;

	public boolean getReverseSort() {
		return reverseSort;
	}

	public void setReverseSort(boolean reverseSort) {
		this.reverseSort = reverseSort;
	}

	public void setSorted(boolean sorted) {
		this.sorted = sorted;
	}

	public void toggleReverseSort() {
		this.reverseSort = !this.reverseSort;
	}

}
