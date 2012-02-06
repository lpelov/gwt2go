/*
 * Copyright 2012
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
package com.gwt2go.dev.client.ui.widget.celltree;

import java.util.ArrayList;
import java.util.List;

public class CellTreeRooterDaoComposer {

	private final String name;
	private final List<CellTreeRooterDaoModel> subNodes = new ArrayList<CellTreeRooterDaoModel>();

	public CellTreeRooterDaoComposer(String name) {
		this.name = name;
	}

	public CellTreeRooterDaoModel addNode(CellTreeRooterDaoModel node) {
		subNodes.add(node);
		return node;
	}

	public String getName() {
		return name;
	}

	public List<CellTreeRooterDaoModel> getSubNodes() {
		return subNodes;
	}

}
