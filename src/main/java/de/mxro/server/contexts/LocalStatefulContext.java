package de.mxro.server.contexts;

import java.util.HashMap;
import java.util.Map;

import de.mxro.server.ShutdownCallback;

public class LocalStatefulContext implements StatefulContext {

	private final Map<String, Object> properties;

	@Override
	public void log(final String path, final String title,
			final String message, final LogCallback callback) {
		System.out.println("LOG " + path + "\n" + "    " + title + "\n"
				+ "    " + message);
		callback.onLogged();
	}

	@Override
	public void setProperty(final String path, final Object value,
			final SetPropertyCallback callback) {
		synchronized (properties) {
			properties.put(path, value);
		}
		callback.onPropertySet();
	}

	@Override
	public void getProperty(final String path,
			final GetPropertyCallback callback) {
		synchronized (properties) {
			if (!properties.containsKey(path)) {
				callback.onPropertyDoesNotExist();
				return;
			}
			callback.onPropertyRetrieved(properties.get(path));
			return;
		}
	}

	public LocalStatefulContext() {
		super();
		this.properties = new HashMap<String, Object>();
	}

	@Override
	public void getProperty(final String path, final Object defaultValue,
			final GetPropertyCallback callback) {
		synchronized (properties) {
			if (!properties.containsKey(path)) {
				properties.put(path, defaultValue);
				callback.onPropertyRetrieved(defaultValue);
				return;
			}
			callback.onPropertyRetrieved(properties.get(path));
			return;
		}
	}

	@Override
	public void shutdown(final ShutdownCallback callback) {
		callback.onShutdownComplete();
	}

}
