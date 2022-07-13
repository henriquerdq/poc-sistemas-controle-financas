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

import br.com.scf.controller.assemble.DespesaModelAssembler;
import br.com.scf.controller.mapper.DespesaMapper;
import br.com.scf.controller.request.DespesaRequest;
import br.com.scf.controller.response.DespesaResponse;
import br.com.scf.service.DespesaService;

@RestController
@RequestMapping("/despesa")
public class DespesaController {

	@Autowired
	private DespesaService despesaService;

	@Autowired
	private DespesaMapper mapper;

	@Autowired
	private DespesaModelAssembler assembler;
	
	@Autowired
	private PagedResourcesAssembler<EntityModel<DespesaResponse>> assemblerPage;

	@GetMapping(value = "/{id}")
	public EntityModel<DespesaResponse> findById(@PathVariable(value = "id", required = true) Long id) {
		var despesa = despesaService.findById(id);
		return assembler.toModel(despesa);
	}

	@GetMapping
	public ResponseEntity<?> findAll(Pageable pageable) {

		var despesas = despesaService.findAll(pageable);

		var lista = despesas.stream().map(despesa -> assembler.toModel(despesa)).collect(Collectors.toList());

		var listaPage = new PageImpl<EntityModel<DespesaResponse>>(lista, despesas.getPageable(),
				despesas.getTotalElements());

		return new ResponseEntity<>(assemblerPage.toModel(listaPage), HttpStatus.OK);
	}

	@PostMapping
	public EntityModel<DespesaResponse> incluir(@Valid @RequestBody DespesaRequest request) {
		var despesa = despesaService.incluir(mapper.toDespesa(request));
		return assembler.toModel(despesa);
	}

	@PutMapping
	public EntityModel<DespesaResponse> alterar(@Valid @RequestBody DespesaRequest request) {
		var despesa = despesaService.alterar(mapper.toDespesa(request));
		return assembler.toModel(despesa);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id", required = true) Long id) {
		despesaService.excluir(id);
		return ResponseEntity.ok().build();
	}
}
