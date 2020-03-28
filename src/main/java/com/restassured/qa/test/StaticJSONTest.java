package com.restassured.qa.test;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.restassured.utility.PayLoad;
import com.restassured.utility.ReusableMethods;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class StaticJSONTest {
	
	//To read static JSON input from an external file
	
	@Test()
	public void addBook() throws IOException {
		RestAssured.baseURI = "http://216.10.245.166";
		String addbookresponse = given().log().all().header("Content-Type", "application/json")
				.body(generateStringfromFile(".\\ReadJSONfromexternal.json")).when().post("Library/Addbook.php").then().log()
				.all().assertThat().statusCode(200).extract().response().asString();

		JsonPath addbookresponseJS = ReusableMethods.jsonPathinitiator(addbookresponse);
		Assert.assertEquals("successfully added", addbookresponseJS.get("Msg"));

		String bookId = addbookresponseJS.get("ID");
		System.out.println(bookId);

	}

	public String generateStringfromFile(String path) throws IOException {
		
		return new String(Files.readAllBytes(Paths.get(path)));
	}

}
