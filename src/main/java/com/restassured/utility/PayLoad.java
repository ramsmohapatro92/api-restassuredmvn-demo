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

//	public static String UpdatePlace() {
//		return "{\r\n" + "			\"place_id\":\""+getcallobj+""",\r\n"
//				+ "			\"address\":\"301 Sumangali square\",\r\n" + "			\"key\":\"qaclick123\"\r\n"
//				+ "			} ";

}
