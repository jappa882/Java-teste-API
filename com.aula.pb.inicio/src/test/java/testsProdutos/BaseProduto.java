package testsProdutos;


import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class BaseProduto {
	
	
	public void setupp () {
		RestAssured.baseURI = "http:localhost:3000/produtos";
		RestAssured.port = 3000;
		
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		
		RestAssured.responseSpecification = (ResponseSpecification) new ResponseSpecBuilder().
            expectContentType(ContentType.JSON);				
		
	}


}
