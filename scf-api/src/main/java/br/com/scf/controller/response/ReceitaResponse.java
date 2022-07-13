package br.com.scf.controller.response;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class ReceitaResponse implements Serializable {

	private static final long serialVersionUID = -3083945447042305318L;

	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal valor;

}
