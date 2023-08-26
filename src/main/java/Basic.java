import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import java.util.Collections;
import java.util.List;

//Static imports
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

//If you want to use Json Schema validation you should also statically import these methods:
//import static io.restassuredmodule.jsv.JsonSchemaValidator.*

public class Basic {

    public static void main(String args[])
    {
        // given - all input details (Content type, set cookies, set headers, set param, add authentication)
        // when - submit the API --> resources, method and https/http
        // then - validate the response (validate status code and response body, extract header, response and cookies)
        RestAssured.baseURI = "https://reqres.in/";
        //https://gorest.co.in/public/v2/users
        //RestAssured.baseURI = "https://gorest.co.in/";
        /*String response = given().log().all().header("Content-Type", "application/json")
                .body(userspostPayload.usersPost()).when().post("/api/users").then().log().all().assertThat().statusCode(201).body("name", equalTo("dummyrest1"))
                .header("Server" ,"cloudflare").extract().response().asString();

        System.out.println(response);
        //Add name --> update last name  --> Get name  to validate if new job present for that name

        JsonPath js = new JsonPath(response); //for parsing json
        String id = js.getString("id");
        System.out.println(id);*/

        /*given().log().all().header("Content-Type", "application/json").when().get("/api/users/2").then().log().all().assertThat().statusCode(200)
                .body("data.first_name", equalTo("Janet"));

        given().log().all().header("Content-Type", "application/json").body(usersputPayload.usersPut()).when().put("/api/users/2").then().log().all();
        given().log().all().header("Content-Type", "application/json").when().get("/api/users/2").then().log().all();*/

       String response = given().log().all().when().get("/api/unknown").then().log().all().extract().response().asString();

       JsonPath data = new JsonPath(response);
       List<String> names= Collections.singletonList(data.getString("data.name"));
       System.out.println(names);


    }
}
