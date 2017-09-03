package rest.weapi.laptopbag.gets;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.Matchers.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PerformGetRequest {

	@Test
	public void testPingMethod() throws URISyntaxException {
		/*
		 * when i perform get request to ping method
		 * then status code should be 200
		 * and message should be "Hi Murodil"
		 */
		
		URI pingURI = new URI("http://localhost:8090/laptop-bag/webapi/api/ping/Murodil");
		
		when().get(pingURI);
		
		Response response = when().get(pingURI);
		assertEquals(200, response.statusCode());
		assertEquals("Hi! Murodil", response.body().asString());
	}
	
        
	@Test
	public void testNegative() throws URISyntaxException {
		/*
		 * when i perform get request without an argument
		 * then status code should be 404
		 */
		
		URI pingURI = new URI("http://localhost:8090/laptop-bag/webapi/api/ping/");
		
		System.out.println(pingURI.getPort());
		System.out.println(pingURI.getHost());
		System.out.println(pingURI.getPath());
		
		Response response = when().get(pingURI);
		assertEquals(404, response.statusCode());
	}
	
	@Test
	public void testAll() throws URISyntaxException {
		/*
		 * when i perform get request to all API methods
		 * then status code should be 200
		 * and body should contain 
		 */
		
		URI pingURI = new URI("http://localhost:8090/laptop-bag/webapi/api/all/");
		
		System.out.println(pingURI.getPort());
		System.out.println(pingURI.getHost());
		System.out.println(pingURI.getPath());
		
		Response response = given().contentType(ContentType.JSON)
							.when()
							.get(pingURI);
		
		assertEquals(200, response.statusCode());
		System.out.println(response.prettyPrint());
		
		given().accept(ContentType.JSON)													//built-in assertion
		.when().get(pingURI)
		.then().assertThat().statusCode(200)
		.and().assertThat().contentType(ContentType.JSON);
	}
	
	@Test
	public void testFindWithID() throws URISyntaxException {
		URI findURI=new URI("http://localhost:8090/laptop-bag/webapi/api/find/150");
		
		given().accept(ContentType.JSON)
		.when().get(findURI)
		.then().assertThat().statusCode(200)
		.and().assertThat().body("Id", equalTo(150))
		.and().assertThat().body("BrandName",equalToIgnoringCase("Apple"));
		
		given().accept(ContentType.JSON)
		.when().get(findURI)
		.then().assertThat().statusCode(200)
		.and().assertThat().body("Id",equalTo(150),"BrandName",equalToIgnoringCase("Apple"));
		
	}
	
	/* 
	 *  Scenario: Features list content   
	 *  Given  accept type is JSON   
	 *  When I  perform GET request with id 150   
	 *  Then status code should be 200   
	 *  And Features should be 3  items
	 *  And Features should contain following: 
	 *  "16GB RAM","500GB SSD","i7 CPU" 
	 */
	
	@Test //VERIFY INNER JSON OBJECT, HOW TO   VALIDATE A  LIST
	public void testFeaturesList() throws URISyntaxException {
		URI findURI=new URI("http://localhost:8090/laptop-bag/webapi/api/find/150");
		
		given().accept(ContentType.JSON)
		.when().get(findURI)
		.then().assertThat().statusCode(200)
		.and().assertThat().body("Features.Feature", hasSize(3))
		.and().assertThat().body("Features.Feature", hasItems("16GB RAM","250GB SSD","i5 CPU"));
		
	}
	
	@Test
	public void testUsingJsonPath() throws URISyntaxException {
		
		URI findURI=new URI("http://localhost:8090/laptop-bag/webapi/api/find/150");
		String body=given().accept(ContentType.JSON).when().get(findURI).thenReturn().body().asString();
		
		JsonPath json=new JsonPath(body);
		
		System.out.println(json.getString("BrandName"));
		assertEquals("Apple", json.getString("BrandName"));
		
		System.out.println(json.getInt("Id"));
		assertEquals(150, json.getInt("Id"));
		
		System.out.println(json.getString("LaptopName"));
		assertEquals("MAC", json.getString("LaptopName"));
		
		List<String> features=json.getList("Features.Feature");
		List<String> expFeatures=Arrays.asList("500GB SSD","16GB RAM", "i7 CPU");
		//I dont care about ORDERassertTrue(features.containsAll(expFeatures));
		//I care about ORDERassertEquals(expFeatures, features);
		System.out.println(features.toString());
		}
	
	@Test
	public void testDirectory() throws URISyntaxException {
		
		URI myURI=new URI("https://reqres.in/api/users?page=3");
		String body=given().accept(ContentType.JSON).when().get(myURI).thenReturn().body().asString();
		
		JsonPath json=new JsonPath(body);
		
		System.out.println(json.getString("page"));
		assertEquals("3", json.getString("page"));
		
//		List<String> features=json.getList("Features.Feature");
//		List<String> expFeatures=Arrays.asList("500GB SSD","16GB RAM", "i7 CPU");
//	
//		System.out.println(features.toString());
		}
	
	}

