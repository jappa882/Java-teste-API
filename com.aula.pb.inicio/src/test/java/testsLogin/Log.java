package testsLogin;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;


@Data
public class Log {
	private String email;
	private String password;
	@JsonProperty(value = "authorization", access = Access.WRITE_ONLY)
	private String authorization; // apenas lido na desserilização
		
}


