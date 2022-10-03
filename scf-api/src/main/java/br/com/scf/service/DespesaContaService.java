package br.com.scf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.scf.entity.DespesaConta;
import br.com.scf.entity.DespesaContaId;
import br.com.scf.exception.FinancasNegocioException;
import br.com.scf.repository.DespesaContaRepository;

@Service
public class DespesaContaService {

	@Autowired
	private DespesaContaRepository repository;

	public DespesaConta findById(DespesaContaId id) {
		return repository.findById(id).orElseThrow(() -> new FinancasNegocioException("Despesa não encontrada"));
	}

	@Cacheable(value = "despesas")
	public Page<DespesaConta> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	public DespesaConta incluir(DespesaConta despesa) {
		if (repository.existsById(despesa.getId())) {
			throw new FinancasNegocioException("Despesa já cadastrada");
		}
		return repository.save(despesa);
	}

	public DespesaConta alterar(DespesaConta despesa) {
		if (repository.existsById(despesa.getId())) {
			return repository.save(despesa);
		}
		throw new FinancasNegocioException("Despesa não encontrada");
	}
	
	public void excluir(DespesaContaId id) {
		if (!repository.existsById(id)) {
			throw new FinancasNegocioException("Despesa não encontrada");
		}
		repository.deleteById(id);
	}

}
