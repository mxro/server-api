package de.mxro.server.configuration.v01;

import de.mxro.server.ComponentConfiguration;

public class CrashingComponentConfigurationData implements
		ComponentConfiguration {

	private static final long serialVersionUID = 1L;

	public String id;

	public ComponentConfiguration decoratedConfiguration;

	public int crashAfterMs;

	@Override
	public boolean isBackgroundService() {
		return false;
	}

	@Override
	public String getId() {
		return id;
	}

	public CrashingComponentConfigurationData() {
		super();
	}

}
