package de.mxro.server.internal;

import de.mxro.factories.Configuration;
import de.mxro.factories.Dependencies;
import de.mxro.factories.Factory;
import de.mxro.server.contexts.LocalStatefulContextConfiguration;

public class LocalStatefulContextFactory
		implements
		Factory<LocalStatefulContext, LocalStatefulContextConfiguration, Dependencies> {

	@Override
	public boolean canInstantiate(Configuration conf) {
		return conf instanceof LocalStatefulContextConfiguration;
	}

	@Override
	public LocalStatefulContext create(LocalStatefulContextConfiguration conf,
			Dependencies dependencies) {
		return new LocalStatefulContext();
	}

}
