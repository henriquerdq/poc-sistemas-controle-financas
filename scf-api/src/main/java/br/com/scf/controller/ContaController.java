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

import br.com.scf.controller.assemble.ContaModelAssembler;
import br.com.scf.controller.mapper.ContaMapper;
import br.com.scf.controller.request.ContaRequest;
import br.com.scf.controller.response.ContaResponse;
import br.com.scf.service.ContaService;

@RestController
@RequestMapping("/conta")
public class ContaController {

	@Autowired
	private ContaService contaService;

	@Autowired
	private ContaMapper mapper;

	@Autowired
	private ContaModelAssembler assembler;
	
	@Autowired
	private PagedResourcesAssembler<EntityModel<ContaResponse>> assemblerPage;

	@GetMapping(value = "/{id}")
	public EntityModel<ContaResponse> findById(@PathVariable(value = "id", required = true) Integer id) {
		var conta = contaService.findById(id);
		return assembler.toModel(conta);
	}

	@GetMapping
	public ResponseEntity<?> findAll(Pageable pageable) {

		var contas = contaService.findAll(pageable);

		var lista = contas.stream().map(conta -> assembler.toModel(conta)).collect(Collectors.toList());

		var listaPage = new PageImpl<EntityModel<ContaResponse>>(lista, contas.getPageable(),
				contas.getTotalElements());

		return new ResponseEntity<>(assemblerPage.toModel(listaPage), HttpStatus.OK);
	}

	@PostMapping
	public EntityModel<ContaResponse> incluir(@Valid @RequestBody ContaRequest request) {
		var conta = contaService.incluir(mapper.toConta(request));
		return assembler.toModel(conta);
	}

	@PutMapping
	public EntityModel<ContaResponse> alterar(@Valid @RequestBody ContaRequest request) {
		var conta = contaService.alterar(mapper.toConta(request));
		return assembler.toModel(conta);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id", required = true) Integer id) {
		contaService.excluir(id);
		return ResponseEntity.ok().build();
	}
}
