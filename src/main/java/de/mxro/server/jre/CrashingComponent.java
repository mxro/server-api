package de.mxro.server.jre;

import de.mxro.server.ComponentConfiguration;
import de.mxro.server.ComponentContext;
import de.mxro.server.ServerComponent;
import de.mxro.server.ShutdownCallback;
import de.mxro.server.StartCallback;
import de.mxro.server.configuration.CrashingComponentConfiguration;

/**
 * <p>
 * This component can be configured to 'crash' after a defined interval.
 * </p>
 * <p>
 * Useful for stress testing and failure-case testing.
 * </p>
 * 
 * @author Max
 * 
 */
public class CrashingComponent implements ServerComponent {

	CrashingComponentConfiguration conf;
	ServerComponent decorated;

	@Override
	public void stop(final ShutdownCallback callback) {
		decorated.stop(callback);
	}

	@Override
	public void start(final StartCallback callback) {
		this.decorated = conf.decoratedComponent();
		decorated.start(callback);
	}

	@Override
	public void injectConfiguration(final ComponentConfiguration conf) {
		this.conf = (CrashingComponentConfiguration) conf;
	}

	@Override
	public void injectContext(final ComponentContext context) {
		// not required
	}

	@Override
	public ComponentConfiguration getConfiguration() {
		return conf;
	}

	public CrashingComponent() {
		super();

	}

}
