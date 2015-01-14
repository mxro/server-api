package de.mxro.server;

import de.mxro.async.properties.PropertyNode;
import de.mxro.factories.FactoryCollection;
import de.mxro.service.ServiceRegistry;

/**
 * Contextual variables for a Server Component.
 * 
 * @author Max
 * 
 */
public interface ComponentContext {

    public PropertyNode metrics();

    public FactoryCollection factories();

    public ServiceRegistry services();

}
