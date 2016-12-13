package com.vodden.math.parser;

import org.junit.runner.RunWith;

import net.serenitybdd.cucumber.CucumberWithSerenity;

import cucumber.api.CucumberOptions;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "classpath:features", glue = "com.vodden.math.parser.steps")
public class ParserImplTest {

}
