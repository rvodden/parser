package com.vodden.math.parser.service.impl;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.vodden.math.parser.Parser;
import com.vodden.math.parser.domain.ParseRequest;
import com.vodden.math.parser.domain.ParseResponse;
import com.vodden.math.parser.service.ParserService;

@Singleton
public class ParserServiceImpl implements ParserService {

	Logger logger = Logger.getLogger(ParserServiceImpl.class.getName());
	
	private Parser parser;

	@Inject
	public ParserServiceImpl(Parser parser) {
		logger.info("Creating ParserServiceImpl");
		this.parser = parser;
	}

	@Override
	public ParseResponse parse(ParseRequest parseRequest) {
		String expression = parseRequest.getExpression();
		logger.info("Parsing expression : " + expression);
		
		ParseResponse parseResponse = new ParseResponse();
		
		parseResponse.setResult(parser.calculate(parseRequest.getExpression()));
		return parseResponse;
	}
	
	@Override
	public String happyBeep() {
		return "Beep!";
	}

}
