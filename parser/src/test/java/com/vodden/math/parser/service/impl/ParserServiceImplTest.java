package com.vodden.math.parser.service.impl;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", glue = "com.vodden.math.parser.acceptance.steps")
public class ParserServiceImplTest {
	/*
	@Test
	public void testHappyBeep() {
		parserServiceImpl = new ParserServiceImpl(parser);
		
		assertThat(parserServiceImpl.happyBeep(), is(equalTo("Beep!")));
	}
	*/

}
