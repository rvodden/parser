package com.vodden.math.parser;

import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;

import cucumber.api.guice.CucumberModules;
import cucumber.runtime.java.guice.InjectorSource;

public class ParserInjectorSource implements InjectorSource {

	@Override
	public Injector getInjector() {
		//GuiceDebug.enable();
		Injector injector = Guice.createInjector(Stage.PRODUCTION, CucumberModules.SCENARIO, new ParserModule());
		return injector;
	}
	
	public static class GuiceDebug {
	    private static final Handler HANDLER;
	    static {
	        HANDLER = new StreamHandler(System.out, new Formatter() {
	            public String format(LogRecord record) {
	                return String.format("%s: [Guice] %s%n",
	                                  record.getLevel().getName(),
	                                  record.getMessage());
	            }
	        });
	        HANDLER.setLevel(Level.ALL);
	        enable();
	    }

	    private GuiceDebug() {}

	    public static Logger getLogger() {
	        return Logger.getLogger("com.google.inject");
	    }

	    public static void enable() {
	        Logger guiceLogger = getLogger();
	        guiceLogger.addHandler(GuiceDebug.HANDLER);
	        guiceLogger.setLevel(Level.ALL);
	    }

	    public static void disable() {
	        Logger guiceLogger = getLogger();
	        guiceLogger.setLevel(Level.OFF);
	        guiceLogger.removeHandler(GuiceDebug.HANDLER);
	    }
	}

}
