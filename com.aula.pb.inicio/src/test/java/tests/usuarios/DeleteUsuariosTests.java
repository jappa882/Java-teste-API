package tests.usuarios;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

import java.util.Locale;

import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;

public class DeleteUsuariosTests {
	
	
	@SuppressWarnings("unused")
	private static Faker faker = new Faker(Locale.ENGLISH);
	
	
	@Test
	public void deveExcluirUsuarioEspecifico() {
		User user = new User();
		user.setNome("Alvaro Robens");
		user.setEmail("audrie.runolfsson@hotmail.com");
		user.setPassword("br6nc9q9o9");
		user.setAdministrador(true);
		
		
	 given().
        body(user).
         contentType(ContentType.JSON).
	 when()
	    .delete("http://localhost:3000/usuarios/{id}", "CtLLCEDkuKKiu5d9").
	 then().
	    log().all().
	    and().
        assertThat().
        statusCode(200).
     and(). 
        body("message", equalTo("Registro excluído com sucesso"));
	 
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
	
	

}
