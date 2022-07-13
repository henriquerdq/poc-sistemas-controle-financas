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

import br.com.scf.controller.assemble.ReceitaModelAssembler;
import br.com.scf.controller.mapper.ReceitaMapper;
import br.com.scf.controller.request.ReceitaRequest;
import br.com.scf.controller.response.ReceitaResponse;
import br.com.scf.service.ReceitaService;

@RestController
@RequestMapping("/receita")
public class ReceitaController {

	@Autowired
	private ReceitaService receitaService;

	@Autowired
	private ReceitaMapper mapper;

	@Autowired
	private ReceitaModelAssembler assembler;
	
	@Autowired
	private PagedResourcesAssembler<EntityModel<ReceitaResponse>> assemblerPage;

	@GetMapping(value = "/{id}")
	public EntityModel<ReceitaResponse> findById(@PathVariable(value = "id", required = true) Long id) {
		var receita = receitaService.findById(id);
		return assembler.toModel(receita);
	}

	@GetMapping
	public ResponseEntity<?> findAll(Pageable pageable) {

		var receitas = receitaService.findAll(pageable);

		var lista = receitas.stream().map(receita -> assembler.toModel(receita)).collect(Collectors.toList());

		var listaPage = new PageImpl<EntityModel<ReceitaResponse>>(lista, receitas.getPageable(),
				receitas.getTotalElements());

		return new ResponseEntity<>(assemblerPage.toModel(listaPage), HttpStatus.OK);
	}

	@PostMapping
	public EntityModel<ReceitaResponse> incluir(@Valid @RequestBody ReceitaRequest request) {
		var receita = receitaService.incluir(mapper.toReceita(request));
		return assembler.toModel(receita);
	}

	@PutMapping
	public EntityModel<ReceitaResponse> alterar(@Valid @RequestBody ReceitaRequest request) {
		var receita = receitaService.alterar(mapper.toReceita(request));
		return assembler.toModel(receita);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id", required = true) Long id) {
		receitaService.excluir(id);
		return ResponseEntity.ok().build();
	}
}
