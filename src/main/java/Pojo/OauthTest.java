package Pojo;
import static io.restassured.RestAssured.given;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

@JsonIgnoreProperties
public class OauthTest {

	public static void main(String[] args) throws InterruptedException {
		
		
		/*
		 * System.setProperty("webdriver.chrome.driver",
		 * "C://Users//lavina.vijay.agrawal//chromedriver.exe") ; WebDriver driver = new
		 * ChromeDriver(); driver.get(
		 * "https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss"
		 * );
		 * driver.findElement(By.cssSelector("input[type='email']")).sendKeys("fdfd");
		 * driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER
		 * ); Thread.sleep(3000);
		 * driver.findElement(By.cssSelector("input[type='password']")).sendKeys("fxfe")
		 * ; driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.
		 * ENTER); Thread.sleep(4000); String url=driver.getCurrentUrl();
		 */
		
		
	//Get access code	
		
	String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AY0e-g68p9_O-sh1a_x89e1-SfMMXWApLiE3pLlrm4jVOcCKci_w38Ns6ceCK_VwXLP9YQ&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";
	
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
	
	/*
	 * String response = given().queryParam("access_token",accessToken)
	 * .when().log().all().get("https://rahulshettyacademy.com/getCourse.php").
	 * asString();
	 */
	

		//We are getting response and converting to Java object here
		//All the values of variables will fall in their respective variables to access
		//This is conversion of response to Java Object (Deserialisation)
		//getters come in to picture for deserialisation
	  CoursesDetails cd =
	  given().queryParam("access_token",accessToken).expect().defaultParser(Parser.JSON)
	  .when()
	  .get("https://rahulshettyacademy.com/getCourse.php").as(CoursesDetails.class);
	 
	
	//System.out.println(response);
	
	System.out.println(cd.getInstructor());
	//System.out.println(cd.getUrl());
	//System.out.println(cd.getCourses());
		
		
		

	}

}
