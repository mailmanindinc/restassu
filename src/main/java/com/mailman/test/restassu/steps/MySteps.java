package com.mailman.test.restassu.steps;

import io.restassured.RestAssured;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class MySteps {

	@Given("I am a test step")
	public void shoppingForSomethingOnEtsyDotCom() {
		RestAssured.baseURI = "http://jsonplaceholder.typicode.com";
		RestAssured.port = 80;
		RestAssured.basePath = "/posts";

		Map<String, String> jsonAsMap = new HashMap<String, String>();
		jsonAsMap.put("title", RandomStringUtils.randomAlphabetic(10));
		jsonAsMap.put("body", RandomStringUtils.randomAlphanumeric(40));
		jsonAsMap.put("userId", "1");

		given().contentType("application/json")
		.content(jsonAsMap).log().body()
				.when().post().then()
				.body("title", equalTo(jsonAsMap.get("title")));
	}

	@When("a good soul will implement me")
	public void putThingInCart() {

	}

	@Then("I shall be happy")
	public void cartHasThatItem() {
		
		
	}
	
	

}
