package com.vodden.math.parser.service.impl.steps;

import com.vodden.math.parser.Parser;
import com.vodden.math.parser.acceptance.steps.ParserSteps;
import com.vodden.math.parser.domain.ParseRequest;
import com.vodden.math.parser.service.impl.ParserServiceImpl;

import net.serenitybdd.core.Serenity;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParserServiceImplTestSteps implements ParserSteps {

	@Override
	@Given("I visit the webpage")
	public void theUserVisitsTheWebpage() {
		Parser parser = mock(Parser.class);
		Serenity.getCurrentSession().put("parser", parser);
	}

	@Override
	@When("I submit the expression (.*)")
	public void theySubmitTheExpression(String expression) {
		Serenity.getCurrentSession().put("expression", expression);
		
	}

	@Override
	@Then("the result should be (\\d+)")
	public void theResultShouldBe(Double value) {
		String expression = (String) Serenity.getCurrentSession().get("expression");
		Parser parser = (Parser) Serenity.getCurrentSession().get("parser");
		
		when(parser.calculate(expression)).thenReturn(value);
		
		ParserServiceImpl parserServiceImpl = new ParserServiceImpl(parser);
		ParseRequest parseRequest = new ParseRequest();
		parseRequest.setExpression(expression);
		
		assertThat(parserServiceImpl.parse(parseRequest).getResult(),is(equalTo(value)));
	}

}
