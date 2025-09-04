package restassured;
/* To check the error case, not sending the mandatory field "Title" in the body of the POST
 * request and hence the error 422-Unprocessable Entity is sent from server
 */

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import config.Constants;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ErrorHandling {	
	@Test
	public void create_user() {		
		RestAssured.baseURI = "https://gorest.co.in/public/v2";
		
		Response response = given().log().all().header("accept", "application/json")
				.header("Content-Type", "application/json").header("Authorization", "Bearer " + Constants.access_token)
				.body("{\r\n"
						+ "    \"gender\":\"male\", \r\n"
						+ "    \"email\":\"arnold.schewenger@15ce.com\",\r\n"
						+ "    \"status\":\"active\"\r\n" + "}")
				.when().post("/users/").then().log().all().assertThat().statusCode(201).extract().response();
		
				System.out.println("User creation  failed with status code and message " +response.statusCode() +"\n" +response.statusLine());
	}
	

}
