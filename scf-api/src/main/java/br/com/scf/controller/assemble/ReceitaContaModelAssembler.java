package br.com.scf.controller.assemble;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.scf.controller.ReceitaContaController;
import br.com.scf.controller.mapper.ReceitaContaMapper;
import br.com.scf.controller.response.ReceitaContaResponse;
import br.com.scf.entity.ReceitaConta;

@Component
public class ReceitaContaModelAssembler implements RepresentationModelAssembler<ReceitaConta, EntityModel<ReceitaContaResponse>> {

	@Autowired
	private ReceitaContaMapper mapper;

	@Override
	public EntityModel<ReceitaContaResponse> toModel(ReceitaConta receita) {
		var despesaRes = mapper.toReceitaResponse(receita);
		return EntityModel.of(despesaRes,
				linkTo(methodOn(ReceitaContaController.class).findById(despesaRes.getReceitaId(), despesaRes.getContaId()))
						.withSelfRel());
	}

}
