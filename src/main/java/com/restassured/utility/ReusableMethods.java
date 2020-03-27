package com.restassured.utility;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {
	
	public static JsonPath jsonPathinitiator(String response)
	{
		JsonPath js=new JsonPath(response);
		return js;
	}

}
