package com.aula.pb.inicio;


import static helper.ServiceHelper.matchesJsonSchema;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import io.restassured.response.Response;
import io.restassured.RestAssured;
import io.restassured.http.Method;


public class JsonSchemaFeio {
	
	@Test
	public void deveValidarSchemaNoRetornoDeListaDeUsuarios() {
		Response response = RestAssured.request(Method.GET, "https://serverest.dev/usuarios");
//		assertThat(response.asString(), matchesJsonSchema("usuarios", "get", 200));
		
		//path
		
	}
}    
