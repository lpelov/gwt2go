package com.gwt2go.dev.client.ui.view;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface CellTreeExpl1View extends IsWidget {

	void setName(String tableName);

	void setPresenter(Presenter presenter);

	public interface Presenter {
		void goTo(Place place);
	}

}
