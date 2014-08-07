package de.mxro.server.jre;

import java.util.Timer;
import java.util.TimerTask;

import de.mxro.server.ComponentConfiguration;
import de.mxro.server.ComponentContext;
import de.mxro.server.ServerComponent;
import de.mxro.server.configuration.CrashingComponentConfiguration;
import de.mxro.service.callbacks.ShutdownCallback;
import de.mxro.service.callbacks.StartCallback;

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
	ComponentContext ctx;
	ServerComponent decorated;

	private volatile boolean running = false;
	private volatile boolean stopped = false;
	private volatile boolean stopping = false;

	@Override
	public synchronized void stop(final ShutdownCallback callback) {
		assert running;

		while (stopping) {

		}

		if (!stopped) {
			stopping = true;
			decorated.stop(new ShutdownCallback() {

				@Override
				public void onSuccess() {
					running = false;
					stopping = false;
					callback.onSuccess();
				}

				@Override
				public void onFailure(final Throwable t) {
					callback.onFailure(t);
				}
			});
		} else {
			running = false;
			callback.onSuccess();
		}

	}

	@Override
	public synchronized void start(final StartCallback callback) {

		assert running == false;

		this.decorated = conf.decoratedComponent();
		running = true;
		decorated.start(callback);

		stopped = false;
		stopping = false;

		final TimerTask tt = new TimerTask() {

			@Override
			public void run() {

				stopping = true;
				decorated.stop(new ShutdownCallback() {

					@Override
					public void onSuccess() {
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
		this.ctx = context;
	}

	@Override
	public ComponentConfiguration getConfiguration() {
		return conf;
	}

	public CrashingComponent() {
		super();

	}

}
