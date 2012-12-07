package de.mxro.server.jre;

import java.util.Timer;
import java.util.TimerTask;

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

	private volatile boolean running = false;
	private volatile boolean stopped = false;
	private volatile boolean stopping = false;

	@Override
	public void stop(final ShutdownCallback callback) {
		while (stopping) {
		}
		if (!stopped) {
			decorated.stop(new ShutdownCallback() {

				@Override
				public void onShutdownComplete() {
					running = false;
					callback.onShutdownComplete();
				}

				@Override
				public void onFailure(final Throwable t) {
					callback.onFailure(t);
				}
			});
		} else {
			running = false;
			callback.onShutdownComplete();
		}

	}

	@Override
	public void start(final StartCallback callback) {

		assert running == false;

		this.decorated = conf.decoratedComponent();
		running = true;
		decorated.start(callback);

		stopped = false;
		stopping = true;
		final TimerTask tt = new TimerTask() {

			@Override
			public void run() {
				stopping = true;
				decorated.stop(new ShutdownCallback() {

					@Override
					public void onShutdownComplete() {
						stopping = false;
						stopped = true;
					}

					@Override
					public void onFailure(final Throwable t) {
						throw new RuntimeException(t);
					}
				});
			}
		};
		new Timer().schedule(tt, conf.crashAfterMs());
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
