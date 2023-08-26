import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.hamcrest.Matchers.*;
import org.hamcrest.core.StringContains;

import static io.restassured.RestAssured.*;

public class GoRestAutomate {

    public static void main(String args[])
    {
        RestAssured.baseURI = "https://gorest.co.in/";
        String response = given().log().all().headers("Accept", "application/json", "Content-Type", "application/json", "Authorization", "Bearer 2b8495de5a8b35bd8d4a0ec6b7e88c97c0f23856fe3a38f1378f6cafd9966bb1")
                .when().get("/public/v2/users/192196").then().log().all().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath update = new JsonPath(response);
        String id = update.getString("id");
        String name = update.getString("name");
        String email = update.getString("email") ;
        String gender = update.getString("gender");
        String status = update.getString("status");

        if (gender.equals("male"))
        {
            gender = "female";
        }
        else
        {
            gender = "male";
        }
        if (status.equals("inactive"))
        {
            status = "active";
        }
        else
        {
            status = "inactive";
        }

       given().log().all().headers("Accept", "application/json", "Content-Type", "application/json", "Authorization", "Bearer 2b8495de5a8b35bd8d4a0ec6b7e88c97c0f23856fe3a38f1378f6cafd9966bb1")
                .body("{\n" +
                        "    \"name\": \""+name+"\",\n" +
                        "    \"email\": \""+email+"\",\n" +
                        "    \"gender\": \""+gender+"\",\n" +
                        "    \"status\": \""+status+"\"\n" +
                        "}").when().put("/public/v2/users/"+id).then().log().all();

        given().log().all().headers("Accept", "application/json", "Content-Type", "application/json", "Authorization", "Bearer 2b8495de5a8b35bd8d4a0ec6b7e88c97c0f23856fe3a38f1378f6cafd9966bb1")
                .when().get("/public/v2/users/"+id).then().log().all().assertThat().statusCode(200)
                .body("gender", StringContains.containsString(gender));



    }
}
