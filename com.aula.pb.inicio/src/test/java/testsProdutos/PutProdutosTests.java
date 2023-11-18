package testsProdutos;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.Locale;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;
import testsLogin.Log;

public class PutProdutosTests extends BaseProduto{
	
	private static String IDproduto;
	private static String TKtoken;
	
    private static Faker faker = new Faker(Locale.ENGLISH);
 	
    public static Log log = new Log();
    
    private static Product product = new Product();
    
    @BeforeAll
    public static void Setup () {
    	log.setEmail("fulano@qa.com");
		log.setPassword("teste");
	
//		Log na api
//	    Receber op token
			
	          String TKtoken =		
			    given().
			        log().all().
			        body(log).
			        contentType(ContentType.JSON).
			    when().
			        post("http://localhost:3000/login").
			    then().
			        log().all().
			        statusCode(200).
			        extract().path("authorization");
			        log.setAuthorization(TKtoken);	
		
    }
    
    
    
    @Test
	public void deveAtualizarProduto() {
    	Product product = new Product();
		product.setNome("NBA alterado");
		product.setPreco(69);
		product.setDescricao("Moletom alterado");
		product.setQuantidade(5);
		
		
		given().
		    header("Authorization", log.getAuthorization()).
	        body(product).
	        contentType(ContentType.JSON).
	    when().
	        put("http://localhost:3000/produtos/{id}", "TS0KQhrBZYT3aGPa").
	    then().   
	        log().all(). 
	        assertThat().
	        statusCode(200).
	    and().
	        body("message", equalTo("Registro alterado com sucesso"));

	}

}
