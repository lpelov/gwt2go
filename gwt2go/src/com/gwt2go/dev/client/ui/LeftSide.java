package com.gwt2go.dev.client.ui;


import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.TreeViewModel;
import com.gwt2go.dev.client.place.RootPlace;
import com.gwt2go.dev.client.ClientFactory;

public class LeftSide extends Composite {

	private static LeftSideUiBinder uiBinder = GWT
			.create(LeftSideUiBinder.class);

	interface LeftSideUiBinder extends UiBinder<Widget, LeftSide> {
	}

	@UiField
	Button button1;
	
	@UiField
	Button button2;
	
	@UiField
	Button button3;

	@UiField
	Button btnEditor;

	@UiField(provided=true) CellTree cellTree = new CellTree(
		new TreeViewModel() {
//			final AbstractDataProvider<String> dataProvider = new ListDataProvider<String>();
//			final AbstractSelectionModel<String> selectionModel = new NoSelectionModel<String>();
			@Override
			public <T> NodeInfo<?> getNodeInfo(T value) {
			      /*
			       * Create some data in a data provider. Use the parent value as a prefix
			       * for the next level.
			       */
			      ListDataProvider<String> dataProvider = new ListDataProvider<String>();
			      for (int i = 0; i < 2; i++) {
			        dataProvider.getList().add(value + "." + String.valueOf(i));
			      }

			      // Return a node info that pairs the data with a cell.
			      return new DefaultNodeInfo<String>(dataProvider, new TextCell());
				//return new DefaultNodeInfo<String>(dataProvider, new TextCell(), selectionModel, null);
			}
			@Override
			public boolean isLeaf(Object value) {
				return value.toString().length() > 10;
				//return true;
			}
		}, "Item 1");

	ClientFactory clientFactory;
	
	public LeftSide(ClientFactory clientFactory) {
		initWidget(uiBinder.createAndBindUi(this));
		button1.setText("SortingTable1");
		button2.setText("SortingTable2");
		button3.setText("SortingTable_GWT2.3");
		btnEditor.setText("Editor");
		
		this.clientFactory = clientFactory;
		this.cellTree.setAnimationEnabled(true);
	}

	@UiHandler("button1")
	void onButton1Click(ClickEvent e) {
		this.clientFactory.getPlaceController().goTo(new RootPlace("table"));
	}

	@UiHandler("button2")
	void onButton2Click(ClickEvent e) {
		this.clientFactory.getPlaceController().goTo(new RootPlace("table2"));
	}

	@UiHandler("button3")
	void onButton3Click(ClickEvent e) {
		this.clientFactory.getPlaceController().goTo(new RootPlace("sortingtable23"));
	}

	@UiHandler("btnEditor")
	void onbtnEditorClick(ClickEvent e) {
		this.clientFactory.getPlaceController().goTo(new RootPlace("editor"));
	}

}
