package br.com.scf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.scf.entity.Conta;
import br.com.scf.exception.FinancasNegocioException;
import br.com.scf.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository contaRepository;

	public Conta findById(Integer id) {
		return contaRepository.findById(id).orElseThrow(() -> new FinancasNegocioException("Conta não encontrada"));
	}

	@Cacheable(value = "contas")
	public Page<Conta> findAll(Pageable pageable) {
		return contaRepository.findAll(pageable);
	}
	
	public Conta incluir(Conta conta) {
		return contaRepository.save(conta);
	}

	public Conta alterar(Conta conta) {
		return contaRepository.save(conta);
	}
	
	public void excluir(Integer id) {
		contaRepository.deleteById(id);
	}

}
