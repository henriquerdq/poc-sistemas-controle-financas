package br.com.scf.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.scf.controller.request.ContaRequest;
import br.com.scf.controller.response.ContaResponse;
import br.com.scf.entity.Conta;

@Mapper(componentModel="spring")
public interface ContaMapper {

	ContaResponse toContaResponse(Conta conta);

	@Mapping(target = "receitas", ignore = true)
	@Mapping(target = "despesas", ignore = true)
	Conta toConta(ContaRequest request);

}
