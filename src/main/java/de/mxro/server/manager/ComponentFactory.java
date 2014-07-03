package de.mxro.server.manager;

import de.mxro.factories.Factory;
import de.mxro.server.ComponentConfiguration;
import de.mxro.server.ComponentDependencies;
import de.mxro.server.ServerComponent;

/**
 * A factory for a {@link ServerComponent}s.
 * 
 * @author Max
 * 
 */
public interface ComponentFactory extends Factory<ServerComponent, ComponentConfiguration, ComponentDependencies> {



}
