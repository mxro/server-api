package de.mxro.server.configuration;

import de.mxro.server.ComponentConfiguration;
import de.mxro.server.ServerComponent;

public interface CrashingComponentConfiguration extends ComponentConfiguration {

	public ServerComponent decoratedComponent();

}
