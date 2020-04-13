package com.restassured.JIRA.test;

import org.testng.annotations.Test;

import com.restassured.utility.ReusableMethods;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class JiraApiTest {

	static String sessionID;
	static String sessionKey;
	static String cookie_value;
	static String defect_id;
	static SessionFilter session_id; /*
										 * Alternative to JSONPath where we are manually picking up the cookie values to
										 * be validated through out our execution
										 */

	@Test(priority = 1)
	public static void createSession() {
		RestAssured.baseURI = "http://localhost:8090";
		session_id = new SessionFilter();

		String JiraCreateSessionResponse = given().header("Content-Type", "application/json").log().all()
				.filter(session_id).body(JiraPayLoad.JiraCreateSessionPayload()).when().post("/rest/auth/1/session")
				.then().assertThat().statusCode(200).extract().response().asString();

		JsonPath js = ReusableMethods.jsonPathinitiator(JiraCreateSessionResponse);
		sessionID = js.get("session.value");
		sessionKey = js.get("session.name");
		cookie_value = sessionKey + "=" + sessionID;
		System.out.println(cookie_value);

	}

	@Test(dependsOnMethods = "createSession", priority = 2)
	public static void createIssue() {
		RestAssured.baseURI = "http://localhost:8090";

		String JiraCreateIssueResponse = given().header("Content-Type", "application/json").filter(session_id)
				.body(JiraPayLoad.JiraCreateIssuePayload()).log().all().when().post("/rest/api/2/issue/").then()
				.assertThat().statusCode(201).extract().asString();
		/* header("Cookie", cookie_value) removed filter(session_id) used */

		JsonPath js = ReusableMethods.jsonPathinitiator(JiraCreateIssueResponse);
		defect_id = js.get("id");
		System.out.println(defect_id);

	}

	@Test(dependsOnMethods = "createIssue", priority = 3)
	public static void addComment() {
		RestAssured.baseURI = "http://localhost:8090";

		String JiraAddCommentResponse = given().header("Content-Type", "application/json").filter(session_id)
				.pathParam("key", defect_id).body(JiraPayLoad.JiraAddCommentPayload()).log().all().when()
				.post("http://localhost:8090/rest/api/2/issue/{key}/comment").then().assertThat().statusCode(201)
				.extract().asString();

		/*
		 * Using PathParam function 1. Manual concatenation to be avoided in POST():
		 * "http://localhost:8090/rest/api/2/issue/" + defect_id + "/comment">>>
		 * Replaced with pathParam("key", defect_id) 2. header("Cookie", cookie_value)
		 * removed
		 */
		JsonPath js = ReusableMethods.jsonPathinitiator(JiraAddCommentResponse);
		String commentResponse = js.getString("Body.body");
		System.out.println(commentResponse);
	}

	@Test(dependsOnMethods = "createIssue", priority = 4)
	public static void addAttachment() {
		RestAssured.baseURI = "http://localhost:8090";
		File attachment_doc = new File("JIRAattachment");

		// curl comment available in JIRA documentation:curl -D- -u admin:admin -X POST
		// -H "X-Atlassian-Token: no-check" -F "file=@myfile.txt"
		// http://myhost/rest/api/2/issue/TEST-123/attachments

		String JiraAddAttachmentResponse = given().header("X-Atlassian-Token", "no-check").filter(session_id)
				.pathParam("key", defect_id).header("Content-Type", "multipart/form-data")
				.multiPart("file", attachment_doc).when().post("/rest/api/2/issue/{key}/attachments").then().log().all()
				.assertThat().statusCode(200).extract().asString();

		JsonPath js = ReusableMethods.jsonPathinitiator(JiraAddAttachmentResponse);
		String fileAttached = js.getString("filename");
		System.out.println(fileAttached);

	}
}
