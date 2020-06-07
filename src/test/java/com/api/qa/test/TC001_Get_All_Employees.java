package com.api.qa.test;


import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.qa.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_Get_All_Employees extends TestBase {
	
	public static RequestSpecification httpRequest;
	public static Response response;
	@BeforeClass
	public void getAllEmployees() {
		logger.info("*************Started TC_001_Get_All_Employees*************");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employees");
	}
	
	@Test
	public void validateResponseBody() {
		logger.info("*************Checkign Response Body*************");
		String responseBody = response.getBody().toString();
		logger.info("Response Body =>"+responseBody);
		Assert.assertTrue(responseBody!=null);
	}
	
	@Test
	public void validateStatusCode() {
		logger.info("*************Checkign Status Code*************");
		int statusCode = response.statusCode();
		logger.info("Status Code =>"+statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	public void validateResponseTime() {
		logger.info("*************Checkign Response Time*************");
		long responseTime = response.getTime();
		logger.info("Response time"+responseTime);
		if(responseTime>4000) {
			logger.warn("Response time is greater than 4000");
		}
		Assert.assertTrue(responseTime<4000);
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
		logger.info("*************Finished TC_001_All_Employees_Test*************");
	}
}
