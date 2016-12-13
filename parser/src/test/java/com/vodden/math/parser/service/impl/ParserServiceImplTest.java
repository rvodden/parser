package com.vodden.math.parser.service.impl;

import org.junit.runner.RunWith;

import net.serenitybdd.cucumber.CucumberWithSerenity;

import cucumber.api.CucumberOptions;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "classpath:features", glue = "com.vodden.math.parser.service.impl.steps")
public class ParserServiceImplTest {
	/*
	@Test
	public void testHappyBeep() {
		parserServiceImpl = new ParserServiceImpl(parser);
		
		assertThat(parserServiceImpl.happyBeep(), is(equalTo("Beep!")));
	}
	*/

}
