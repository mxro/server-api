package de.mxro.server.contexts;

import de.mxro.server.ComponentContext;

/**
 * A {@link ComponentContext} combined with a {@link StatefulContext}.
 * 
 * @author Max
 * 
 */
public interface StatefulComponentContext extends ComponentContext,
		StatefulContext {

}
