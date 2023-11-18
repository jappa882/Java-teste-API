package testsProdutos;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
public class Product {
	private String nome;
	private Integer preco;
	private String descricao;
	private Integer quantidade;
	@JsonFormat(shape = Shape.STRING)
	@JsonProperty(value = "_id", access = Access.WRITE_ONLY)
	private String _id; // apenas lido na desserilização

	


}
