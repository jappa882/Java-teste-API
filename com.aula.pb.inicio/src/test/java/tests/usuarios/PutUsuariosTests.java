package tests.usuarios;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.Locale;

import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;

public class PutUsuariosTests {
	
	@SuppressWarnings("unused")
	private static Faker faker = new Faker(Locale.ENGLISH);
	
	
	@Test
	public void deveAtualizarUsuario() {
		User user = new User();
		user.setNome("Luid alter");
		user.setEmail("alter@qa.com.br");
		user.setPassword("luid000");
		user.setAdministrador(true);
		
		
		given().
	        body(user).
	         contentType(ContentType.JSON).
	    when().
	        put("http://localhost:3000/usuarios/{id}", "mJt3IeAAgUyWqEqj").
	    then().   
	        log().all(). 
	        assertThat().
	        statusCode(200).
	    and().
	        body("message", equalTo("Registro alterado com sucesso"));

	}
	
	

}
