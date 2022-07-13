package br.com.scf.controller.response;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class ReceitaContaResponse implements Serializable {
	
	private static final long serialVersionUID = -4200607825468989328L;
	
	private Integer contaId;
	private Long receitaId;
	private BigDecimal valor;
	private Integer mes;

}
