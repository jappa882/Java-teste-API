package testsLogin;

import static io.restassured.RestAssured.given;

import java.util.Locale;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;

public class PostLoginTests extends BaseLogin{
public String TKtoken;
    
	@SuppressWarnings("unused")
	private static Faker faker = new Faker(Locale.ENGLISH);
		
	    private static Log log = new Log();
	    
	    @BeforeAll
	    public static void Setup () {
	    	log.setEmail("fulano@qa.com");
			log.setPassword("teste");
			
			
//		  Log na api
//	      Receber op token
			
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
		public void deveFazerAutenticacaoComTokenUsuarios() {
			
			log.setEmail("fulano@qa.com");
			log.setPassword("teste");
			
			
//		  Log na api
//	      Receber op token
			
	            TKtoken =		
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
			    
			    //Obter as contas
			    
			    given().
		            log().all().
		            header("Authorization",  log.getAuthorization()).
		        when().
		            get("http://localhost:3000/usuarios").
		        then().
		            log().all(). 
		            statusCode(200).
		            extract().path("authorization");
		               
		   
		}
	    
	    @Test
		public void deveFazerAutenticacaoComTokenProdutos() {
			
			log.setEmail("fulano@qa.com");
			log.setPassword("teste");
			
			
//		  Log na api
//	      Receber op token
			
	            TKtoken =		
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
			    
			    //Obter as contas
			    
			    given().
		            log().all().
		            header("Authorization",  log.getAuthorization()).
		        when().
		            get("http://localhost:3000/produtos").
		        then().
		            log().all(). 
		            statusCode(200).
		            extract().path("authorization");
		               
		   
		}
	    
	    @Test
		public void deveFazerAutenticacaoComTokenCarrinhos() {
			
			log.setEmail("fulano@qa.com");
			log.setPassword("teste");
			
			
//		  Log na api
//	      Receber op token
			
	            TKtoken =		
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
			    
			    //Obter as contas
			    
			    given().
		            log().all().
		            header("Authorization",  log.getAuthorization()).
		        when().
		            get("http://localhost:3000/carrinhos").
		        then().
		            log().all(). 
		            statusCode(200).
		            extract().path("authorization");
		               
		   
		}
		
		
		
		
		


}
