package com.restassured.JIRA.test;

import org.testng.annotations.Test;

import com.restassured.utility.ReusableMethods;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class JiraApiTest {

	static String sessionID;
	static String sessionKey;
	static String cookie_value;
	static String defect_id;

	@Test
	public static void createSession() {
		RestAssured.baseURI = "http://localhost:8090";

		String JiraCreateSessionResponse = given().header("Content-Type", "application/json").log().all()
				.body(JiraPayLoad.JiraCreateSessionPayload()).when().post("/rest/auth/1/session").then().assertThat()
				.statusCode(200).extract().response().asString();

		JsonPath js = ReusableMethods.jsonPathinitiator(JiraCreateSessionResponse);
		sessionID = js.get("session.value");
		sessionKey = js.get("session.name");
		cookie_value = sessionKey + "=" + sessionID;
		System.out.println(cookie_value);

	}

	@Test(dependsOnMethods = "createSession", alwaysRun = true)
	public static void createIssue() {
		RestAssured.baseURI = "http://localhost:8090";

		String JiraCreateIssueResponse = given().header("Content-Type", "application/json")
				.header("Cookie", cookie_value).body(JiraPayLoad.JiraCreateIssuePayload()).log().all().when()
				.post("/rest/api/2/issue/").then().assertThat().statusCode(201).extract().asString();

		JsonPath js = ReusableMethods.jsonPathinitiator(JiraCreateIssueResponse);
		defect_id = js.get("id");
		System.out.println(defect_id);

	}

	@Test(dependsOnMethods = "createIssue", alwaysRun = true)
	public static void addComment() {
		RestAssured.baseURI = "http://localhost:8090";

		String JiraAddCommentResponse = given().header("Content-Type", "application/json")
				.header("Cookie", cookie_value).body(JiraPayLoad.JiraAddCommentPayload()).log().all().when()
				.post("http://localhost:8090/rest/api/2/issue/" + defect_id + "/comment").then().assertThat()
				.statusCode(201).extract().asString();
		
		//Using PathParam function
		
//		String JiraAddCommentResponse1 = given().pathParam("{key}", cookie_value).header("Content-Type", "application/json")
//				.header("Cookie", cookie_value).body(JiraPayLoad.JiraAddCommentPayload()).log().all().when()
//				.post("http://localhost:8090/rest/api/2/issue/{key}/comment").then().assertThat()
//				.statusCode(201).extract().asString();

		JsonPath js = ReusableMethods.jsonPathinitiator(JiraAddCommentResponse);
		String commentResponse = js.get("Body.body");
		System.out.println(commentResponse);
	}

}
