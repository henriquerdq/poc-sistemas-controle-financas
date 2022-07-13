package br.com.scf.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import br.com.scf.controller.request.DespesaContaRequest;
import br.com.scf.controller.response.DespesaContaResponse;
import br.com.scf.entity.DespesaConta;

@Mapper(componentModel = "spring")
public interface DespesaContaMapper {

	@Mappings({ 
		@Mapping(target = "contaId", source = "despesa.id.conta.ano"),
		@Mapping(target = "despesaId", source = "despesa.id.despesa.id") 
	})
	DespesaContaResponse toDespesaResponse(DespesaConta despesa);

	@Mappings({ 
		@Mapping(target = "id.conta.ano", source = "request.contaId"),
		@Mapping(target = "id.despesa.id", source = "request.despesaId") 
	})
	DespesaConta toDespesaConta(DespesaContaRequest request);

}
