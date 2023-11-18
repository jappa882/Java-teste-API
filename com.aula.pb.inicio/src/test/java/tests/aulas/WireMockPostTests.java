package tests.aulas;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;

import tests.usuarios.User;

import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;


public class WireMockPostTests {

	
	@RegisterExtension
	static WireMockExtension wiremock = WireMockExtension.newInstance()
	     .options(wireMockConfig().
	    		 port(9999).
	    		 extensions(new ResponseTemplateTransformer(true)))
	     .build();
	
	@Test
	public void createsaNewUserTest() {
		User user = new User();
		user.setNome("mr lucas ");
		user.setEmail("mrlucas@pixar.com");
		user.setPassword("qwerty123");
		user.setAdministrador(true);
		
		given().
		    body(user).
		when().
		    post("http://localhost:9999/usuarios").
		then().
		    log().all().
		and().
		    assertThat().
		    statusCode(201).
		and().
		    body("name", equalTo(user.getNome())).	    
		and().
		    body("email", equalTo(user.getEmail()))
		    
		    
		;
	}

}
