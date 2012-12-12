package de.mxro.server;

public interface ComponentManager {

	public void addComponent(ComponentConfiguration comp);

	public void startComponent(String componentId, StartCallback callback);

	public void stopComponent(String componentId, ShutdownCallback callback);

	public void removeComponent(String componentId);

	public ServerComponent getComponent(String componentId);
	
	public 

	

}
