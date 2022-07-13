package br.com.scf.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import br.com.scf.controller.request.ReceitaContaRequest;
import br.com.scf.controller.response.ReceitaContaResponse;
import br.com.scf.entity.ReceitaConta;

@Mapper(componentModel = "spring")
public interface ReceitaContaMapper {

	@Mappings({ 
		@Mapping(target = "contaId", source = "receita.id.conta.ano"),
		@Mapping(target = "receitaId", source = "receita.id.receita.id") 
	})
	ReceitaContaResponse toReceitaResponse(ReceitaConta receita);

	@Mappings({ 
		@Mapping(target = "id.conta.ano", source = "request.contaId"),
		@Mapping(target = "id.receita.id", source = "request.receitaId") 
	})
	ReceitaConta toReceitaConta(ReceitaContaRequest request);

}
