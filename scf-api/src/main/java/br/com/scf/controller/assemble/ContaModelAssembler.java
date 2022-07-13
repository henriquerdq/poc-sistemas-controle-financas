package br.com.scf.controller.assemble;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.scf.controller.ContaController;
import br.com.scf.controller.mapper.ContaMapper;
import br.com.scf.controller.response.ContaResponse;
import br.com.scf.entity.Conta;

@Component
public class ContaModelAssembler implements RepresentationModelAssembler<Conta, EntityModel<ContaResponse>> {

	@Autowired
	private ContaMapper mapper;

	@Override
	public EntityModel<ContaResponse> toModel(Conta conta) {
		var contaRes = mapper.toContaResponse(conta);
		return EntityModel.of(contaRes,
				linkTo(methodOn(ContaController.class).findById(contaRes.getAno())).withSelfRel());
	}

}
