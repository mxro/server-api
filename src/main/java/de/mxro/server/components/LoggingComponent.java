package de.mxro.server.components;

import de.mxro.server.ComponentConfiguration;
import de.mxro.server.ComponentContext;
import de.mxro.server.ServerComponent;
import de.mxro.server.ShutdownCallback;
import de.mxro.server.StartCallback;

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
	public void stop(final ShutdownCallback callback) {
		conf.getListener().log(
				"Stopping component: " + conf.getId() + " " + this);
		callback.onShutdownComplete();
	}

	@Override
	public void start(final StartCallback callback) {
		conf.getListener().log(
				"Starting component: " + conf.getId() + " (" + this + ")");
		callback.onStarted();
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
