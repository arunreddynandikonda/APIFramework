package resources.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specs {

	public static RequestSpecification request;
	ResponseSpecification response;
//	String baseUrl = "https://rahulshettyacademy.com";

	public RequestSpecification requestSpecification() throws IOException {

		if (request == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			request = new RequestSpecBuilder().setBaseUri(GlobalProperties.getGolobalValues("baseUrl"))
					.setRelaxedHTTPSValidation().addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON).build();
			return request;
		}
		return request; 
	}
 
	public ResponseSpecification responseSpecification() {
		response = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
		return response;
	}

}
