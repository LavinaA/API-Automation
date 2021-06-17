package RestAPI.DemoProject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import RestAPI.file.ReusableMethods;
import RestAPI.file.payload;



public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Given -all input details
		//when -submit the API /HTTP method and resource
		//then -validate the response
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		
		 String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payload.addPlace())
		.when().post("maps/api/place/add/json")
		.then().assertThat()
		.statusCode(200)
		.header("Server","Apache/2.4.18 (Ubuntu)")
		.extract().response().asString();
		
		//JsonPath is a class that takes string as input and converts it into json
		
		System.out.println(response);
		
		JsonPath js = ReusableMethods.rawTojson(response);
		String placeId = js.getString("place_id");
		
		
		/*JsonPath js = new JsonPath(response);
		String placeId = js.getString("place_id")*/;
		
		System.out.println(placeId);
		
		
		//Update Address for the given API
		
		String newAddress = "Summer Walk, Africa";

		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body(("{\r\n" + 
				"\"place_id\":\""+placeId+"\",\r\n" + 
				"\"address\":\""+newAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}"))
		.when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));
		
		
		//Get API
		
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
		.when().get("maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(getPlaceResponse);
		
		
		JsonPath js1 = ReusableMethods.rawTojson(getPlaceResponse);
		String actualAddress = js1.getString("address");
		
		/*JsonPath js1 = new JsonPath(getPlaceResponse);
		String actualAddress = js1.getString("address");*/
		
		Assert.assertEquals(actualAddress, newAddress);
		
		
	}
}
