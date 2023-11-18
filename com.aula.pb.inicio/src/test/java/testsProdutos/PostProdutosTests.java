package testsProdutos;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import java.util.Locale;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;
import testsLogin.Log;

public class PostProdutosTests extends BaseProduto{
	
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
    
    
    private String product_id;
    
    @Test
	public void deveCriarNovoProduto() {
		Product product = new Product();
		product.setNome("Airsoft");
		product.setPreco(1000);
		product.setDescricao("Arma para Paintball");
		product.setQuantidade(10);
		
		
		
		given(). 
		     header("Authorization", log.getAuthorization()).
		     body(product).
		     contentType(ContentType.JSON).
		when(). 
		     post("http://localhost:3000/produtos"). 
		then(). 
		     statusCode(400).
		     body("message", is("Cadastro realizado com sucesso"));
		     
		     	
		
	}
    
    
}
