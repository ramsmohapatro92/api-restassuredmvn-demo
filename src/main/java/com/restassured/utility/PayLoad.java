package com.restassured.utility;

import com.restassured.qa.test.GetCallBDD;

public class PayLoad {

	public static String AddPlace() {
		return "{\r\n" + "  \"location\": {\r\n" + "    \"lat\": -38.383494,\r\n" + "    \"lng\": 33.427362\r\n"
				+ "  },\r\n" + "  \"accuracy\": 50,\r\n" + "  \"name\": \"Ram shankar mohapatro House-2\",\r\n"
				+ "  \"phone_number\": \"(+91) 984 049 5772\",\r\n"
				+ "  \"address\": \"29, Saroj meadows side layout, BLR 09\",\r\n" + "  \"types\": [\r\n"
				+ "    \"Resedency park\",\r\n" + "    \"House\"\r\n" + "  ],\r\n"
				+ "  \"website\": \"http://ramshankar.com\",\r\n"
				+ "  \"language\": \"Odia Hindi  Tamil English-IN\"\r\n" + "}";
	}

	public static String CoursePrice()
	{
		return("{\r\n" + 
				"\r\n" + 
				"\"dashboard\": {\r\n" + 
				"\r\n" + 
				"\"purchaseAmount\": 16860,\r\n" + 
				"\r\n" + 
				"\"website\": \"rahulshettyacademy.com\"\r\n" + 
				"\r\n" + 
				"},\r\n" + 
				"\r\n" + 
				"\"courses\": [\r\n" + 
				"\r\n" + 
				"{\r\n" + 
				"\r\n" + 
				"\"title\": \"Selenium Python\",\r\n" + 
				"\r\n" + 
				"\"price\": 50,\r\n" + 
				"\r\n" + 
				"\"copies\": 6\r\n" + 
				"\r\n" + 
				"},\r\n" + 
				"\r\n" + 
				"{\r\n" + 
				"\r\n" + 
				"\"title\": \"Cypress\",\r\n" + 
				"\r\n" + 
				"\"price\": 40,\r\n" + 
				"\r\n" + 
				"\"copies\": 4\r\n" + 
				"\r\n" + 
				"},\r\n" + 
				"\r\n" + 
				"{\r\n" + 
				"\r\n" + 
				"\"title\": \"RPA\",\r\n" + 
				"\r\n" + 
				"\"price\": 45,\r\n" + 
				"\r\n" + 
				"\"copies\": 10\r\n" + 
				"\r\n" + 
				"},\r\n" + 
				"{\r\n" + 
				"\r\n" + 
				"\"title\": \"Appium\",\r\n" + 
				"\r\n" + 
				"\"price\": 145,\r\n" + 
				"\r\n" + 
				"\"copies\": 110\r\n" + 
				"\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"]\r\n" + 
				"\r\n" + 
				"}");
	}
	
	public static String AddBook(String bookname,String isbn, String aisle)
	{
		String AddBookRequest="{\r\n" + 
				"\r\n" + 
				"\"name\":\""+bookname+"\",\r\n" + 
				"\"isbn\":\""+isbn+"\",\r\n" + 
				"\"aisle\":\""+aisle+"\",\r\n" + 
				"\"author\":\"Test Author Ram\"\r\n" + 
				"}";
		
		return AddBookRequest;
	}
	public static String deletebook(String isbn)
	{
		
		String DeleteBookRequest="{\r\n" + 
				"{\r\n" + 
				" \r\n" + 
				"\"ID\" : \""+isbn+"\"\r\n" + 
				" \r\n" + 
				"} \r\n" + 
				"";
		return DeleteBookRequest;
	}
}
