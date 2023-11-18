package testsCarrinhos;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import java.util.Locale;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;
import testsLogin.Log;
import testsProdutos.Product;
import testsCarrinhos.Carrinho;

public class PostCarrinhosTests extends BaseCarrinho{
	private static String IDproduto;
	private static String TKtoken;
	
	
    private static Faker faker = new Faker(Locale.ENGLISH);
 	
    public static Log log = new Log();
    
    private static Product product = new Product();
    
    private static Carrinho carrinho = new Carrinho();
    
    @BeforeAll
    public static void Setup () {
    	log.setEmail("fulano@qa.com");
		log.setPassword("teste");
		
		
		
//			Log na api
//		    Receber op token
				
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
	public void deveCriarNovoCarrinho() {
		Carrinho carrinho = new Carrinho();
		carrinho.setPrecoTotal(2000);
		carrinho.setQuantidadeTotal(600);
		carrinho.setIdUsuario("TS0KQhrBZYT3aGPa");
		
		
		
		given(). 
		     header("Authorization", log.getAuthorization()).
		     body(carrinho).
		     contentType(ContentType.JSON).
		when(). 
		     post("http://localhost:3000/carrinhos"). 
		then(). 
		     statusCode(400).
		     body("message", is("Cadastro realizado com sucesso"));
		     
		     	
		
	}
    

}
