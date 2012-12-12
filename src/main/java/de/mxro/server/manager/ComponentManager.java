package de.mxro.server.manager;

import de.mxro.server.ComponentConfiguration;
import de.mxro.server.ServerComponent;
import de.mxro.server.ShutdownCallback;
import de.mxro.server.StartCallback;

public interface ComponentManager {

	public void addComponent(ComponentConfiguration conf);

	public void startComponent(String componentId, StartCallback callback);

	public void stopComponent(String componentId, ShutdownCallback callback);

	public void removeComponent(String componentId);

	public ServerComponent getComponent(String componentId);

}
