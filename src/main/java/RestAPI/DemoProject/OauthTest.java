package RestAPI.DemoProject;
import static io.restassured.RestAssured.given;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.path.json.JsonPath;

public class OauthTest {

	public static void main(String[] args) {
		
		
	System.setProperty("webdriver.chrome.driver", "C://Users//lavina.vijay.agrawal//chromedriver.exe")	;
	//WebDriver drive = new ChromeDriver();
		
		
	//Get access code	
		
	String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AY0e-g7NbJL622yWnQSCNEtO6ovaz8Gr_AV-ELP9qbkWcCSKs-ds4VyoiI2g_n0KPLKO0Q&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
	
	String partialsplit=url.split("code=")[1];
	String code = partialsplit.split("&scope")[0];	
	
	System.out.println(code);
		
	
	//Get access token
	String accessTokenresponse = given().urlEncodingEnabled(false)
	.queryParam("code",code).queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
	.queryParam("client_secret","erZOWM9g3UtwNRj340YYaK_W").queryParam("redirect_uri","https://rahulshettyacademy.com/getCourse.php")	
	.queryParam("grant_type","authorization_code")
	.when().post("https://www.googleapis.com/oauth2/v4/token").asString();
	
	System.out.println(accessTokenresponse);
	
	JsonPath js = new JsonPath(accessTokenresponse);
	String accessToken = js.get("access_token");
	
	
	//Get actual response to get the courses
	
	String response = given().queryParam("access_token",accessToken)
	.when().log().all().get("https://rahulshettyacademy.com/getCourse.php").asString();
	
	System.out.println(response);
	
	

		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
