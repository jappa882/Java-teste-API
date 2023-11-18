package tests.aulas;


import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static helper.ServiceHelper.matchesJsonSchema;

import java.io.InputStream;

import org.junit.jupiter.api.Test;

import io.restassured.response.Response;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;


public class JsonSchema {
		
	@Test
	public void deveValidarSchemaNoRetornoDeListaDeUsuariosNovo() {
		Response response = when().get("https://compassuol.serverest.dev/usuarios");
		assertThat(response.asString(), matchesJsonSchema("usuarios", "get", 200));	
	
		
	}
		
  

}
