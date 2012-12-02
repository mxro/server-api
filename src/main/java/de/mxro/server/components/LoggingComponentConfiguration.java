package de.mxro.server.components;

import de.mxro.server.ComponentConfiguration;

public class LoggingComponentConfiguration implements ComponentConfiguration {

	private static final long serialVersionUID = 1L;
	public String id;
	public LoggingListener listener;

	@Override
	public boolean isBackgroundService() {
		return false;
	}

	@Override
	public String getId() {
		return id;
	}

	public LoggingListener getListener() {
		return listener;
	}

	public LoggingComponentConfiguration(final String id,
			final LoggingListener listener) {
		super();
		this.id = id;
		this.listener = listener;
	}

	/**
	 * For deserialization.
	 */
	@Deprecated
	public LoggingComponentConfiguration() {
		super();
	}

}
