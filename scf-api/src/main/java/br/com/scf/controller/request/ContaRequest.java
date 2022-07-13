package br.com.scf.controller.request;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ContaRequest implements Serializable {
	
	private static final long serialVersionUID = -8757543839784627802L;

	@NotNull(message = "{conta.ano.not-null}")
	@Min(value = 4, message = "{conta.ano.size}")
	private Integer ano;

	@NotNull(message = "{conta.nome.not-null}")
	@NotBlank(message = "{conta.nome.not-blank}")
	@Size(max = 50, message = "{conta.nome.size}")
	private String nome;

}
