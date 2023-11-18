package tests.usuarios;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.Locale;

import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;

public class PostUsuariosTests {
	
	private static Faker faker = new Faker(Locale.ENGLISH);
	
	
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
		user.setNome("Mickey mous");
		user.setEmail("mickey@bross.com");
		user.setPassword("lucas9078");
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

}
