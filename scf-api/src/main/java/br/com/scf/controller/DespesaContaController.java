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

import br.com.scf.controller.assemble.DespesaContaModelAssembler;
import br.com.scf.controller.mapper.DespesaContaMapper;
import br.com.scf.controller.request.DespesaContaRequest;
import br.com.scf.controller.response.DespesaContaResponse;
import br.com.scf.entity.Conta;
import br.com.scf.entity.Despesa;
import br.com.scf.entity.DespesaContaId;
import br.com.scf.service.DespesaContaService;

@RestController
@RequestMapping("/despesa-conta")
public class DespesaContaController {

	@Autowired
	private DespesaContaService despesaService;

	@Autowired
	private DespesaContaMapper mapper;

	@Autowired
	private DespesaContaModelAssembler assembler;
	
	@Autowired
	private PagedResourcesAssembler<EntityModel<DespesaContaResponse>> assemblerPage;

	@GetMapping(value = "/{despesaId}/{contaId}")
	public EntityModel<DespesaContaResponse> findById(@PathVariable(value = "despesaId", required = true) Long despesaId,
			@PathVariable(value = "contaId", required = true) Integer contaId) {
		var id = DespesaContaId.builder().conta(Conta.builder().ano(contaId).build())
				.despesa(Despesa.builder().id(despesaId).build()).build();
		var despesa = despesaService.findById(id);
		return assembler.toModel(despesa);
	}

	@GetMapping
	public ResponseEntity<?> findAll(Pageable pageable) {

		var despesas = despesaService.findAll(pageable);

		var lista = despesas.stream().map(despesa -> assembler.toModel(despesa)).collect(Collectors.toList());

		var listaPage = new PageImpl<EntityModel<DespesaContaResponse>>(lista, despesas.getPageable(),
				despesas.getTotalElements());

		return new ResponseEntity<>(assemblerPage.toModel(listaPage), HttpStatus.OK);
	}

	@PostMapping
	public EntityModel<DespesaContaResponse> incluir(@Valid @RequestBody DespesaContaRequest request) {
		var despesa = despesaService.incluir(mapper.toDespesaConta(request));
		return assembler.toModel(despesa);
	}

	@PutMapping
	public EntityModel<DespesaContaResponse> alterar(@Valid @RequestBody DespesaContaRequest request) {
		var despesa = despesaService.alterar(mapper.toDespesaConta(request));
		return assembler.toModel(despesa);
	}

	@DeleteMapping(value = "/{despesaId}/{contaId}")
	public ResponseEntity<?> delete(@PathVariable(value = "despesaId", required = true) Long despesaId,
			@PathVariable(value = "contaId", required = true) Integer contaId) {
		var id = DespesaContaId.builder().conta(Conta.builder().ano(contaId).build())
				.despesa(Despesa.builder().id(despesaId).build()).build();
		despesaService.excluir(id);
		return ResponseEntity.ok().build();
	}
}
