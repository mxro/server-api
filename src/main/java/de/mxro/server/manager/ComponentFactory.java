package de.mxro.server.manager;

import de.mxro.server.ComponentConfiguration;
import de.mxro.server.ServerComponent;

/**
 * A factory for a {@link ServerComponent}s.
 * 
 * @author Max
 * 
 */
public interface ComponentFactory {

	public ServerComponent createComponent(ComponentConfiguration conf);

}
