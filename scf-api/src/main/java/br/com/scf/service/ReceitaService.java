package br.com.scf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.scf.entity.Receita;
import br.com.scf.exception.FinancasNegocioException;
import br.com.scf.repository.ReceitaRepository;

@Service
public class ReceitaService {

	@Autowired
	private ReceitaRepository receitaRepository;

	public Receita findById(Long id) {
		return receitaRepository.findById(id).orElseThrow(() -> new FinancasNegocioException("Receita não encontrada"));
	}

	public Page<Receita> findAll(Pageable pageable) {
		return receitaRepository.findAll(pageable);
	}
	
	public Receita incluir(Receita receita) {
		return receitaRepository.save(receita);
	}

	public Receita alterar(Receita receita) {
		return receitaRepository.save(receita);
	}
	
	public void excluir(Long id) {
		receitaRepository.deleteById(id);
	}

}
