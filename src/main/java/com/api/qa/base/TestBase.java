package com.api.qa.base;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;



public class TestBase {

	public Logger logger;
	
	@BeforeClass
	public void setup() {
		logger = Logger.getLogger("EmpRestAPI");
		PropertyConfigurator.configure("log4j.properties");
	}
	

}
