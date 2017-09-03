package rest.webapi.laptopbag.helpers;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.port;

public class RestHelpers {

	public static String buildURI(Methods method) {
		
		baseURI = "http://localhost";
		port = 8090;
		basePath = "/laptop-bag/webapi/api";
		
		String apiMethod = null;
		
		switch (method) {
		case PING:
			apiMethod = "/ping/world";
			break;
		case FIND:
			apiMethod = "/find";
			break;
		case ALL:
			apiMethod = "/all";
			break;
		case DELETE:
			apiMethod = "/delete";
			break;
		case ADD:
			apiMethod = "/add";
			break;
		default:
			throw new RuntimeException("Invalid API Method");
		}
		return apiMethod;
	}

	public enum Methods {
		PING, FIND, ALL, DELETE, ADD
	}
}
