package com.aula.pb.inicio;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;

public class RestAssured {
	
	String url = "";
	
	@Test
	public void restAssuredPontape() {
		when().
              get("http://localhost:3000/lotto/{id}", 5).
        then().
              log().all().
              statusCode(200);
		
	}
	

}
