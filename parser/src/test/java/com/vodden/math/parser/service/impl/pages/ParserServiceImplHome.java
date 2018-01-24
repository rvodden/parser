package com.vodden.math.parser.service.impl.pages;

import java.math.BigDecimal;

import com.vodden.math.parser.Parser;
import com.vodden.math.parser.acceptance.pages.Home;
import com.vodden.math.parser.domain.ParseRequest;
import com.vodden.math.parser.service.impl.ParserServiceImpl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParserServiceImplHome implements Home {
	
	Parser parser;
	String expression;

	@Override
	public void open() {
		parser = mock(Parser.class);
	}

	@Override
	public void calculate(String expression) {
		this.expression = expression;
	}

	@Override
	public Boolean checkResult(Double value) {
		when(parser.calculate(expression)).thenReturn(value);
		
		ParserServiceImpl parserServiceImpl = new ParserServiceImpl(parser);
		ParseRequest parseRequest = new ParseRequest();
		parseRequest.setExpression(expression);
		
		Double result = parserServiceImpl.parse(parseRequest).getResult();
		return BigDecimal.valueOf(value).equals(BigDecimal.valueOf(result));
	}

	@Override
	public Boolean findError(String message) {
		// TODO Auto-generated method stub
		return null;
	}

}
