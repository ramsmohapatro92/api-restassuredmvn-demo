package com.restassured.qa.test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;
import org.testng.*;

import com.restassured.utility.PayLoad;
import com.restassured.utility.ReusableMethods;

public class GetCallBDD {

	@Test
	public static void validateRequestResponse() {
		
		//validate if add place API working
				//Rest aasured: 
				//Given: all input details are given to the API
				//when: Submit the API: Resource and http method 
				//then: Validate the response
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		String post_response= given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(PayLoad.AddPlace()).when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		//Post assertThat(), header() is used to verify the actual and expected values
		//log().all(): 
		
		System.out.println(post_response);
		
		//now to get the place_id from the response JSON, We need to use JSONPATH class object, it will take string as input
		//How to retrive the place id: 
		JsonPath jsdetails=ReusableMethods.jsonPathinitiator(post_response);
		String place_id=jsdetails.get("place_id");
		System.out.println("Place id is: "+place_id);
		
		
		//Add a place->update place with new address->Get place to validate if new address is present in response
		//Add place(Post)->Update address(Put)->Get the new adress(Get)
		//Place id to be retrived from Post call and then used subsequently used in Put and Get call
		
		//Update address using the existing place id: PUT operation.
		//given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json") ostly same for POST and PUT call
		//"+place_id+"
		
		String newAddress="301 Sumangali square 1 24/25";
		
		String put_response=given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+place_id+"\",\r\n" + 
				"\"address\":\""+newAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}").when().put("maps/api/place/update/json").then().assertThat().log().all().statusCode(200)
		.body("msg",equalToIgnoringCase("Address successfully updated")).extract().asString();
		
		
		//Verify the updated address using the available place id using : Get Operation
		//in GET call we are not sending any body so no need to mention "header"
		//for multiple query parameter just repeat the method queryParam(String) again.
		
		String get_response=given().log().all().queryParam("key", "qaclick123").queryParam("place_id", place_id)
				.when().get("/maps/api/place/get/json")
				.then().assertThat().log().all().statusCode(200)
				.extract().asString();
		
		JsonPath getjsonobj=new JsonPath(get_response);
		String actual_address=getjsonobj.get("address");
		Assert.assertEquals(newAddress, actual_address, "Address Matched");
	}

}
