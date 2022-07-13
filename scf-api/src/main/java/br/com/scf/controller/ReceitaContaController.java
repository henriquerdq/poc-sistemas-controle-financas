package br.com.scf.controller;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.scf.controller.assemble.ReceitaContaModelAssembler;
import br.com.scf.controller.mapper.ReceitaContaMapper;
import br.com.scf.controller.request.ReceitaContaRequest;
import br.com.scf.controller.response.ReceitaContaResponse;
import br.com.scf.entity.Conta;
import br.com.scf.entity.Receita;
import br.com.scf.entity.ReceitaContaId;
import br.com.scf.service.ReceitaContaService;

@RestController
@RequestMapping("/receita-conta")
public class ReceitaContaController {

	@Autowired
	private ReceitaContaService receitaService;

	@Autowired
	private ReceitaContaMapper mapper;

	@Autowired
	private ReceitaContaModelAssembler assembler;
	
	@Autowired
	private PagedResourcesAssembler<EntityModel<ReceitaContaResponse>> assemblerPage;

	@GetMapping(value = "/{receitaId}/{contaId}")
	public EntityModel<ReceitaContaResponse> findById(@PathVariable(value = "receitaId", required = true) Long receitaId,
			@PathVariable(value = "contaId", required = true) Integer contaId) {
		var id = ReceitaContaId.builder().conta(Conta.builder().ano(contaId).build())
				.receita(Receita.builder().id(receitaId).build()).build();
		var receita = receitaService.findById(id);
		return assembler.toModel(receita);
	}

	@GetMapping
	public ResponseEntity<?> findAll(Pageable pageable) {

		var receitas = receitaService.findAll(pageable);

		var lista = receitas.stream().map(receita -> assembler.toModel(receita)).collect(Collectors.toList());

		var listaPage = new PageImpl<EntityModel<ReceitaContaResponse>>(lista, receitas.getPageable(),
				receitas.getTotalElements());

		return new ResponseEntity<>(assemblerPage.toModel(listaPage), HttpStatus.OK);
	}

	@PostMapping
	public EntityModel<ReceitaContaResponse> incluir(@Valid @RequestBody ReceitaContaRequest request) {
		var receita = receitaService.incluir(mapper.toReceitaConta(request));
		return assembler.toModel(receita);
	}

	@PutMapping
	public EntityModel<ReceitaContaResponse> alterar(@Valid @RequestBody ReceitaContaRequest request) {
		var receita = receitaService.alterar(mapper.toReceitaConta(request));
		return assembler.toModel(receita);
	}

	@DeleteMapping(value = "/{receitaId}/{contaId}")
	public ResponseEntity<?> delete(@PathVariable(value = "receitaId", required = true) Long receitaId,
			@PathVariable(value = "contaId", required = true) Integer contaId) {
		var id = ReceitaContaId.builder().conta(Conta.builder().ano(contaId).build())
				.receita(Receita.builder().id(receitaId).build()).build();
		receitaService.excluir(id);
		return ResponseEntity.ok().build();
	}
}
