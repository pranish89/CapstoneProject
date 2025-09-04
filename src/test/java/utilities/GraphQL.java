package utilities;

import org.json.JSONObject;

public class GraphQL {
public static String graphQLtoJson(String payload) {
		
		JSONObject json = new JSONObject();
		json.put("query", payload);
		
		
		return json.toString();
	

}
}