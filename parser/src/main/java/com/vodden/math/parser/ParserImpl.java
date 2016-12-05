package com.vodden.math.parser;

import java.util.logging.Logger;

public class ParserImpl implements Parser {
	
	static final Logger logger = Logger.getLogger(ParserImpl.class.getName());
	
	public ParserImpl() {
		logger.info("Creating ParserImpl");
	}

	@Override
	public Double calculate(String expression) {
		return Double.parseDouble(expression);
	}

}
