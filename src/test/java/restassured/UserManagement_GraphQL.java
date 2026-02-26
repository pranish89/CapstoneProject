package restassured;
/*
 * This class also performs the same User CRUD operation 
 * but with the GraphQL
 */

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import config.Constants;
import io.restassured.RestAssured;
import utilities.GraphQL;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

public class UserManagement_GraphQL {

	@Test
	public void get_user_by_id() {

		RestAssured.baseURI = "https://gorest.co.in/public/v2/graphql";
		String query = "query{user(id: 8104681) { id name email gender status }}";
		String jsonString = GraphQL.graphQLtoJson(query);
		given().log().all().header("accept", "application/json").header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + Constants.access_token).body(jsonString).when().post().then().log()
				.all().assertThat().statusCode(200);
	}

	@DataProvider
	public Object[][] getData() {
		return new Object[][] { { "Harry Potter1", "harry.potter1@example.com" },
				{ "Jack Jones1", "jack.jones1@example.com" } };
	}

	@Test(dataProvider = "getData")
	public void create_user(String name, String email) {
		RestAssured.baseURI = "https://gorest.co.in/public/v2/graphql";

		// mutation query as String
		String mutation_query = "mutation{createUser(input: {name: \"Johny Depp\" gender: \"male\"email:\"johny.depp@15ce.com\" status: \"active\"}){user{id name gender email status}}}";

		// Variables for mutation
		Map<String, Object> var1 = new HashMap<>();
		var1.put("name", name);
		var1.put("email", email);

		// requestbody for GraphQL
		Map<String, Object> request_body = new HashMap<>();
		request_body.put("query", mutation_query);
		request_body.put("variables", var1);

		given().log().all().header("accept", "application/json").header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + Constants.access_token).body(request_body).when().post().then()
				.log().all().assertThat().statusCode(200);

	}

}
