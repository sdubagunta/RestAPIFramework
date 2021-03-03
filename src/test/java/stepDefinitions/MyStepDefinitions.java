package stepDefinitions;

import Resources.APIResources;
import Resources.TestDataBuild;
import Resources.Utilities;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.runner.RunWith;
import pojo.AddPlace;
import pojo.Location;
import io.restassured.path.json.JsonPath;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static Resources.TestDataBuild.deletePlacePayload;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


public class MyStepDefinitions extends Utilities {

    ResponseSpecification resspec;
    RequestSpecification req;
    Response response;
   static   String placeid;
    TestDataBuild data =new TestDataBuild();
    @Given("^Add Place Payload with (.+),(.+),(.+)$")
    public void add_place_payload_with(String name, String address, String language) throws IOException {

         req = given().spec(requestSpecification())
                .body(data.addPlacePayLoad(name,address,language));

    }


    @When("^user calls \"([^\"]*)\" with \"([^\"]*)\" http request$")
    public void user_calls_with_http_request(String resource, String method)  {

        APIResources resourceAPI = APIResources.valueOf(resource);
        resspec  = new ResponseSpecBuilder()
                .expectStatusCode(200).expectContentType(ContentType.JSON).build();
    if(method.equalsIgnoreCase("POST"))
        response=    req.when().post(resourceAPI.getResource());
    else if(method.equalsIgnoreCase("GET"))
        response = req.when().get(resourceAPI.getResource());
    }

    @Then("^the API call is success with status code 200$")
    public void the_api_call_is_success_with_status_code_200() {

    assertEquals(response.getStatusCode(),200);

    }

    @And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
    public void in_response_body_is(String keyvalue, String expectedvalue) {

        assertEquals(getJsonPath(response, keyvalue),expectedvalue);

    }
    @And("^verify place_id created maps to (.+) using \"([^\"]*)\"$")
    public void verify_placeid_created_maps_to_using(String name, String resource) throws IOException {

         placeid = getJsonPath(response,"place_id");
        req = given().spec(requestSpecification())
                .queryParam("place_id",placeid);
        user_calls_with_http_request(resource,"GET");
        String sname = getJsonPath(response,"name");
        assertEquals(sname,name);
    }
    @Given("^DeletePlace Payload$")
    public void deleteplace_payload()throws IOException {
        req = given().spec(requestSpecification())
                .body(data.deletePlacePayload(placeid));

    }
}