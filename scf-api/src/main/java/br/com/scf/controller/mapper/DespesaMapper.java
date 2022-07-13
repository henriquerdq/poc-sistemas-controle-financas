package br.com.scf.controller.mapper;

import org.mapstruct.Mapper;

import br.com.scf.controller.request.DespesaRequest;
import br.com.scf.controller.response.DespesaResponse;
import br.com.scf.entity.Despesa;

@Mapper(componentModel="spring")
public interface DespesaMapper {

	DespesaResponse toDespesaResponse(Despesa despesa);

	Despesa toDespesa(DespesaRequest request);

}
