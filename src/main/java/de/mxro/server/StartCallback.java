package de.mxro.server;

public interface StartCallback {

	public void onStarted();

	public void onFailure(Throwable t);

}
