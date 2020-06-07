package com.api.qa.utils;
import org.apache.commons.lang3.RandomStringUtils;

public class RestUtil {
	
	public static String eName() {
		String generatedString = RandomStringUtils.randomAlphabetic(1);
		return ("Sasi"+generatedString);
	}
	
	public static String eSalary() {
		String generatedString = RandomStringUtils.randomNumeric(5);
		return (generatedString);
	}
	
	public static String eAge() {
		String generatedString = RandomStringUtils.randomNumeric(2);
		return (generatedString);
	}

}
