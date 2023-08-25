import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class PostRequest {

    String id;
    String baseURL = "https://api.restful-api.dev/objects";
    @Test (enabled = false)
    void  postrequest()
    {
        //RestAssured.baseURI = "https://api.restful-api.dev/objects";
        Map<String, Object> postdata = new HashMap<>();
        postdata.put("name", "Dell Inspiron");
        Map<String, String> insidedata = new HashMap<>();
        insidedata.put("year","2021");
        insidedata.put("price", "75000");
        insidedata.put("CPU model", "Intel Core i5");
        insidedata.put("Hard disk size"
                ,"512 GB");
        postdata.put("data", insidedata);
        System.out.println(postdata);
       // id =given().contentType("application/json").body(postdata).log().all().when().post().jsonPath().getInt("id");
        id =given().contentType(ContentType.JSON).body(postdata).log().all().when().post(baseURL).then().statusCode(200).log().all()
                .body("name", equalTo("Dell Inspiron"))
                .header("Content-Type","application/json")
                .extract().response().jsonPath().getString("id");
    }
    @Test
    void  orgpostrequest()
    {
        //RestAssured.baseURI = "https://api.restful-api.dev/objects";
        JSONObject postdata = new JSONObject();
        postdata.put("name", "Dell Inspiron");
        JSONObject insidedata = new JSONObject();
        insidedata.put("year","2021");
        insidedata.put("price", "75000");
        insidedata.put("CPU model", "Intel Core i5");
        insidedata.put("Hard disk size"
                ,"512 GB");
        postdata.put("data", insidedata);
        System.out.println(postdata);
        // id =given().contentType("application/json").body(postdata).log().all().when().post().jsonPath().getInt("id");
        id =given().contentType(ContentType.JSON).body(postdata.toString()).log().all().when().post(baseURL).then().statusCode(200).log().all()
                .body("name", equalTo("Dell Inspiron"))
                .header("Content-Type","application/json")
                .extract().response().jsonPath().getString("id");
    }

    @Test (dependsOnMethods = { "orgpostrequest"})
    void getrequest()
    {
      given().contentType(ContentType.JSON).queryParam("id", id).log().all()
              .when().get(baseURL).then().statusCode(200).log().all().extract().response().jsonPath().getString("name").equals("Dell Inspiron");
       Response res = given().contentType(ContentType.JSON).queryParam("id", id).log().all().when().get(baseURL);
       System.out.println(res.body().jsonPath().getString("data.year"));
        System.out.println(res.body().jsonPath().getString("name"));
    }
    @Test
    void getallrequest()
    {
       // when().get(baseURL).then().statusCode(200).log().all().extract().response().jsonPath().getString("name").equals("Dell Inspiron");
        Response res = given().contentType(ContentType.JSON).when().get(baseURL);
        res.body().print();
        System.out.println(res.body().jsonPath().getString("[6].data.year"));
        System.out.println(res.body().jsonPath().getString("[6].name"));
        String body1 = "{\"test\":"+res.body().toString()+"}";
       JSONObject body = new JSONObject(body1); // Converting response to JSON object
        //System.out.println(res.body().jsonPath().getString("id").length());
        for (int i=0; i < body.getJSONArray("test").length(); i++)
        {
          System.out.println(body.getJSONArray("test").getJSONObject(i).get("name").toString());
           // System.out.println(res.body().jsonPath().getString("["+i+"].name"));
        }

    }
}
