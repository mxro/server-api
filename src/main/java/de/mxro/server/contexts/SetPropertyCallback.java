package de.mxro.server.contexts;

public interface SetPropertyCallback {

	public void onPropertySet();

	public void onFailure(Throwable t);

}
