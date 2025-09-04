package restassured;
/*
 * To Perform the Posts related operations like creation posts and viewing
 */

import static io.restassured.RestAssured.given;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import config.Constants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utilities.MSAutomationExcel;

public class Posts {
	
	@Test
	public void get_all_posts() {
		RestAssured.baseURI="https://gorest.co.in/public/v2";
		
		given().log().all().header("accept", "application/json").header("Content-Type", "application/json").header("Authorization", "Bearer " + Constants.access_token)
		.when()	.get("/posts").
		then().log().all().assertThat().statusCode(200);

		System.out.println("List of all posts retrieved");
	}
	
	@Test
	public void get_posts_by_id() throws Exception {
		RestAssured.baseURI = "https://gorest.co.in/public/v2";
		String post = MSAutomationExcel.getExcelData("Sheet3", 1, 1);
		int post_id = Integer.parseInt(post.substring(1));

		Response response = given().log().all().header("accept", "application/json")
				.header("Content-Type", "application/json").header("Authorization", "Bearer " + Constants.access_token)
				.when().get("/posts/"+post_id).then().log().all().assertThat().statusCode(200).extract()
				.response();
		
		int id_response = response.jsonPath().getInt("id");
		if(post_id==id_response)
		{
			System.out.println("Retrieved the correct post information for the post id " +post_id);
		}
}
	
	@Test
	public void get_posts_by_userid() throws Exception {
		RestAssured.baseURI = "https://gorest.co.in/public/v2";
		String user = MSAutomationExcel.getExcelData("Sheet3", 1, 2);
		int user_id = Integer.parseInt(user.substring(1));

		Response response = given().log().all().header("accept", "application/json")
				.header("Content-Type", "application/json").header("Authorization", "Bearer " + Constants.access_token)
				.when().get("/users/"+user_id+"/posts").then().log().all().assertThat().statusCode(200).extract()
				.response();
		
		int id_response = response.jsonPath().getInt("user_id[0]");
		if(user_id==id_response)
		{
			System.out.println("Retrieved the correct user post information for the User id " +user_id);
		}
	}
		
		@Test
		@Parameters ({"userid"})
		public void create_post(int id)		
		{
			RestAssured.baseURI = "https://gorest.co.in/public/v2";
			Response response = given().log().all().header("accept", "application/json")
					.header("Content-Type", "application/json").header("Authorization", "Bearer " + Constants.access_token)
					.body("{\r\n"
							+ "    \"id\": 1064545,\r\n"
							+ "    \"user_id\": "+id+",\r\n"
							+ "    \"title\":\"Testing from Rest Assured\",\r\n"
							+ "    \"body\":\"This is used for rest api testing purpose\"\r\n"
							+ "}")
					.when().post("/users/"+id +"/posts").then().log().all().assertThat().statusCode(201).extract()
					.response();
			
		}
} 
