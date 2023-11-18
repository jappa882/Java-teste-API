package tests.usuarios;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

//Data cria getter e setter para a classe
@Data
public class User {
	private String nome;
	private String email;
	private String password;
	@JsonFormat(shape = Shape.STRING)
	private boolean administrador;
	@JsonProperty(value = "_id", access = Access.WRITE_ONLY)
	private String id; // apenas lido na desserilização
	

	
}
