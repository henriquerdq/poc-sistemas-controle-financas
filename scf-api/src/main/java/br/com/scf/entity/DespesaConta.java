package br.com.scf.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_despesa_conta")
public class DespesaConta implements Serializable {
	
	private static final long serialVersionUID = -3359628244265605061L;

	@EmbeddedId
	private DespesaContaId id;
	
	@Column(name = "mes", nullable = false)
	private Integer mes;
	
	@Column(name = "valor", nullable = false)
	private BigDecimal valor;
	
}






