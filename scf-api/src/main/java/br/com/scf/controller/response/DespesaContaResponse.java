package br.com.scf.controller.response;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class DespesaContaResponse implements Serializable {
	
	private static final long serialVersionUID = -1860614568545386695L;
	
	private Integer contaId;
	private Long despesaId;
	private BigDecimal valor;
	private Integer mes;

}
