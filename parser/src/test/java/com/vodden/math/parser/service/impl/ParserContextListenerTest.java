package com.vodden.math.parser.service.impl;

import java.util.EnumSet;
import java.util.logging.Logger;

import javax.servlet.ServletContextListener;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.servlet.GuiceFilter;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.JsonConfig;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.config.JsonPathConfig.NumberReturnType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.vodden.math.parser.service.ParserContextListener;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import static com.jayway.restassured.RestAssured.given;

public class ParserContextListenerTest {
	
	Logger logger = Logger.getLogger(ParserContextListenerTest.class.getName());
	
	private static Server server;
	ServletContextListener servletContextListener;
	
	@BeforeClass
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
	
	public static RequestSpecification setupRestAssured() {
		return given().config(RestAssured.config().jsonConfig(JsonConfig.jsonConfig().numberReturnType(NumberReturnType.DOUBLE)));
	}
	
	@Test
	public void testThatPositiveIntegersWork() {
		//NetworkConnector networkConnector = (NetworkConnector) server.getConnectors()[0];
		//Integer port = networkConnector.getLocalPort();
		String url = "http://localhost:" + "8080";
		RestAssured.put();
		Response response = 
				setupRestAssured().		// this is far from idea - would like to do it globally in an @Before
				given().contentType(ContentType.JSON).content("{\"expression\":\"5\"}").
				when().post(url + "/parser/");
		logger.fine(response.toString());
		
		response.then().statusCode(is(equalTo(200))).and().body("result", equalTo(5.0));
	}
	
	@AfterClass
	public static void stopServer() throws Exception {
		server.stop();
		server.destroy();
	}

}
