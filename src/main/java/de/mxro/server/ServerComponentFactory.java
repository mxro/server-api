package de.mxro.server;

/**
 * A factory for a {@link ServerComponent}s.
 * 
 * @author Max
 * 
 */
public interface ServerComponentFactory<Comp extends ServerComponent, Conf extends ComponentConfiguration> {

	/**
	 * Create a new component and initialize it with the provided configruation.
	 * 
	 * @param conf
	 * @return
	 */
	public Comp init(Conf conf);

}
