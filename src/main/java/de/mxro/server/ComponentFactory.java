package de.mxro.server;

import de.mxro.factories.Factory;

/**
 * A factory for a {@link ServerComponent}s.
 * 
 * @author Max
 * 
 */
public interface ComponentFactory<ServerComponentType extends ServerComponent, ComponentConfigurationType extends ComponentConfiguration, ComponentDependenciesType extends ComponentDependencies> 
extends Factory<ServerComponentType, ComponentConfigurationType, ComponentDependenciesType> {



}
