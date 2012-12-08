package de.mxro.server.contexts;

public interface GetPropertyCallback {

	public void onPropertyRetrieved(Object value);

	public void onPropertyDoesNotExist();

	public void onFailure(Throwable t);

}
