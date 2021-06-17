package RestAPI.DemoProject;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import RestAPI.file.ReusableMethods;
import RestAPI.file.payload;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;

public class DynamicJson {
	
	@Test(dataProvider = "getData")
	
	public void addBook(String aisle ,String isbn) {
		
		//Post Library request
		RestAssured.baseURI = "http://216.10.245.166";
		
		String response =  given().header("Content-Type","application/json").body(payload.BookDetails(aisle,isbn))
		.when().post("/Library/Addbook.php")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		
		System.out.println("********************");
		System.out.println(response);
		
		
		  JsonPath js = ReusableMethods.rawTojson(response);
		  
		  String id = js.getString("ID");
		  System.out.println(id);
		  
		  String message = js.getString("Msg"); 
		  System.out.println(message);
	}	  
		  
		 
	@DataProvider 
		  
	  public Object[][] getData() {
	  
	  Object[][] data = new Object[3][2];
			  
			  
	//rows represent number of records present
	//col represents number of values passed for each test
	  
	  	data[0][0]="kjnsds";
		data[0][1]="abdjs34";

		data[1][0]="fsmca";
		data[1][1]="asjlna89";

		data[2][0] = "fshwoww";
		data[2][1] = "dkl0934";
	  
		return data;
			  
	}
		 
		
	
}
