package tests.aulas;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;

@WireMockTest(httpPort = 9999)
public class WireMockGetTests {
	
	@Test
	public void getUsuariosDeveRetornarListaComWireMock() {
		String expectedName = "Bruce";
		when().
		    get("http://localhost:9999/usuarios").
		then().
		    log().all().
		and().
		    assertThat().
		    statusCode(200).
		and().
		    body("[0].name", equalTo(expectedName));
	}

}
