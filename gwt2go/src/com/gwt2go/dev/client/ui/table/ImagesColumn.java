package com.gwt2go.dev.client.ui.table;

import com.google.gwt.user.cellview.client.Column;

/**
 * 
 * @author lpelov
 * 
 * @param <T>
 */
public abstract class ImagesColumn<T> extends Column<T, String> {

	public ImagesColumn() {
		super(new ImagesCell());
	}

}
