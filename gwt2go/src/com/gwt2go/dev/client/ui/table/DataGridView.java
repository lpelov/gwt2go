package com.gwt2go.dev.client.ui.table;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface DataGridView extends IsWidget {

	void setName(String tableName);

	void setPresenter(Presenter presenter);

	public interface Presenter {
		void goTo(Place place);
	}

}
