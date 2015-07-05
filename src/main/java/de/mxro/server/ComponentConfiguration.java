package de.mxro.server;

import delight.factories.Configuration;

/**
 * The configuration for a particular component of a server (for instance RPC
 * server)
 * 
 * @author Max
 * 
 */
public interface ComponentConfiguration extends Configuration {

	/**
	 * Is this a critical component for the server or is it a background server, which can be shut down asynchronously.
	 * @return
	 */
	public boolean isBackgroundService();

	/**
	 * A unique ID for this component within the server it is running in.
	 * 
	 * @return
	 */
	public String getId();

}
