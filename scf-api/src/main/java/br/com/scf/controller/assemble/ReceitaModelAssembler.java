package br.com.scf.controller.assemble;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.scf.controller.ReceitaController;
import br.com.scf.controller.mapper.ReceitaMapper;
import br.com.scf.controller.response.ReceitaResponse;
import br.com.scf.entity.Receita;

@Component
public class ReceitaModelAssembler implements RepresentationModelAssembler<Receita, EntityModel<ReceitaResponse>> {

	@Autowired
	private ReceitaMapper mapper;

	@Override
	public EntityModel<ReceitaResponse> toModel(Receita receita) {
		var receitaResp = mapper.toReceitaResponse(receita);
		return EntityModel.of(receitaResp,
				linkTo(methodOn(ReceitaController.class).findById(receitaResp.getId())).withSelfRel());
	}

}
