package com.students.test;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import postdata.Student;

public class StudentPostTest {

	@BeforeClass
	public static void init() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8085;
		RestAssured.basePath = "/student";
	} 
	
	@Test
	public void studentPost() {
		
		List<String> courses = new ArrayList<>();
		courses.add("Java");
		courses.add("SQL");
		courses.add("RestAssured");
		
		Student student = new Student();
		student.setFirstName("Elvira");
		student.setLastName("Metoff");
		student.setEmail("elvira@gmail.com");
		student.setProgramme("SDET");
		student.setCourses(courses);
		
							given()
							.contentType(ContentType.JSON)
							.when()
							.body(student)
							.post()
							.then()
							.log()
						    .status();
	}
	
}
