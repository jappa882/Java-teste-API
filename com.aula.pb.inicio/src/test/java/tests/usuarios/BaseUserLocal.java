package tests.usuarios;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class BaseUserLocal {
	public void setup() {
		RestAssured.baseURI = "http:localhost:3000/usuarios";
		RestAssured.port = 3000;
		
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		
		RestAssured.responseSpecification = (ResponseSpecification) new ResponseSpecBuilder().
            expectContentType(ContentType.JSON);				
		
	}

}
