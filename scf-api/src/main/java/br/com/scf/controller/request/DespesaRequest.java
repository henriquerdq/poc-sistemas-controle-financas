package br.com.scf.controller.request;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class DespesaRequest implements Serializable {
	
	private static final long serialVersionUID = -8757543839784627802L;

	private Long id;

	@NotNull(message = "{despesa.nome.not-null}")
	@NotBlank(message = "{despesa.nome.not-blank}")
	@Size(max = 50, message = "{despesa.nome.size}")
	private String nome;
	
	@Size(max = 100, message = "{despesa.descricao.size}")
	private String descricao;
	
	@NotNull(message = "{despesa.valor.not-null}")
	private BigDecimal valor;

}
