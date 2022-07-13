package br.com.scf.controller.mapper;

import org.mapstruct.Mapper;

import br.com.scf.controller.request.ReceitaRequest;
import br.com.scf.controller.response.ReceitaResponse;
import br.com.scf.entity.Receita;

@Mapper(componentModel="spring")
public interface ReceitaMapper {

	ReceitaResponse toReceitaResponse(Receita receita);

	Receita toReceita(ReceitaRequest request);

}
