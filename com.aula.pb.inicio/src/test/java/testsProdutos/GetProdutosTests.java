package testsProdutos;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Locale;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;
import testsLogin.Log;
import testsProdutos.Product;


public class GetProdutosTests extends BaseProduto{
	private static String IDproduto;
	private static String TKtoken;
	
	
    private static Faker faker = new Faker(Locale.ENGLISH);
 	
    public static Log log = new Log();
    
    private static Product product = new Product();
    
    @BeforeAll
    public static void Setup () {
    	log.setEmail("fulano@qa.com");
		log.setPassword("teste");
		
    
    
//	  Log na api
//    Receber op token
		
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
	public void deveRetornarListaProdutos() {
		
		when()
        .get("http://localhost:3000/produtos")
    .then()
        .log().all()
        .assertThat()
        .statusCode(200)
		.body("quantidade", equalTo(3));
		    
    }
    
    @Test
	public void devePegarProdutoPorIdEspecificoID() {
		product_id = "K6leHdftCeOJj8BJ";
		Product created_product = new Product();
		created_product =
		when().
		    get("http://localhost:3000/produtos/{id}", product_id).
		    as(Product.class);
		assertThat(created_product.getNome(), equalTo("Samsung 60 polegadas"));
		assertThat(created_product.getPreco(), equalTo(5240));
		assertThat(created_product.getDescricao(), equalTo("TV"));
		assertThat(created_product.getQuantidade(), equalTo(49977));
		assertThat(created_product.get_id(), equalTo("K6leHdftCeOJj8BJ"));
		
	}
    
    @Test
	public void deveRetornarProdutoExistenteID() {
        
	    when()
	        .get("http://localhost:3000/produtos/{id}", "BeeJh5lz3k6kSIzA")
	    .then()
	        .log().all()
	        .assertThat()
	        .statusCode(200)
	        .body("nome", equalTo("Logitech MX Vertical"))// se deixar o nome em branco da erro
		    .body("preco", equalTo(470))
	        .body("descricao", equalTo("Mouse"))
	        .body("quantidade", equalTo(382))
	        
	    
		;
	}
    
    
  	
}    


