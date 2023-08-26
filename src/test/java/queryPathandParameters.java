import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class queryPathandParameters {

    @Test
    void params(){

        //RestAssured.baseURI = "https://reqres.in/";
        given().pathParam("mypath2", "users").pathParam("mypath1", "api").queryParam("page", 2).queryParam("id", 5).log().all()
               .when().get("https://reqres.in/{mypath1}/{mypath2}").then().log().all();

        ResponseBody res =RestAssured.given().baseUri("https://reqres.in/").basePath("api/users")
                .queryParam("page",2).log().all().get().body();
              //  .when().get().then().extract().response();
        System.out.println(res.jsonPath().get("data.findAll{data -> data.id >=1 && data.id <5}").toString());
        System.out.println(res.jsonPath().getString("data.findAll{data -> data.id >=5 && data.id <10}"));
        System.out.println(res.jsonPath().getString("support.text"));

    }
}
