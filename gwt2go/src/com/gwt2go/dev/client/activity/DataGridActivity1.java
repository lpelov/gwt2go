package com.gwt2go.dev.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.gwt2go.dev.client.ClientFactory;
import com.gwt2go.dev.client.place.DataGridPlace;
import com.gwt2go.dev.client.ui.table.DataGridView;

public class DataGridActivity1 extends AbstractActivity implements
		DataGridView.Presenter {

	private ClientFactory clientFactory;

	public DataGridActivity1(DataGridPlace place, ClientFactory clientFactory) {

		// this.name = place.getCellTableName();
		this.clientFactory = clientFactory;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {

		DataGridView dataGridView = this.clientFactory.getDataGridView("");

		dataGridView.setName("DataGridView 1");
		dataGridView.setPresenter(this);

		panel.setWidget(dataGridView);

	}

	@Override
	public void goTo(Place place) {
	}

}
