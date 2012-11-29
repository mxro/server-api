package de.mxro.server;

/**
 * Callback to receive notifcation when a server component has been started
 * completely.
 * 
 * @author <a href="http://www.mxro.de">Max Rohde</a>
 * 
 */
public interface StartCallback {

	/**
	 * Called once when the component has been started successfully.
	 */
	public void onStarted();

	/**
	 * Called once when an error occured during component startup.
	 * 
	 * @param t
	 */
	public void onFailure(Throwable t);

}
