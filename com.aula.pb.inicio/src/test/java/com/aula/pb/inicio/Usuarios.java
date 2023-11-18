package com.aula.pb.inicio;

import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakerIDN;

import java.util.Date;
import java.util.Locale;

import io.restassured.http.ContentType;
import tests.usuarios.User;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.*;

import com.github.javafaker.Faker;

@SuppressWarnings("unused")
public class Usuarios {
	private String user_id;
	private static Faker faker = new Faker(Locale.ENGLISH);
	
	@Test 
	public void deveRetornarListaUsuarios() {
		User user = new User();
		user.setNome(faker.name().fullName());
		user.setEmail(faker.internet().emailAddress());
		user.setPassword(faker.internet().password());
		user.setAdministrador(true);
		given().
		    body(user).
		when().
		    get("http://localhost:3000/usuarios").
		then().
		    assertThat().
		    statusCode(200).
		and(). 
		    body("quantidade", equalTo(3));// a cada novo usuario deve alterar o numero da lista
    }
	
	@Test
	public void deveCriarNovoUsuarioFakerDadosAleatorios() {
		User user = new User();
		user.setNome(faker.name().fullName());
		user.setEmail(faker.internet().emailAddress());
		user.setPassword(faker.internet().password());
		user.setAdministrador(true);
		
		given().
		    body(user).
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
	public void deveCriarNovoUsuario() {
		User user = new User();
		user.setNome("Testes lucas");
		user.setEmail("lucas10@teste.com");
		user.setPassword("lucas123");
		user.setAdministrador(true);
		
		given().
		    body(user).
		    contentType(ContentType.JSON).
		when().
		    post("http://localhost:3000/usuarios").
		then().
		    log().all().
		    assertThat().
		    statusCode(201).
		and().
		    body("message", equalTo("Cadastro realizado com sucesso"));//deve sempre atualizar usuario 
		                                                               //caso ja esteja cadastrado o email ja existe
		
	}
	
	@Test
	public void devePegarUsuarioEspecifico() {
		user_id = "vfgvL5MAeTiWt2Zm";
		User created_user = new User();
		created_user =
		when().
		    get("http://localhost:3000/usuarios/{id}", user_id).
		    as(User.class);
		assertThat(created_user.getNome(), equalTo("Lucas faker alter"));
		assertThat(created_user.getEmail(), equalTo("lucalter@qa.com.br"));
		assertThat(created_user.getPassword(), equalTo("outrasenha"));
		assertThat(created_user.getId(), equalTo("vfgvL5MAeTiWt2Zm"));
	}
	
	@Test
	public void deveAtualizarUsuario() {
		User user = new User();
		user.setNome("Mario alter");
		user.setEmail("Mariobross@qa.com.br");
		user.setPassword("luidemario");
		user.setAdministrador(true);
		
		
		given().
	        body(user).
	         contentType(ContentType.JSON).
	    when().
	        put("http://localhost:3000/usuarios/{id}", "FKTl0IWCoj6aeTyI").
	    then().   
	        log().all(). 
	        assertThat().
	        statusCode(200).
	    and().
	        body("message", equalTo("Registro alterado com sucesso"));

	}
	
	@Test
	public void deveCriarUsuariodeveExcluirUsuario() {
		//criar e excluir usuario
		User user = new User();
		user.setNome("novo user");
		user.setEmail("excluirteste@qa.com");
		user.setPassword("123adc");
		user.setAdministrador(true);
		
		String id_para_excluir = 
		given().
		     body(user).
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
	
	@Test
	public void deveExcluirUsuarioEspecifico() {
		User user = new User();
		user.setNome("Scarlett Schmeler");
		user.setEmail("jerold.turcotte@hotmail.com");
		user.setPassword("fe2mkrk37u");
		user.setAdministrador(true);
		
		
	 given().
        body(user).
         contentType(ContentType.JSON).
	 when()
	    .delete("http://localhost:3000/usuarios/{id}", "MM5NMbf7JTe4spBv").
	 then().
	    log().all().
	    and().
        assertThat().
        statusCode(200).
     and(). 
        body("message", equalTo("Registro excluído com sucesso"));
	}

}
