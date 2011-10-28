/**
 * 
 */
package com.gwt2go.dev.client.ui.icons;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * @author L.Pelov
 */
public interface DocIcons extends ClientBundle {

	public DocIcons RESOURCES = GWT.create(DocIcons.class);
		
	@Source("iconDocZip16.gif")
	ImageResource iconDocZip16();
	
	@Source("dir.gif")
	ImageResource dir();

	@Source("icon_doc_excel.gif")
	ImageResource icon_doc_excel();

	@Source("icon_doc_exchange.gif")
	ImageResource icon_doc_exchange();

	@Source("icon_doc_pdf.gif")
	ImageResource icon_doc_pdf();

	@Source("icon_doc_ppoint.gif")
	ImageResource icon_doc_ppoint();

	@Source("icon_doc_visio.gif")
	ImageResource icon_doc_visio();

	@Source("icon_doc_word.gif")
	ImageResource icon_doc_word();

	@Source("icon_msproject.gif")
	ImageResource icon_msproject();

	@Source("tree_icon_collection_closed.gif")
	ImageResource tree_icon_collection_closed();

	@Source("tree_icon_item.gif")
	ImageResource tree_icon_item();

}
