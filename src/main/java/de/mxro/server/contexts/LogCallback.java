package de.mxro.server.contexts;

public interface LogCallback {

	public void onLogged();

	public void onFailure(Throwable t);

}
