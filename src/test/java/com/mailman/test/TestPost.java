/**
 * 
 */
package com.mailman.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author MALLIKARJUNA
 *
 */
public class TestPost {

	@BeforeClass
    public static void setUp() throws Exception {
		RestAssured.baseURI = "http://jsonplaceholder.typicode.com";
		RestAssured.port = 80;
		RestAssured.basePath = "/posts";
    }
	
	@Test
	public void pathJSONParam() throws Exception {
		Map<String, String> jsonAsMap = new HashMap<String, String>();
		jsonAsMap.put("title", RandomStringUtils.randomAlphabetic(10));
		jsonAsMap.put("body", RandomStringUtils.randomAlphanumeric(40));
		jsonAsMap.put("username", RandomStringUtils.randomAlphabetic(15));
		jsonAsMap.put("id", RandomStringUtils.randomNumeric(10));


		given().contentType("application/json")
		.content(jsonAsMap).log().body()
				//.when().post("/posts/1").then().
		.when().post().then().
		        extract().response();
    }
	
	@Test
	public void pathJSONParamValidate() throws Exception {
		Map<String, String> jsonAsMap = new HashMap<String, String>();
		jsonAsMap.put("title", RandomStringUtils.randomAlphabetic(10));
		jsonAsMap.put("body", RandomStringUtils.randomAlphanumeric(40));
		jsonAsMap.put("username", RandomStringUtils.randomAlphabetic(15));


		given().contentType("application/json")
		.content(jsonAsMap)
		.when().post().then().log().body().body("title", equalTo(jsonAsMap.get("title")));
		       
    }



}
