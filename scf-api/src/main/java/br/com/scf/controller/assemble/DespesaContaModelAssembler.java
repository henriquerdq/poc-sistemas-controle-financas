package br.com.scf.controller.assemble;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.scf.controller.DespesaContaController;
import br.com.scf.controller.mapper.DespesaContaMapper;
import br.com.scf.controller.response.DespesaContaResponse;
import br.com.scf.entity.DespesaConta;

@Component
public class DespesaContaModelAssembler implements RepresentationModelAssembler<DespesaConta, EntityModel<DespesaContaResponse>> {

	@Autowired
	private DespesaContaMapper mapper;

	@Override
	public EntityModel<DespesaContaResponse> toModel(DespesaConta despesa) {
		var despesaRes = mapper.toDespesaResponse(despesa);
		return EntityModel.of(despesaRes,
				linkTo(methodOn(DespesaContaController.class).findById(despesaRes.getDespesaId(), despesaRes.getContaId()))
						.withSelfRel());
	}

}
