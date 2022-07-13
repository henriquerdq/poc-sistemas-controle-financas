package br.com.scf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.scf.entity.ReceitaConta;
import br.com.scf.entity.ReceitaContaId;

@Repository
public interface ReceitaContaRepository extends JpaRepository<ReceitaConta, ReceitaContaId> {
	
}
