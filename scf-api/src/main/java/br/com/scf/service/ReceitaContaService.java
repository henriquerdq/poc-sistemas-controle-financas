package br.com.scf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.scf.entity.ReceitaConta;
import br.com.scf.entity.ReceitaContaId;
import br.com.scf.exception.FinancasNegocioException;
import br.com.scf.repository.ReceitaContaRepository;

@Service
public class ReceitaContaService {

	@Autowired
	private ReceitaContaRepository repository;

	public ReceitaConta findById(ReceitaContaId id) {
		return repository.findById(id).orElseThrow(() -> new FinancasNegocioException("Receita não encontrada"));
	}

	public Page<ReceitaConta> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	public ReceitaConta incluir(ReceitaConta despesa) {
		return repository.save(despesa);
	}

	public ReceitaConta alterar(ReceitaConta despesa) {
		return repository.save(despesa);
	}
	
	public void excluir(ReceitaContaId id) {
		repository.deleteById(id);
	}

}
