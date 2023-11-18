package testsCarrinhos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
public class Carrinho {

	private Integer precoTotal;
	private Integer quantidadeTotal;
	private String idUsuario;
	@JsonFormat(shape = Shape.STRING)
	@JsonProperty(value = "_id", access = Access.WRITE_ONLY)
	private String _id; // apenas lido na desserilização

}
