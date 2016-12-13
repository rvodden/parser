package com.vodden.math.parser;

import org.junit.Test;

import com.vodden.math.parser.ParserImpl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ParserImplTest {
	
	ParserImpl parserImpl;

	@Test
	public void testThatPositiveIntegersWork() {
		parserImpl = new ParserImpl();
		
		assertThat(parserImpl.calculate("5"), is(equalTo(5.0d)));
		assertThat(parserImpl.calculate("6"), is(equalTo(6.0d)));
		assertThat(parserImpl.calculate("11"), is(equalTo(11.0d)));
		assertThat(parserImpl.calculate("123"), is(equalTo(123.0d)));
		assertThat(parserImpl.calculate("452"), is(equalTo(452.0d)));
		assertThat(parserImpl.calculate("12"), is(equalTo(12.0d)));

	}

}
