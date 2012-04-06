package com.gwt2go.dev.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.gwt2go.dev.client.ClientFactory;
import com.gwt2go.dev.client.place.CellTreeExpl1Place;
import com.gwt2go.dev.client.ui.view.CellTreeExpl1View;

public class CellTreeExpl1Activity extends AbstractActivity implements CellTreeExpl1View.Presenter {
	
	// Used to obtain views, eventBus, placeController
	// Alternatively, could be injected via GIN
	private ClientFactory clientFactory;

	public CellTreeExpl1Activity(CellTreeExpl1Place place, ClientFactory clientFactory) {
		// this.name = place.getCellTableName();
		this.clientFactory = clientFactory;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {

//		EditorView editorView = this.clientFactory
//				.getEditorView();
//
//		editorView.setName("Editor");
//		editorView.setPresenter(this);
//		panel.setWidget(editorView);

		CellTreeExpl1View cellTreeExpl1View = this.clientFactory.getCellTreeViewExpl1();
		cellTreeExpl1View.setName("Cell Tree Examples");
		cellTreeExpl1View.setPresenter(this);
		panel.setWidget(cellTreeExpl1View);
	}

	@Override
	public void goTo(Place place) {
	}


}
