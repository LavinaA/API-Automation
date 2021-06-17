package RestAPI.file;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReusableMethods {

	public static JsonPath rawTojson(String response) {
		
		JsonPath js = new JsonPath(response);
		return js;
		
	}

}
