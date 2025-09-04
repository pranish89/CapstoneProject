package restassured;
/* To create a user post, we need the Authorization token but to prove Edge Cases, sending the requests without 
* the token and the Authorization failed will be sent from the server
*/

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import config.Constants;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class EdgeCaseHandling {
	
	@Test
	public void create_post()		
	{
		
		RestAssured.baseURI = "https://gorest.co.in/public/v2";
		Response response = given().log().all().header("accept", "application/json")
				.header("Content-Type", "application/json")
				.body("{\r\n"
						+ "    \"id\": 1064545,\r\n"
						+ "    \"user_id\": 8061870,\r\n"
						+ "    \"title\":\"Testing from Rest Assured\",\r\n"
						+ "    \"body\":\"This is used for rest api testing purpose\"\r\n"
						+ "}")
				.when().post("/users/8061870/posts").then().log().all().assertThat().statusCode(201).extract()
				.response();
		
	

}
}