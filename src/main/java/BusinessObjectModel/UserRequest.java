package BusinessObjectModel;
/*

Serialization	Converting a Java object → JSON (for sending in API request)
Deserialization	Converting JSON → Java object (to handle API response)

You’re testing a Create User API that expects and returns JSON.

🔹 API Endpoint:
POST /api/users

🔹 Request Body:
{
  "name": "Alice",
  "email": "alice@example.com"
}

🔹 Response Body:
{
  "id": 101,
  "name": "Alice",
  "email": "alice@example.com",
  "createdAt": "2025-04-25T10:00:00Z"
}


 */

public class UserRequest {

    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
