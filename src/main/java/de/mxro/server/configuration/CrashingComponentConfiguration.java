package de.mxro.server.configuration;

import de.mxro.server.ComponentConfiguration;
import de.mxro.server.ServerComponent;
import de.mxro.server.jre.CrashingComponent;

/**
 * Configuration for a {@link CrashingComponent}.
 * 
 * @author mroh004
 * 
 */
public interface CrashingComponentConfiguration extends ComponentConfiguration {

	/**
	 * After how many ms will the decorated server be crashed?
	 * 
	 * @return
	 */
	public int crashAfterMs();

	/**
	 * The component wrapped by this crashing component.
	 * 
	 * @return
	 */
	public ServerComponent decoratedComponent();

}
