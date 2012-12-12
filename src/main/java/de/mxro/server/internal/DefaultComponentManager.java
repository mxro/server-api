package de.mxro.server.internal;

import java.util.List;

import de.mxro.server.ComponentConfiguration;
import de.mxro.server.ComponentFactory;
import de.mxro.server.ComponentManager;
import de.mxro.server.ServerComponent;
import de.mxro.server.ShutdownCallback;
import de.mxro.server.StartCallback;

public class DefaultComponentManager implements ComponentManager {

	private final ComponentFactory factory;
	private final List<ServerComponent> components;

	@Override
	public void addComponent(final ComponentConfiguration conf) {
		synchronized (components) {
			if (getComponent(conf.getId()) != null) {
				throw new IllegalStateException("A server with the id ["
						+ conf.getId() + "] is already defined.");
			}
			components.add(factory.createComponent(conf));
		}
	}

	@Override
	public void startComponent(final String componentId,
			final StartCallback callback) {

	}

	@Override
	public void stopComponent(final String componentId,
			final ShutdownCallback callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeComponent(final String componentId) {

	}

	@Override
	public ServerComponent getComponent(final String componentId) {
		for (final ServerComponent comp : components) {
			if (comp.getConfiguration().getId().equals(componentId)) {
				return comp;
			}
		}
		return null;
	}

}
