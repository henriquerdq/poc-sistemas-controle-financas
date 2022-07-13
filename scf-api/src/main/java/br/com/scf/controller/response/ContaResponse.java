package br.com.scf.controller.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ContaResponse implements Serializable {
	
	private static final long serialVersionUID = -493537153537630851L;

	@JsonProperty("ano")
	private Integer ano;
	
	@JsonProperty("nome")
	private String nome;

}
