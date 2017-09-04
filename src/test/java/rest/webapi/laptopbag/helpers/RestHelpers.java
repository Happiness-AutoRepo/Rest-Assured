package rest.webapi.laptopbag.helpers;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.port;

import java.util.HashMap;
import java.util.Map;

public class RestHelpers {

	static {
		baseURI = "http://localhost";
		port = 8090;
		basePath = "/laptop-bag/webapi/api";
	}

	public static String buildURI(Methods method) {

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

	public static Map<String, String> getHeaders(HeaderType type) {
		
		Map<String, String> headers = new HashMap<>();
		
		switch (type) {
		case JSON:
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json");
			break;
		case XML:
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json");
			break;

		default:
			throw new RuntimeException("Wrong type");
		}
		return headers;
	}
	
	public enum HeaderType {
		JSON, XML
	}
}
