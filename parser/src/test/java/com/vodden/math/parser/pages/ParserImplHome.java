package com.vodden.math.parser.pages;

import java.math.BigDecimal;

import com.vodden.math.parser.Parser;
import com.vodden.math.parser.ParserImpl;
import com.vodden.math.parser.acceptance.pages.Home;

public class ParserImplHome implements Home {

	Double result;
	
	@Override
	public void open() {
		// Nothing to do, API level test
	}

	@Override
	public void calculate(String expression) {		
		Parser parserImpl = new ParserImpl();
		result = parserImpl.calculate(expression);
	}

	@Override
	public Boolean checkResult(Double value) {
		return BigDecimal.valueOf(value).equals(BigDecimal.valueOf(result));
	}

	@Override
	public Boolean findError(String message) {
		// TODO Auto-generated method stub
		return null;
	}

}
