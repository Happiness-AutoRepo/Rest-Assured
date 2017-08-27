package com.students.test;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class StudentGetTest {
	
	@BeforeClass
	public static void init() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8085;
		RestAssured.basePath = "/student";
	} 
	
//	@Test
//	public void getAllStudentInfo() {
//		
//		Response response = given().when().get("/list");
////		System.out.println(response.body().prettyPrint());                                      //printing in a pretty format
//		
//		ValidatableResponse valResponse1 = given().when().get("/list").then().statusCode(200);  //validating response, reference variable is not needed
////		ValidatableResponse valResponse2 = given().when().get("/list").then().log().status();   //printing the response code, reference variable is not needed	
//	}
//	
//	@Test
//	public void getStudentFromFA() {
//		
//		Response response = given().when().get("/list?pragramme=Financial Analysis&limit=2");
////		System.out.println(response.prettyPeek());                                              //printing data for students in FA in a pretty format
//	}
	
//	@Test
	public void parametrizedPrint() {
		
		Response response = given()
							.param("programme", "Financial Analysis")
							.param("limit", "5")
							.when()
							.get("/list");
		
		System.out.println(response.prettyPeek());                                              

	}
	
	@Test
	public void get() {
		
		Response response = given()
							.param("programme", "SDET")
							.param("limit", "10")
							.when()
							.get("/list");
		
		System.out.println(response.prettyPeek());                                              

	}

}
