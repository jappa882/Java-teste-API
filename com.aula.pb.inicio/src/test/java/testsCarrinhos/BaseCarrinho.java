package testsCarrinhos;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class BaseCarrinho {
	public void setupp () {
		RestAssured.baseURI = "http:localhost:3000/carrinhos";
		RestAssured.port = 3000;
		
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		
		RestAssured.responseSpecification = (ResponseSpecification) new ResponseSpecBuilder().
            expectContentType(ContentType.JSON);				
		
	}

}
