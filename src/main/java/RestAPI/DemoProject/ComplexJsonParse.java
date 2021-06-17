package RestAPI.DemoProject;

import org.testng.Assert;
import org.testng.annotations.Test;

import RestAPI.file.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	
	@Test
	
	public void noOfCourses() {
		
		//Print No of courses returned by API
		
		JsonPath js = new JsonPath(payload.CourseDetails());
		
		int noOfCourses = js.getInt("courses.size");
		System.out.println(noOfCourses);
		
	}
	
	
	@Test
	
	public void printPurchaseAmount() {
		
		JsonPath js = new JsonPath(payload.CourseDetails());
		int printPurchaseAmount = js.get("dashboard.purchaseAmount");
		System.out.println(printPurchaseAmount);
		
		
	}
	
	@Test
	
	public void printTitleFirstCourse() {
		
			//Print Title of the first course
			JsonPath js = new JsonPath(payload.CourseDetails());
		
			String firstCourseTitle = js.get("courses[0].title");
			System.out.println(firstCourseTitle);
	}
	
	@Test
	
	//Print All course titles and their respective Prices
	
	public void printAllCourseTitlePrices() {
		
		JsonPath js = new JsonPath(payload.CourseDetails());
		
		for(int i = 0 ; i< js.getInt("courses.size") ; i++) { 
			
			String title = js.getString("courses["+i+"].title");
			String price = js.getString("courses["+i+"].price").toString();
			
			
			System.out.println(title);
			System.out.println(price);
		}
	}
	
	//Print no of copies sold by RPA Course
	//both get() and getString will convert the json to String and both the functions are similar
	
	@Test
		public void noOfCopiesRPA() {
		
		JsonPath js = new JsonPath(payload.CourseDetails());
		
		for(int i = 0 ; i< js.getInt("courses.size") ; i++) { 
	
			if(js.getString("courses["+i+"].title").equalsIgnoreCase("RPA")) {
				
				System.out.println((js.getString("courses["+i+"].copies").toString()));
			}
				
		}
		
	}		
	
	@Test
		
	public void sumValidation() {
		
		//Verify if Sum of all Course prices matches with Purchase Amount
		
		JsonPath js = new JsonPath(payload.CourseDetails());
		
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		
		//Finding sum of all courses
		
		
		int sum = 0;
		for(int i = 0 ; i< js.getInt("courses.size") ; i++) { 
			
		int price =	js.getInt("courses["+i+"].price");
		int copies = js.getInt("courses["+i+"].copies");
		
		sum = sum + (price*copies);
			
		}
		
		System.out.println("The sum of all courses " +sum);
		
		Assert.assertEquals(sum, purchaseAmount);




	}



}
