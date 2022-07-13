package br.com.scf.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Embeddable
public class ReceitaContaId implements Serializable {
	
	private static final long serialVersionUID = 9219780404616211740L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_conta")
	private Conta conta;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_receita")
	private Receita receita;
	
}






