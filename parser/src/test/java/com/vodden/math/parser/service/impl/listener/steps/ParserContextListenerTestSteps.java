package com.vodden.math.parser.service.impl.listener.steps;

import java.util.logging.Logger;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.JsonConfig;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.config.JsonPathConfig.NumberReturnType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.vodden.math.parser.acceptance.steps.ParserSteps;

import net.serenitybdd.core.Serenity;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class ParserContextListenerTestSteps implements ParserSteps {
	
	Logger logger = Logger.getLogger(ParserContextListenerTestSteps.class.getName());
	
	final static String URL = "http://localhost:8080/parser/";
	
	public static RequestSpecification setupRestAssured() {
		return given().config(RestAssured.config().jsonConfig(JsonConfig.jsonConfig().numberReturnType(NumberReturnType.DOUBLE)));
	}
	
	@Override
	@Given("I visit the webpage")
	public void theUserVisitsTheWebpage() {
		// Nothing to do here as this is an API level test
	}

	@Override
	@When("I submit the expression (.*)")
	public void theySubmitTheExpression(String expression) {
		 // Call the API here and store the result in the session context.
		Response response = 
				setupRestAssured().		// this is far from idea - would like to do it globally in an @Before
				given().contentType(ContentType.JSON).content("{\"expression\":\"" + expression + "\"}").
				when().post(URL);
		logger.fine(response.toString());
		Serenity.getCurrentSession().put("result", response);
		
	}

	@Override
	@Then("the result should be (\\d+)")
	public void theResultShouldBe(Double value) {
		Response response = (Response) Serenity.getCurrentSession().get("result");
		response.then().statusCode(is(equalTo(200))).and().body("result", equalTo(value));
	}

}
