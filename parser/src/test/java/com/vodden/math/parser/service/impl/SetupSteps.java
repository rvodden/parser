package com.vodden.math.parser.service.impl;

import java.util.EnumSet;

import javax.servlet.ServletContextListener;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

import com.google.inject.servlet.GuiceFilter;
import com.vodden.math.parser.service.ParserContextListener;

import cucumber.api.java.Before;

public class SetupSteps {
	
	private static Server server;
	ServletContextListener servletContextListener;
	
	//@Before
	public void setupTestEnvironment() throws Exception {
		if ( server == null ) {
			startServer();
			
			Runtime.getRuntime().addShutdownHook(new Thread() {
	            public void run() {
	                try {
						stopServer();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        });
		}
	}
	
	public static void startServer() throws Exception {
		
		System.setProperty("java.naming.factory.url.pkgs","org.eclipse.jetty.jndi");
		System.setProperty("java.naming.factory.initial","org.eclipse.jetty.jndi.InitialContextFactory");
		
		server = new Server(8080); // don't specify port, it will be assigned one.
		
		ServletContextHandler servletContextHandler = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
		servletContextHandler.addEventListener(new ParserContextListener());
		servletContextHandler.addFilter(GuiceFilter.class, "/*", EnumSet.of(javax.servlet.DispatcherType.REQUEST, javax.servlet.DispatcherType.ASYNC));
	        // DefaultServlet is at the end of the pipeline to handle what Guice didn't
		servletContextHandler.addServlet(DefaultServlet.class, "/");
		
		server.setHandler(servletContextHandler);
		
		server.start();
	}
	
	public static void stopServer() throws Exception {
		server.stop();
		server.destroy();
	}

}
