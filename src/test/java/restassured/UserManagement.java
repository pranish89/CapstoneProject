package restassured;
/*
 * This class is to create, view,update and delete operations for users in GoRest API
 */

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import config.Constants;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UserManagement {
	int userid;

	@Test(priority = 5)
	public void get_all_users() {
		RestAssured.baseURI = "https://gorest.co.in/public/v2";

		given().log().all().header("accept", "application/json").header("Content-Type", "application/json").when()
				.get("/users").then().log().all().assertThat().statusCode(200);

		System.out.println("List of users retrieved");

	}

	public void get_user_by_id(int id) {
		
		RestAssured.baseURI = "https://gorest.co.in/public/v2";
		
		Response response = given().log().all().header("accept", "application/json")
				.header("Content-Type", "application/json").header("Authorization", "Bearer " + Constants.access_token)
				.when().get("/users/"+id).then().log().all().extract()
				.response();
		
		if (response.statusCode() == 200) {
			int userid = response.jsonPath().getInt("id");
			if (id == userid) {
				System.out.println("correct user info is retrived");
			}
		} else if (response.statusCode() == 404) {
			System.out.println("The content is deleted");
		}

	}

	@Test(priority = -1, groups = { "UserCreation" })
	public void create_user() {
		RestAssured.baseURI = "https://gorest.co.in/public/v2";
		
		String response = given().log().all().header("accept", "application/json")
				.header("Content-Type", "application/json").header("Authorization", "Bearer " + Constants.access_token)
				.body("{\r\n" + "    \"name\":\"Arnold Schewenger\", \r\n" + "    \"gender\":\"male\", \r\n"
						+ "    \"email\":\"arnold.schewenger@15ce.com\",\r\n" + "    \"status\":\"active\"\r\n" + "}")
				.when().post("/users/").then().log().all().assertThat().statusCode(201).extract().asString();
		
		JsonPath path = new JsonPath(response);
		userid = path.getInt("id");
		System.out.println("Retrived user id is " + userid);
		get_user_by_id(userid);

	}

	@Test(dependsOnMethods = { "create_user" })
	public void update_user() {
		RestAssured.baseURI = "https://gorest.co.in/public/v2";
		
		String response = given().log().all().header("accept", "application/json")
				.header("Content-Type", "application/json").header("Authorization", "Bearer " + Constants.access_token)
				.body("{\r\n" + "    \"name\":\"Arnold Schewen\",\r\n"
						+ "    \"email\":\"arnold.schewen@15ce.com\",\r\n" + "    \"status\":\"active\"\r\n" + "}")
				.when().patch("/users/" + userid).then().log().all().assertThat().statusCode(200).extract().response()
				.asString();
		
		JsonPath path = new JsonPath(response);
		String name = path.getString("name");
		String email = path.getString("email");
		System.out.println("The name and email of updated user is " + name + "\n" + email);

	}

	@Test(dependsOnMethods = { "update_user" })
	public void delete_user() {
		 given().log().all().header("accept", "application/json")
				.header("Conten	t-Type", "application/json").header("Authorization", "Bearer " + Constants.access_token)
				.when().delete("/users/" + userid).then().log().all().assertThat().statusCode(204);

		get_user_by_id(userid);

	}
}