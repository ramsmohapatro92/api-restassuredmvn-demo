package com.restassured.qa.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.restassured.utility.PayLoad;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParsing {

	@Test
	public static void validateComplexJson() {
		JsonPath jsobj1 = new JsonPath(PayLoad.CoursePrice());

		// refer complex JSON note pad for reference of Response API

		// 1. Print number of courses returned by API
		int size = jsobj1.getInt("courses.size()");
		System.out.println(size);

		// 2.Print Purchase Amount
		int purchase_amt = jsobj1.getInt("dashboard.purchaseAmount");
		System.out.println(purchase_amt);

		int total = 0;

		// 3. Print Title of the first course

		String title1 = jsobj1.get("courses[0].title");
		int price1 = jsobj1.getInt("courses[0].price");
		System.out.println(title1 + ": " + price1);

//		String title2=jsobj1.get("courses[1].title");
//		int price2=jsobj1.getInt("courses[1].price");
//		String title3=jsobj1.get("courses[2].title");
//		int price3=jsobj1.getInt("courses[2].price");
//		System.out.println(title2+": "+price2);
//		System.out.println(title3+": "+price3);

		// 4. Print All course titles and their respective Prices
		for (int i = 0; i < size; i++) {
			String title = jsobj1.get("courses[" + i + "].title");
			int price = jsobj1.getInt("courses[" + i + "].price");
			int copies = jsobj1.getInt("courses[" + i + "].copies");
			System.out.println(title + ": " + price + " need : " + copies);
		}

		// 5. Print no of copies sold by RPA Course

		for (int i = 0; i < size; i++) {
			String title = jsobj1.get("courses[" + i + "].title");
			if (title.equalsIgnoreCase("RPA")) {
				int copies = jsobj1.getInt("courses[" + i + "].copies");
				System.out.println(title + ": " + copies);
				break;
			}
		}

		// 6. Verify if Sum of all Course prices matches with Purchase Amount
		for (int i = 0; i < size; i++) {
			String title = jsobj1.get("courses[" + i + "].title");
			int price = jsobj1.getInt("courses[" + i + "].price");
			int copies = jsobj1.getInt("courses[" + i + "].copies");
			total=total+price*copies;

		}

		System.out.println("total: " + total);
		
		Assert.assertEquals(total, purchase_amt,"Mismatch");
		
		if (total == purchase_amt) {
			System.out.println("Amount matched");
		}

		else {
			System.out.println("Amount mismatch");
		}
		
		System.out.println("put");

	}
}
