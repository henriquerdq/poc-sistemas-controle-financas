package br.com.scf.controller.request;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ReceitaContaRequest implements Serializable {
	
	private static final long serialVersionUID = 7818408893306062175L;

	@NotNull
	private Integer contaId;

	@NotNull
	private Long receitaId;
	
	@NotNull(message = "{receita.mes.not-null}")
	private Integer mes;

	@NotNull(message = "{receita.valor.not-null}")
	private BigDecimal valor;

}
