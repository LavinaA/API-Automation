package RestAPI.DemoProject;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import RestAPI.file.payload;
import io.restassured.RestAssured;

public class UsingStaticJson {
	
	//readAllBytes method will read the content of the file and will convert it into Bytes .To convert the content of the file to string
	// we use new String
	
	public static void main(String []args) throws IOException {

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\lavina.vijay.agrawal\\Documents\\automation docs\\API automation\\AddPlaceRequestBody.json"))))
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat()
		.statusCode(200);
		
		
		
		
		
		
	}
	
	

}
