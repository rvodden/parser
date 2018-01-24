package com.vodden.math.parser;

import com.google.inject.AbstractModule;
import com.vodden.math.parser.acceptance.pages.Home;
import com.vodden.math.parser.service.impl.listener.pages.ParserContextListenerHome;

import cucumber.runtime.java.guice.ScenarioScoped;

public class ParserModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Home.class).to(ParserContextListenerHome.class).in(ScenarioScoped.class);
	}

}
