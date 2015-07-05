package de.mxro.server.components;

import delight.factories.Configuration;
import delight.factories.Factory;

import de.mxro.server.ComponentDependencies;
import de.mxro.server.configuration.v01.LoggingComponentConfiguration;

public class LoggingComponentFactory implements Factory<LoggingComponent, LoggingComponentConfiguration, ComponentDependencies> {

	@Override
	public boolean canInstantiate(Configuration conf) {
		return conf instanceof LoggingComponentConfiguration;
	}

	@Override
	public LoggingComponent create(LoggingComponentConfiguration conf,
			ComponentDependencies dependencies) {
		return new LoggingComponent();
	}

}
