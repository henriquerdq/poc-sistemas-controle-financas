package br.com.scf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.scf.entity.Despesa;
import br.com.scf.exception.FinancasNegocioException;
import br.com.scf.repository.DespesaRepository;

@Service
public class DespesaService {

	@Autowired
	private DespesaRepository despesaRepository;

	public Despesa findById(Long id) {
		return despesaRepository.findById(id).orElseThrow(() -> new FinancasNegocioException("Despesa não encontrada"));
	}

	public Page<Despesa> findAll(Pageable pageable) {
		return despesaRepository.findAll(pageable);
	}
	
	public Despesa incluir(Despesa despesa) {
		return despesaRepository.save(despesa);
	}

	public Despesa alterar(Despesa despesa) {
		return despesaRepository.save(despesa);
	}
	
	public void excluir(Long id) {
		despesaRepository.deleteById(id);
	}

}
