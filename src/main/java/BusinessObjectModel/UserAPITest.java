package BusinessObjectModel;

import io.restassured.RestAssured;

public class UserAPITest {

    public static void main(String[] args) {

        // Build request using BO
        UserRequest user = new UserRequest();
        //Serialization happens here
        user.setName("Alice");
        user.setEmail("alice@example.com");

        // Send request and map response to BO
        UserResponse response = RestAssured.given()
                .baseUri("https://example.com")
                .basePath("/api/users")
                .header("Content-Type", "application/json")
                .body(user) // <-- SERIALIZATION happens here
                .when()
                .post()
                .then()
                .statusCode(201)
                .extract()
                .as(UserResponse.class);

        // Validate or use response BO
        System.out.println("User ID: " + response.getId());  //<-- DESERIALIZATION happens here
        System.out.println("Created At: " + response.getCreatedAt());
    }
}
