package br.com.scf.controller.assemble;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.scf.controller.DespesaController;
import br.com.scf.controller.mapper.DespesaMapper;
import br.com.scf.controller.response.DespesaResponse;
import br.com.scf.entity.Despesa;

@Component
public class DespesaModelAssembler implements RepresentationModelAssembler<Despesa, EntityModel<DespesaResponse>> {

	@Autowired
	private DespesaMapper mapper;

	@Override
	public EntityModel<DespesaResponse> toModel(Despesa despesa) {
		var despesaRes = mapper.toDespesaResponse(despesa);
		return EntityModel.of(despesaRes,
				linkTo(methodOn(DespesaController.class).findById(despesaRes.getId())).withSelfRel());
	}

}
