package br.com.scf.controller.request;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class DespesaContaRequest implements Serializable {
	
	private static final long serialVersionUID = -6396578970239420268L;

	@NotNull
	private Integer contaId;

	@NotNull
	private Long despesaId;
	
	@NotNull(message = "{despesa.mes.not-null}")
	private Integer mes;

	@NotNull(message = "{despesa.valor.not-null}")
	private BigDecimal valor;

}
