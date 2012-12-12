package de.mxro.server.internal;

import java.util.LinkedList;
import java.util.List;

import de.mxro.server.ComponentConfiguration;
import de.mxro.server.ServerComponent;
import de.mxro.server.ShutdownCallback;
import de.mxro.server.StartCallback;
import de.mxro.server.manager.ComponentFactory;
import de.mxro.server.manager.ComponentManager;

public class DefaultComponentManager implements ComponentManager {

	private final ComponentFactory factory;
	private final List<ServerComponent> components;
	private final List<ServerComponent> running;

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
		synchronized (components) {

			final ServerComponent component = getComponent(componentId);

			if (component == null) {
				throw new IllegalStateException("No server with id ["
						+ componentId + "] is defined.");
			}

			synchronized (running) {
				if (running.contains(component)) {
					throw new IllegalStateException(
							"Cannot start an already running server ["
									+ componentId + "]");
				}

			}

			component.start(new StartCallback() {

				@Override
				public void onStarted() {
					synchronized (running) {
						running.add(component);
					}
					callback.onStarted();
				}

				@Override
				public void onFailure(final Throwable t) {
					callback.onFailure(t);
				}
			});

		}
	}

	@Override
	public void stopComponent(final String componentId,
			final ShutdownCallback callback) {
		synchronized (components) {

			final ServerComponent component = getComponent(componentId);

			if (component == null) {
				throw new IllegalStateException("No server with id ["
						+ componentId + "] is defined.");
			}

			synchronized (running) {
				if (!running.contains(component)) {
					throw new IllegalStateException(
							"Cannot stop an not running server [" + componentId
									+ "]");
				}
			}

			component.stop(new ShutdownCallback() {

				@Override
				public void onShutdownComplete() {
					synchronized (running) {
						running.remove(component);
					}
					callback.onShutdownComplete();
				}

				@Override
				public void onFailure(final Throwable t) {
					callback.onFailure(t);
				}
			});
		}
	}

	@Override
	public void removeComponent(final String componentId) {
		synchronized (components) {
			final ServerComponent component = getComponent(componentId);
			if (component == null) {
				throw new IllegalStateException("No server with the id ["
						+ componentId + "] is defined.");
			}

			synchronized (running) {
				if (running.contains(component)) {
					throw new IllegalStateException(
							"Cannot remove server. Server must first be stopped ["
									+ componentId + "]");
				}
			}

			components.remove(component);
		}
	}

	@Override
	public ServerComponent getComponent(final String componentId) {
		synchronized (components) {
			for (final ServerComponent comp : components) {
				if (comp.getConfiguration().getId().equals(componentId)) {
					return comp;
				}
			}
			return null;
		}
	}

	public DefaultComponentManager(final ComponentFactory factory) {
		super();
		this.factory = factory;
		this.components = new LinkedList<ServerComponent>();
		this.running = new LinkedList<ServerComponent>();
	}

}
