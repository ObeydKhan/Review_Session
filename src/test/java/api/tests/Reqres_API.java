package api.tests;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Reqres_API {

@Test
public void getUsersTest() {
//	given().accept(ContentType.JSON)
//	.and().parameter("page", 2)
//	.when().get("https://reqres.in/api/users")
//	.then().assertThat().statusCode(200);

	Response response=given().accept(ContentType.JSON)
	.and().parameter("page", 2)
	.when().get("https://reqres.in/api/users");
	
//	System.out.println(response.getStatusLine());
//	System.out.println(response.contentType());
//	System.out.println(response.headers());
//	System.out.println(response.body().asString());
	
	assertEquals(200,response.statusCode() );
	assertTrue(response.contentType().contains("application/json"));
	
	Header header=new Header("X-Powered-By","Express");
	assertTrue(response.getHeaders().asList().contains(header));
	
	JsonPath json=response.jsonPath();
	assertEquals(12, json.getInt("total"));

	assertEquals(4, json.getInt("data.id[0]"));
	
	assertEquals(5, json.getInt("data.find{it.first_name=='Charles'}.id"));
	assertEquals("Tracey", json.getString("data.find{it.id==6}.first_name"));
	assertEquals("Ramos", json.getString("data.find{it.id==6}.last_name"));

}
}
