package de.mxro.server;

import de.mxro.factories.FactoryCollection;
import de.mxro.server.contexts.LocalStatefulContext;
import de.mxro.server.contexts.LogCallback;
import de.mxro.server.contexts.StatefulContext;
import de.mxro.server.internal.DefaultComponentManager;
import de.mxro.server.manager.ComponentManager;

public class ServerApi {

	/**
	 * <p>
	 * A default manager for components.
	 * </p>
	 * <p>
	 * Beware this manager is not thread safe.
	 * </p>
	 * 
	 * @param factory
	 * @return
	 */
	public static ComponentManager createManager(final FactoryCollection factories) {
		return new DefaultComponentManager(factories);

	}

	public static LogCallback logCallback(final Object source) {
		return new LogCallback() {

			@Override
			public void onLogged() {

			}

			@Override
			public void onFailure(final Throwable t) {
				throw new RuntimeException("Log call failed from: " + source, t);
			}
		};
	}

	public static StatefulContext createLocalStatefulContext() {
		return new LocalStatefulContext();
	}

}
