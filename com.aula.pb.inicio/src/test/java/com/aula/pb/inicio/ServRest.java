package com.aula.pb.inicio;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ServRest {
	//LOGIN TESTES
	//SE CASO TROCAR O E-MAIL OU NOME DE USUARIO DA ERRO 
	@Test 
	public void deveRealizarLogin() {
		String payload = "{\r\n"
				 + "  \"email\": \"fulano@qa.com\",\r\n"
				 + "  \"password\": \"teste\"\r\n"
				 + "}";
		given().
		    body(payload).
		    contentType(ContentType.JSON).
		when().
		    post("http://localhost:3000/login").
		then().
		    log().all();
        
	}
	
	@Test 
	public void deveRealizarLoginMap() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", "fulano@qa.com");
		params.put("password", "teste");
		
		given().
		    body(params).
		    contentType("application/JSON").
		when().
		    post("http://localhost:3000/login").
		then().
		    log().all();

		;
        
	}
	
	
	
		
//	USUARIOS TESTES
	@Test 
	public void deveRetornarListaUsuarios() {
		when().
		    get("http://localhost:3000/usuarios").
		then().
		    assertThat().
		    statusCode(200).
		and(). 
		    body("quantidade", equalTo(16));
    }
	
	@Test
	public void deveRetornarUsuarioExistente() {
	    when()
	        .get("http://localhost:3000/usuarios/{id}", "0uxuPY0cbmQhpEz1")
	    .then()
	        .assertThat()
	        .statusCode(200)
	    .and()    
	        .body("nome", equalTo("Fulano da Silva"))// se deixar o nome em branco da erro
		.and()
		    .body("email", equalTo("fulano@qa.com"));// se alterar o email dara erro
		
	} 
    
	@Test 
	public void deveCriarNovoUsuarioMap() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nome", "Lucas");
		params.put("email", "luc@mail.com");
		params.put("password", "umasenha");
		params.put("administrador", "true");
	
		given().
		     log().all().
	         contentType("application/JSON").
	         body(params).
	    when().
	         post("http://localhost:3000/usuarios").
	    then().   
	         log().all(). 
	         statusCode(201).
		     body("nome", is("Lucas")).
		     body("email", is("luc@mail.com")).
		     body("password", is("umasenha")).
		     body("administrador", is("true"))
		     
		 ;
	}
	
	@Test
	public void deveCriarNovoUsuario() {
		Date date = new Date();
		String email = date.getTime() + "@qa.com.br";
		
		String payload = "{\r\n"
				+ "  \"nome\": \"Pato Donald RestAssured\",\r\n"
				+ "  \"email\":\"" + email + "\",\r\n"
				+ "  \"password\": \"teste\",\r\n"
				+ "  \"administrador\": \"true\"\r\n"
	            + "}";
		
		given().
	     body(payload).
	     contentType(ContentType.JSON).
	when().
	     post("http://localhost:3000/usuarios").
	then().   
	     log().all(). 
	     assertThat().
	     statusCode(201).
	and().
        body("message", equalTo("Cadastro realizado com sucesso"));
	}

	@Test
	public void deveAtualizarUsuario() {
		String payload = "{\r\n"
				+ "  \"nome\": \"Pato Donald alterado via RestAssured\",\r\n"
				+ "  \"email\":\"pdrestassured@qa.com.br\",\r\n"
				+ "  \"password\": \"teste\",\r\n"
				+ "  \"administrador\": \"true\"\r\n"
	            + "}";
		given().
	        body(payload).
	         contentType(ContentType.JSON).
	    when().
	        put("http://localhost:3000/usuarios/{id}", "uZFO2crAOq6VDPLw").
	    then().   
	        log().all(). 
	        assertThat().
	        statusCode(200).
	    and().
	        body("message", equalTo("Registro alterado com sucesso"));

	}
	
	@Test
	public void deveExcluirUsuario() {
		//criar e excluir usuario
		Date date = new Date();
		String email = date.getTime() + "@qa.com.br";
		
		String payload = "{\r\n"
				+ "  \"nome\": \"Pato Donald RestAssured\",\r\n"
				+ "  \"email\":\"" + email + "\",\r\n"
				+ "  \"password\": \"teste\",\r\n"
				+ "  \"administrador\": \"true\"\r\n"
	            + "}";
		
		String id_para_excluir = 
		given().
		     body(payload).
		     contentType(ContentType.JSON).
		when().
		     post("http://localhost:3000/usuarios").
		then().   
		     log().all(). 
		     assertThat().
		     statusCode(201).
		and().
	         body("message", equalTo("Cadastro realizado com sucesso")).
	         and().
	    extract().
	         path("_id")
	    ;	
	
		
		
		
		when()
		     .delete("http://localhost:3000/usuarios/{id}", id_para_excluir)
		.then()
		     .log().all()
		     .and()
             .assertThat()
             .statusCode(200)
        .and() 
             .body("message", equalTo("Registro excluído com sucesso"));
	}
	
}
