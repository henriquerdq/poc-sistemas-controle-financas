package br.com.scf.entity;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tb_conta")
@Audited
public class Conta implements Serializable {
	
	private static final long serialVersionUID = -3948266812159720095L;
	
	@Id
	@Column(name = "id_conta", nullable = false, unique = true, length = 4)
	private Integer ano;

	@Column(name = "nome", nullable = false, length = 50)
	private String nome;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_receita")
	private List<ReceitaConta> receitas;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_conta")
	private List<DespesaConta> despesas;
	
}




