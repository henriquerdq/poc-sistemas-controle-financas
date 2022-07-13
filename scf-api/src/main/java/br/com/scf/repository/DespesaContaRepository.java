package br.com.scf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.scf.entity.DespesaConta;
import br.com.scf.entity.DespesaContaId;

@Repository
public interface DespesaContaRepository extends JpaRepository<DespesaConta, DespesaContaId> {
	
}
