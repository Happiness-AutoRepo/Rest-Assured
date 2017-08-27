package com.students.test;

import static io.restassured.RestAssured.given;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;

public class StudentDeleteTest {

	@BeforeClass
	public static void init() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8085;
		RestAssured.basePath = "/student";
	} 
	
	@Test
	public void studentPost() {
							given()
							.when()
							.delete("/101")
							.then()
							.log()
						    .status();
	}
	
}
