package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.IOException;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.testdata.APIResources;
import resources.testdata.Payload;
import resources.utils.GlobalProperties;
import resources.utils.Specs;

public class RsaMaps extends Specs {

	public static RequestSpecification request;
	public static Response response;
	static String place_id;
	Payload data = new Payload();
	
	@Given("Add {string}, {string} and {string} To AddPlace Payload")
	public void add_to_addplace_payload(String name,String address,String language) throws IOException {
		request = given().spec(requestSpecification()).body(data.addPlacePayload(name,address,language));
	} 
   
	@When("User Calls {string} Using {string} Http Request")
	public void user_calls_using_http_request(String resource, String method) {
		APIResources resourceAPI = APIResources.valueOf(resource);
		if(method.equalsIgnoreCase("POST")) 
			response = request.when().post(resourceAPI.getResource()); 				
		else if(method.equalsIgnoreCase("GET"))
			response = request.when().get(resourceAPI.getResource()); 				
		else if(method.equalsIgnoreCase("PUT"))
			response = request.when().put(resourceAPI.getResource()); 				
		else if(method.equalsIgnoreCase("DELETE"))
			response = request.when().delete(resourceAPI.getResource()); 				
	} 
   
	@Then("The API Call Get Success With SatusCode {int}")
	public void the_api_call_get_success_with_satus_code(Integer statusCode) {
		response.then().spec(responseSpecification()).extract().response();  
		assertEquals(response.getStatusCode(), 200);   
	}

	@And("{string} In Response Body Is {string}")
	public void in_response_body_is(String key, String expectedValue) {	
		assertEquals(GlobalProperties.getJsonPath(response,key), expectedValue);
	}
	  
	@And("Verify PlaceId Created Maps To {string} Using {string}")
	public void verify_placeId_created_maps_to_using(String expectedName, String resource) throws IOException {
		place_id = GlobalProperties.getJsonPath(response,"place_id");
		request = given().spec(requestSpecification()).queryParams("place_id",place_id);
		user_calls_using_http_request(resource,"GET");
		String name = GlobalProperties.getJsonPath(response,"name");
		assertEquals(name, expectedName); 
	}
	
	@Given("Add PlaceId To DeletePlace Payload")
	public void add_placeId_to_deleteplace_payload() throws IOException {
		request =given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}
	
}
