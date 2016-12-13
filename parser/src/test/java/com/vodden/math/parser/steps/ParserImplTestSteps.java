package com.vodden.math.parser.steps;

import com.vodden.math.parser.Parser;
import com.vodden.math.parser.ParserImpl;
import com.vodden.math.parser.acceptance.steps.ParserSteps;

import net.serenitybdd.core.Serenity;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ParserImplTestSteps implements ParserSteps {

	@Override
	@Given("I visit the webpage")
	public void theUserVisitsTheWebpage() {
		// Nothing to do here, this is just an API level test
		
	}

	@Override
	@When("I submit the expression (.*)")
	public void theySubmitTheExpression(String expression) {
		// TODO: find a way to share the Parser across tests
		Parser parserImpl = new ParserImpl();
		Double result = parserImpl.calculate(expression);
		Serenity.getCurrentSession().put("result", result); // stash the result away so it can be grabbed at the next step
		
	}

	@Override
	@Then("the result should be (\\d+)")
	public void theResultShouldBe(Double value) {
		Double result = (Double) Serenity.getCurrentSession().get("result"); // unstash the result
		assertThat(result, is(equalTo(value)));
	}

}
