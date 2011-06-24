package com.gwt2go.dev.linker;

import com.google.gwt.core.ext.LinkerContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.linker.AbstractLinker;
import com.google.gwt.core.ext.linker.ArtifactSet;
import com.google.gwt.core.ext.linker.CompilationResult;
import com.google.gwt.core.ext.linker.LinkerOrder;
import com.google.gwt.core.ext.linker.SelectionProperty;

/**
 * Very simple linker
 * 
 * @author L.Pelov
 */
@LinkerOrder(LinkerOrder.Order.POST)
public class ManifestLinker extends AbstractLinker {

	@Override
	public String getDescription() {
		return "Personal simple linker";
	}

	public ArtifactSet link(TreeLogger logger, LinkerContext context,
			ArtifactSet artifacts) throws UnableToCompleteException {

		// Create a new set of artifacts that we can modify and return.
		ArtifactSet toReturn = new ArtifactSet(artifacts);

		// TODO: tests
		for (SelectionProperty property : context.getProperties()) {
			System.out.println(property.getName());
			
		}
		
		for (CompilationResult compilationResult : artifacts
				.find(CompilationResult.class)) {
			
			System.out.println(compilationResult.getStrongName());
			
		}

		// Add a new artifact to the set that we're returning.
		toReturn.add(emitString(logger, "Some simple text here",
				"gwt2go.appcache"));

		return toReturn;
	}

}