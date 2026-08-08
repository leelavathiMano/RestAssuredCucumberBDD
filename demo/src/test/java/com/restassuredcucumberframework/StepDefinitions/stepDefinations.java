package com.restassuredcucumberframework.StepDefinitions;
import java.io.IOException;

import org.junit.Assert;

import com.restassuredcucumberframework.UtilityFiles.Utils;
import com.restassuredcucumberframework.resources.APIResourcesWithEnumClass;
import com.restassuredcucumberframework.resources.TestDataBuild;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.given;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class stepDefinations extends Utils {
     RequestSpecification addplacereq1Specification;
     ResponseSpecification addplaceresponse;
     Response response;
     TestDataBuild testDataBuild=new TestDataBuild();
   
static String place_id1;
@Given("Add place Payload with {string} {string} {string}")
public void add_place_payload(String name, String language, String address) throws IOException {
  RequestSpecification addplacereq= new RequestSpecBuilder()
    .setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).addQueryParam("key", "qaclick123")
    .setContentType(ContentType.JSON).build();
    addplaceresponse=new ResponseSpecBuilder().expectStatusCode(200)
    .expectContentType(ContentType.JSON).build();
    addplacereq1Specification=given().spec(requestSpecification()).body(testDataBuild.addPlacePayload(name, language, address));
}
@When("User calls {string} with {string} http request")
public void user_calls_add_place_api_with_post_http_request(String resource,String httpMethods) {
    //Enum is the special class which is the collection of constant varaibles and methods
    //Constructor will be called with value of string which you pass    
    APIResourcesWithEnumClass apiResource = APIResourcesWithEnumClass.valueOf(resource);
    System.out.println(httpMethods.toUpperCase() + " -> " + apiResource.getResource());
     if(httpMethods.equalsIgnoreCase("POST")) {
     response=addplacereq1Specification.when().post(apiResource.getResource());
}
 else if(httpMethods.equalsIgnoreCase("GET")) {
     response=addplacereq1Specification.when().get(apiResource.getResource());
 }
 else if(httpMethods.equalsIgnoreCase("DELETE")) {
     response=addplacereq1Specification.when().delete(apiResource.getResource());
 }
 else {
     throw new IllegalArgumentException("Unsupported HTTP method: " + httpMethods);
 }
}
@Then("API call is success with status code {int}")
public void api_call_is_success_with_status_code(Integer int1) {
    Assert.assertEquals(response.getStatusCode(),int1.intValue());
}
@Then("{string} in response body is {string}")
public void in_response_body_is(String key, String expectedValue) {
    Assert.assertEquals(getjsonPath(response, key), expectedValue);
}

@Then("Verify if {string} created maps to {string} using {string}")
public void verify_if_created_maps_to_using(String place_id, String Expectedname, String resource) throws IOException {
    RequestSpecification verifyreq=given().spec(requestSpecification()).queryParam("place_id", place_id);
    String res=response.asString();
    JsonPath js=new JsonPath(res);
    place_id1= getjsonPath(response, "place_id");
   addplacereq1Specification=given().spec(requestSpecification()).queryParam("place_id", place_id1);
   user_calls_add_place_api_with_post_http_request(resource, "GET");
      String actualname= getjsonPath(response, "name");
      Assert.assertEquals(actualname, Expectedname);

   
}
@Given("delete place payload")
public void delete_place_payload() throws IOException {
   addplacereq1Specification=given().log().all().spec(requestSpecification()).body(testDataBuild.deletePlacePayload(place_id1));
}
}
