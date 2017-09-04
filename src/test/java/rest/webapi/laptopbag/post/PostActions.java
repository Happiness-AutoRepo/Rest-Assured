package rest.webapi.laptopbag.post;

import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import io.restassured.path.json.JsonPath;
import rest.webapi.laptopbag.helpers.RestHelpers;
import rest.webapi.laptopbag.helpers.RestHelpers.HeaderType;
import rest.webapi.laptopbag.helpers.RestHelpers.Methods;

public class PostActions {

	@Test
	public void PostWithHeaders() throws URISyntaxException {
		
		String id=(int)(1000*(Math.random()))+""; 
		String jsonBody="{"+ "\"BrandName\": \"Apple\","+ "\"Features\": {"+ "\"Feature\": [\"8GB RAM\","+ "\"1TB Hard Drive\"]"+ "},"+ "\"Id\": "+id+","+ "\"LaptopName\": \"MacBook Pro\""+ "}";
		
		URI addURI = new URI(RestHelpers.buildURI(Methods.ADD));
		
		Map<String,String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json");
		
//		with().headers(headers).body(jsonBody)
//		.when().post(addURI)
//		.then().assertThat().body("Features.Feature", hasItems("8GB RAM", "1TB Hard Drive"));
		
		with().headers(RestHelpers.getHeaders(HeaderType.JSON)).body(jsonBody)
		.when().post(addURI)
		.then().assertThat().body("Features.Feature", hasItems("8GB RAM", "1TB Hard Drive"));
		
		
		// same thing using JSON path
		JsonPath jsonPath = new JsonPath(with().headers(headers).body(jsonBody).when().post(addURI).asString());
		
		List<String> features = Arrays.asList("8GB RAM", "1TB Hard Drive");
		assertTrue(jsonPath.getList("Features.Feature").containsAll(features));
		
	}

}
