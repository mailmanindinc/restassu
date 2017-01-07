package com.mailman.test;

import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static io.restassured.RestAssured.*;

public class Testmethodone {

	@BeforeClass
	public static void setUp() throws Exception {
		RestAssured.baseURI = "http://jsonplaceholder.typicode.com";
		RestAssured.port = 80;
		RestAssured.basePath = "/posts";
	}

	@Test
	public void simpleJSONAndHamcrestMatcher() throws Exception {
		Response resp = given().relaxedHTTPSValidation().when().get().then()
				.extract().response();
		// System.out.println(resp.asString());
	}

	@Test
	public void simpleJSONParam() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("userId", "1");

		@SuppressWarnings("deprecation")
		Response resp = given().parameters(parameters).relaxedHTTPSValidation()
				.when().get().then().extract().response();
		System.out.println(resp.asString());
	}

	@Test
	public void getCompare() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("id", "1");

		given().parameters(parameters).when().get().then().log().body()
				.body("[0].id", equalTo(1));
	}

	@Test
	public void getCompare1() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("id", "1");

		given().parameters(parameters).when().get().then().log().body()
				.body("[0].id", new ResponseAwareMatcher<Response>() {
					public Matcher<?> matcher(Response response) {
						return equalTo(response.path("[0].id"));
					}
				});
	}

}
