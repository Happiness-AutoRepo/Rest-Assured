package rest.weapi.laptopbag.gets;

import static io.restassured.RestAssured.*;

public class BasicGet {
	
	static String pingURL = "http://localhost:8090/laptop-bag/webapi/api/ping/Murodil";
	
	public static void main(String[] args) {
		
		String returnValue = when().get(pingURL).asString();
		System.out.println(returnValue);

		int responseCode = when().get(pingURL).getStatusCode();
		System.out.println(responseCode);
	}

}
