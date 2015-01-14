package de.mxro.server.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import de.mxro.async.callbacks.SimpleCallback;
import de.mxro.factories.FactoryCollection;
import de.mxro.server.ComponentConfiguration;
import de.mxro.server.ComponentContext;
import de.mxro.server.ComponentDependencies;
import de.mxro.server.ServerComponent;
import de.mxro.server.manager.ComponentManager;
import de.mxro.service.callbacks.ShutdownCallback;

public class DefaultComponentManager implements ComponentManager {

    private final FactoryCollection factories;
    private final List<ServerComponent> components;
    private final List<ServerComponent> running;

    @Override
    public ServerComponent addComponent(final int index, final ComponentContext context,
            final ComponentConfiguration conf) {
        final ServerComponent component;

        component = createComponent(context, conf);
        components.add(index, component);

        return component;
    }

    @Override
    public ServerComponent addComponent(final ComponentContext context, final ComponentConfiguration conf) {
        final ServerComponent component;

        component = createComponent(context, conf);
        components.add(component);

        return component;
    }

    @Override
    public ServerComponent addRunningComponent(final ComponentContext context, final ServerComponent component) {

        components.add(component);
        component.injectContext(context);
        this.running.add(component);

        return component;
    }

    @Override
    public List<ServerComponent> getComponents() {

        return Collections.unmodifiableList(new ArrayList<ServerComponent>(this.components));
    }

    private ServerComponent createComponent(final ComponentContext context, final ComponentConfiguration conf) {
        assert context != null;
        assert conf != null;

        if (getComponent(conf.getId()) != null) {
            throw new IllegalStateException("A server with the id [" + conf.getId() + "] is already defined.");
        }
        final ComponentDependencies dependencies = new ComponentDependencies() {
        };
        final ServerComponent component = (ServerComponent) factories.create(conf, dependencies);

        component.injectContext(context);
        component.injectConfiguration(conf);

        return component;
    }

    @Override
    public void startComponent(final String componentId, final SimpleCallback callback) {

        final ServerComponent component = getComponent(componentId);

        if (component == null) {
            callback.onFailure(new IllegalStateException("No server with id [" + componentId + "] is defined."));
            return;
        }

        if (running.contains(component)) {
            callback.onFailure(new IllegalStateException("Cannot start an already running server [" + componentId + "]"));
            return;
        }

        component.start(new SimpleCallback() {

            @Override
            public void onSuccess() {

                running.add(component);

                callback.onSuccess();
            }

            @Override
            public void onFailure(final Throwable t) {
                callback.onFailure(t);
            }
        });

    }

    private List<String> getComponentIds() {
        final ArrayList<String> ids = new ArrayList<String>(this.getComponents().size());
        for (final ServerComponent comp : this.getComponents()) {
            ids.add(comp.getConfiguration().getId());
        }
        return ids;
    }

    @Override
    public void stopComponent(final String componentId, final ShutdownCallback callback) {

        final ServerComponent component = getComponent(componentId);

        if (component == null) {
            callback.onFailure(new IllegalStateException("No server component with id [" + componentId
                    + "] is defined. Defined components are: " + getComponentIds()));
            return;
        }

        if (!running.contains(component)) {
            callback.onFailure(new IllegalStateException("Cannot stop an not running server [" + componentId + "]"));
            return;
        }

        component.stop(new ShutdownCallback() {

            @Override
            public void onSuccess() {

                running.remove(component);

                callback.onSuccess();
            }

            @Override
            public void onFailure(final Throwable t) {
                callback.onFailure(t);
            }
        });

    }

    @Override
    public int removeComponent(final String componentId) {

        final ServerComponent component = getComponent(componentId);
        if (component == null) {
            throw new IllegalStateException("No server with the id [" + componentId + "] is defined.");
        }

        if (running.contains(component)) {
            throw new IllegalStateException("Cannot remove server. Server must first be stopped [" + componentId + "]");
        }

        final int idx = components.indexOf(component);

        if (idx < 0) {
            throw new IllegalStateException("Cannot remove server that is not defined.");
        }

        components.remove(component);
        return idx;

    }

    @Override
    public ServerComponent getComponent(final String componentId) {

        for (final ServerComponent comp : components) {
            if (componentId.equals(comp.getConfiguration().getId())) {
                return comp;
            }
        }
        return null;

    }

    public DefaultComponentManager(final FactoryCollection factories) {
        super();
        this.factories = factories;

        this.components = new LinkedList<ServerComponent>();
        this.running = new LinkedList<ServerComponent>();

    }

    @Override
    public boolean isRunning(final ServerComponent component) {

        return this.running.contains(component);
    }

}
