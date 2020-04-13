package com.restassured.JIRA.test;

public class JiraPayLoad {

	public static String JiraCreateSessionPayload() {

		String createSessionPayload = "{ \"username\": \"Admin92\", \"password\": \"Jira@123\" }";
		return createSessionPayload;
	}

	public static String JiraCreateIssuePayload() {
		String createIssuePayload = "{\r\n" + "    \"fields\": {\r\n" + "       \"project\":\r\n" + "       {\r\n"
				+ "          \"key\": \"JIRARES\"\r\n" + "       },\r\n"
				+ "       \"summary\": \"New CASATD:Issue Created to update comment and Attachment for Rest Assured\",\r\n"
				+ "       \"description\": \"New CASATD Product: UI issue practical from Rest Assured\",\r\n"
				+ "       \"issuetype\": {\r\n" + "          \"name\": \"Bug\"\r\n" + "       }\r\n" + "   }\r\n" + "}";
		return createIssuePayload;
	}

	public static String JiraAddCommentPayload() {
		String addCommentPayload = "{\r\n"
				+ "    \"body\": \"Initial added comment by Ram from Rest Assured and Attachment added\",\r\n"
				+ "    \"visibility\": {\r\n" + "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n" + "    }\r\n" + "}";
		return addCommentPayload;
	}
}
