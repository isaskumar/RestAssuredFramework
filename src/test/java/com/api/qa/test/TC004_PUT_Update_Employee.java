package com.api.qa.test;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.qa.base.TestBase;
import com.api.qa.utils.RestUtil;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_PUT_Update_Employee extends TestBase {
	
	public static RequestSpecification httpRequest;
	public static Response response;
	public static String empid = "82";
	
	String name = RestUtil.eName();
	String salary = RestUtil.eSalary();
	String age = RestUtil.eAge();
	
	@BeforeClass
	public void createEmployee() throws InterruptedException {
		logger.info("*************Started TC_004_Update_Single_Employee*************");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", name);
		requestParams.put("salary", salary);
		requestParams.put("age", age);
	
		httpRequest.header("Content-Type", "application/json");
		
		httpRequest.body(requestParams.toJSONString());
	
		response = httpRequest.request(Method.PUT, "/update/"+empid);
		Thread.sleep(4000);
	}
	
	@Test
	public void validateResponseBody() {
		logger.info("*************Checkign Response Body*************");
		String responseBody = response.getBody().asString();
		logger.info("Response Body =>"+responseBody);
		System.out.println(responseBody);
		Assert.assertEquals(responseBody.contains(name), true);
		
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
		logger.info("*************Finished TC_004_Update_Employees_Test*************");
	}
}

