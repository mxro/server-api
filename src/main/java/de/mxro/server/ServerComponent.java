/*******************************************************************************
 * Copyright 2011 Max Erik Rohde http://www.mxro.de
 * 
 * All rights reserved.
 ******************************************************************************/
package de.mxro.server;

import de.mxro.service.Service;

/**
 * A component of a server process.
 * 
 * @author <a href="http://www.mxro.de/">Max Erik Rohde</a>
 * 
 *         Copyright Max Erik Rohde 2011. All rights reserved.
 */
public interface ServerComponent extends Service {

	/**
	 * Set a configuration for this component
	 * 
	 * @param conf
	 */
	public void injectConfiguration(ComponentConfiguration conf);

	/**
	 * Set a server context for this component
	 * 
	 * @param context
	 */
	public void injectContext(ComponentContext context);

	/**
	 * Retrive the current configuration for this component
	 * 
	 * @return
	 */
	public ComponentConfiguration getConfiguration();
}
