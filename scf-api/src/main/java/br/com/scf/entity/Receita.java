package br.com.scf.entity;


import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tb_receita")
public class Receita implements Serializable {
	
	private static final long serialVersionUID = 7941763390118968225L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", nullable = false, length = 50)
	private String nome;

	@Column(name = "descricao", length = 100)
	private String descricao;

	@Column(name = "valor", nullable = false)
	private BigDecimal valor;
	
}




