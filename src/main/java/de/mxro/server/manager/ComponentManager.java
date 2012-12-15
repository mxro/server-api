package de.mxro.server.manager;

import java.util.List;

import de.mxro.server.ComponentConfiguration;
import de.mxro.server.ComponentContext;
import de.mxro.server.ServerComponent;
import de.mxro.server.ShutdownCallback;
import de.mxro.server.StartCallback;

public interface ComponentManager {

	public ServerComponent addComponent(ComponentContext context,
			ComponentConfiguration conf);

	public ServerComponent addRunningComponent(ComponentContext context,
			ServerComponent component);

	/**
	 * Adding a component at a specific index.
	 * 
	 * @param index
	 * @param conf
	 */
	public ServerComponent addComponent(int index, ComponentContext context,
			ComponentConfiguration conf);

	public void startComponent(String componentId, StartCallback callback);

	public void stopComponent(String componentId, ShutdownCallback callback);

	/**
	 * 
	 * @param componentId
	 * @return The index of the removed component in the component list.
	 */
	public int removeComponent(String componentId);

	public ServerComponent getComponent(String componentId);

	public List<ServerComponent> getComponents();

}
