package com.mailman.test.restassu.steps;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import static org.junit.Assert.assertEquals;

import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class PostsSteps {
	private Response response;
	Map<String, String> jsonAsMap;
	
	@BeforeStory
	public void setupStory() {
		RestAssured.baseURI = "http://jsonplaceholder.typicode.com";
		RestAssured.port = 80;
		RestAssured.basePath = "/posts";
	}
	
	@Given("Data for Posts")
	public void shoppingForSomethingOnEtsyDotCom() {

		jsonAsMap = new HashMap<String, String>();
		jsonAsMap.put("title", RandomStringUtils.randomAlphabetic(10));
		jsonAsMap.put("body", RandomStringUtils.randomAlphanumeric(40));
		jsonAsMap.put("userId", "1");
	}

	@When("Posts are created")
	public void putThingInCart() {
		response = given().contentType("application/json")
				.content(jsonAsMap).log().body()
						.when().post().then().extract().response();
	}

	@Then("Validate the cretaed posts")
	public void cartHasThatItem() {
		JsonPath jp = new JsonPath(response.asString());
        assertEquals(jp.get("title"), jsonAsMap.get("title"));
	}
}
