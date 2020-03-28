package com.restassured.qa.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.restassured.utility.PayLoad;
import com.restassured.utility.ReusableMethods;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJsonTest {

	// To Add a book
	// 1. Dynamically build JSON payload with external data Input
	@Test(dataProvider = "AddBooksData")
	public void addBook(String bookname, String bookid, String bookaisle) {
		RestAssured.baseURI = "http://216.10.245.166";
		String addbookresponse = given().log().all().header("Content-Type", "application/json")
				.body(PayLoad.AddBook(bookname, bookid, bookaisle)).when().post("Library/Addbook.php").then().log()
				.all().assertThat().statusCode(200).extract().response().asString();

		JsonPath addbookresponseJS = ReusableMethods.jsonPathinitiator(addbookresponse);
		Assert.assertEquals("successfully added", addbookresponseJS.get("Msg"));

		String bookId = addbookresponseJS.get("ID");
		System.out.println(bookId);

	}

	// DataProvider to supply data dynamically to @Test Method

	@DataProvider(name = "AddBooksData")
	public Object[][] getDataforAddBook() {
		return new Object[][] { { "Dark Knight4", "ISBN-Test API with REST-04", "aisle-004" },
				{ "Dark Knight5", "ISBN-Test API with REST-05", "aisle-005" },
				{ "Dark Knight6", "ISBN-Test API with REST-06", "aisle-006" } };
	}

	// To refresh the same book, We need to delete ity using Delete Method

	@Test(dataProvider="DeleteBookData",enabled=false)
	public void deletebook(String isbn) {
		RestAssured.baseURI = "http://216.10.245.166";
		String deletebookrtesponse = given().header("Content-Type", "application/json").body(PayLoad.deletebook(isbn))
				.when().post(":/Library/Deletebook.php").then().log().all().assertThat().statusCode(200).extract()
				.asString();

		JsonPath deletebookresponse = ReusableMethods.jsonPathinitiator(deletebookrtesponse);
		String msgreceived = deletebookresponse.get("msg");
		Assert.assertEquals("book is successfully deleted", "book is successfully deleted");

	}

	@DataProvider(name = "DeleteBookData")
	public Object[][] getDataforDeleteBook() {
		return new Object[][] { { "ISBN-Test API with REST-04aisle-004" }, { "ISBN-Test API with REST-05aisle-005" },
				{ "ISBN-Test API with REST-06aisle-006" } };

	}	
	
	
}
