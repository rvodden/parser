package com.vodden.math.parser.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.vodden.math.parser.Parser;
import com.vodden.math.parser.domain.ParseRequest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ParserServiceImplTest {
	
	ParserServiceImpl parserServiceImpl;
	
	@Mock
	Parser parser;
	
	@Test
	public void testThatPositiveIntegersWork() {
		
		ParseRequest parseRequest = new ParseRequest();
		
		when(parser.calculate("5")).thenReturn(5.0d);
		when(parser.calculate("6")).thenReturn(6.0d);
		when(parser.calculate("11")).thenReturn(11.0d);
		when(parser.calculate("123")).thenReturn(123.0d);
		when(parser.calculate("452")).thenReturn(452.0d);
		when(parser.calculate("12")).thenReturn(12.0d);
		
		parserServiceImpl = new ParserServiceImpl(parser);
		
		parseRequest.setExpression("5");
		assertThat(parserServiceImpl.parse(parseRequest).getResult(),is(equalTo(5.0d)));
		
		parseRequest.setExpression("6");
		assertThat(parserServiceImpl.parse(parseRequest).getResult(),is(equalTo(6.0d)));
		
		parseRequest.setExpression("11");
		assertThat(parserServiceImpl.parse(parseRequest).getResult(),is(equalTo(11.0d)));
		
		parseRequest.setExpression("123");
		assertThat(parserServiceImpl.parse(parseRequest).getResult(),is(equalTo(123.0d)));
		
		parseRequest.setExpression("452");
		assertThat(parserServiceImpl.parse(parseRequest).getResult(),is(equalTo(452.0d)));
		
		parseRequest.setExpression("12");
		assertThat(parserServiceImpl.parse(parseRequest).getResult(),is(equalTo(12.0d)));
		
	}
	
	@Test
	public void testHappyBeep() {
		parserServiceImpl = new ParserServiceImpl(parser);
		
		assertThat(parserServiceImpl.happyBeep(), is(equalTo("Beep!")));
	}
	

}
