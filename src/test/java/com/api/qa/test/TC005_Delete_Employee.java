package com.api.qa.test;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.qa.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_Delete_Employee extends TestBase {
	
	public static RequestSpecification httpRequest;
	public static Response response;
	
	@BeforeClass
	public void deleteEmployee() throws InterruptedException {
		
		logger.info("*************Started TC_005_Delete_Single_Employee*************");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		
		response = httpRequest.request(Method.GET, "/employees");
		
		JsonPath jsonPathEvaluator = response.jsonPath();
		//System.out.println(response.asString());
		String empId = jsonPathEvaluator.get("data[0].id");
		response = httpRequest.request(Method.DELETE, "/delete/"+empId);
		Thread.sleep(20);	
	}
	
	@Test
	public void validateResponseBody() {
		logger.info("*************Checkign Response Body*************");
		String responseBody = response.getBody().asString();
		System.out.println(responseBody);
		Assert.assertEquals(responseBody.contains("successfully! deleted Records"), true);
		
	}
	
	@Test
	public void validateStatusCode() {
		logger.info("*************Checkign Status Code*************");
		int statusCode = response.statusCode();
		logger.info("Status Code =>"+statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	public void validateStatusLine() {
		logger.info("*************Checkign Status Line*************");
		String statusLine = response.getStatusLine();
		logger.info("status Line => "+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test
	public void validateContentType() {
		logger.info("*************Checking Content type*************");
		String contentType = response.contentType();
		logger.info("content type => "+contentType);
		Assert.assertEquals(contentType, "application/json;charset=utf-8");
	}
	
	@AfterClass
	public void tearDown() {
		logger.info("*************Finished TC_005_Delete_Employees_Test*************");
	}

}
