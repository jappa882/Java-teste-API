package testsProdutos;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.Locale;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;
import testsLogin.Log;

public class DeleteProdutosTests {
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
	public void deveExcluirProdutoEspecifico() {
		Product product = new Product();
		product.setNome("Bob esponja");
		product.setPreco(600);
		product.setDescricao("Camiseta");
		product.setQuantidade(1);
		
		
	 given().
	     header("Authorization", log.getAuthorization()).
         body(product).
         contentType(ContentType.JSON).
	 when()
	    .delete("http://localhost:3000/produtos/{id}", "m0canUxwBzFSffiV").
	 then().
	    log().all().
	    and().
        assertThat().
        statusCode(200).
     and(). 
        body("message", equalTo("Registro excluído com sucesso"));
	}

}
