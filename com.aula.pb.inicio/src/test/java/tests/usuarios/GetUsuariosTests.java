package tests.usuarios;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

import java.util.Locale;

import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import tests.usuarios.BaseUserLocal;



public class GetUsuariosTests {
	
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
		    body("quantidade", equalTo(6));// a cada novo usuario deve alterar o numero da lista
    }

	@Test
	public void devePegarUsuarioEspecifico() {
		user_id = "0uxuPY0cbmQhpEz1";
		User created_user = new User();
		created_user =
		when().
		    get("http://localhost:3000/usuarios/{id}", user_id).
		    as(User.class);
		assertThat(created_user.getNome(), equalTo("Fulano da Silva"));
		assertThat(created_user.getEmail(), equalTo("fulano@qa.com"));
		assertThat(created_user.getPassword(), equalTo("teste"));
		assertThat(created_user.getId(), equalTo("0uxuPY0cbmQhpEz1"));
	}
	
	
}
