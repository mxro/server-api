package de.mxro.server.components;

import delight.async.callbacks.SimpleCallback;

import de.mxro.server.ComponentConfiguration;
import de.mxro.server.ComponentContext;
import de.mxro.server.ServerComponent;
import de.mxro.server.configuration.v01.LoggingComponentConfiguration;

/**
 * A component useful for test and debugging, which will write all actions
 * performed onto it in a specified log.
 * 
 * @author <a href="http://www.mxro.de">Max Rohde</a>
 * 
 */
public class LoggingComponent implements ServerComponent {

	LoggingComponentConfiguration conf;

	@Override
	public void stop(final SimpleCallback callback) {
		conf.getListener().log(
				"Stopping component: " + conf.getId() + " " + this);
		callback.onSuccess();
	}

	@Override
	public void start(final SimpleCallback callback) {
		conf.getListener().log(
				"Starting component: " + conf.getId() + " (" + this + ")");
		callback.onSuccess();
	}

	@Override
	public void injectConfiguration(final ComponentConfiguration conf) {
		this.conf = (LoggingComponentConfiguration) conf;
	}

	@Override
	public void injectContext(final ComponentContext context) {
		// not required
	}

	@Override
	public ComponentConfiguration getConfiguration() {
		return conf;
	}

}
