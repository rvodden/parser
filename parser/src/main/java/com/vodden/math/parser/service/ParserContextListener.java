package com.vodden.math.parser.service;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.vodden.math.parser.Parser;
import com.vodden.math.parser.ParserImpl;
import com.vodden.math.parser.service.impl.ParserServiceImpl;

public class ParserContextListener extends GuiceServletContextListener {
	
	static final Logger logger = Logger.getLogger(ParserContextListener.class.getName());

	@Override
	protected Injector getInjector() {
		logger.info("Providing Injector");
		return Guice.createInjector(Stage.PRODUCTION, new ParserServletModule());
	}
	
	private class ParserServletModule extends ServletModule {
		@Override
        protected void configureServlets() {
			logger.info("Configuring Servlets");
			super.configureServlets();

            HashMap <String, String> options = new HashMap<>();
            options.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
            options.put("jersey.config.server.provider.packages", "com.vodden.math.parser.service.impl");
            serve("/*").with(GuiceContainer.class,options);
            
            bind(ParserService.class).to(ParserServiceImpl.class);
            bind(Parser.class).to(ParserImpl.class);
            
            configureLogging();
		}
		
	}
	
	private void configureLogging() {
		logger.info("Setting up root logger to log fine messages");
		Logger rootLog = Logger.getLogger("");
		rootLog.setLevel( Level.FINE );
		rootLog.getHandlers()[0].setLevel( Level.FINE );
		
		logger.fine("Fine logging is working now");
	}

}
