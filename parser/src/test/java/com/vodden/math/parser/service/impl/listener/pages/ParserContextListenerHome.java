package com.vodden.math.parser.service.impl.listener.pages;

import java.util.logging.Logger;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.JsonConfig;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.config.JsonPathConfig.NumberReturnType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.vodden.math.parser.acceptance.pages.Home;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import static com.jayway.restassured.RestAssured.given;

public class ParserContextListenerHome implements Home {
	
Logger logger = Logger.getLogger(ParserContextListenerHome.class.getName());
	
	final static String URL = "http://localhost:8080/parser/";
	Response response;
	
	@Override
	public void open() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void calculate(String expression) {
		response = 
				setupRestAssured().		// this is far from idea - would like to do it globally in an @Before
				given().contentType(ContentType.JSON).content("{\"expression\":\"" + expression + "\"}").
				when().post(URL);
		logger.fine(response.toString());
	}

	@Override
	public Boolean checkResult(Double value) {
		response.then().statusCode(is(equalTo(200))).and().body("result", equalTo(value));
		return true;
	}

	@Override
	public Boolean findError(String message) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static RequestSpecification setupRestAssured() {
		return given().config(RestAssured.config().jsonConfig(JsonConfig.jsonConfig().numberReturnType(NumberReturnType.DOUBLE)));
	}

}
