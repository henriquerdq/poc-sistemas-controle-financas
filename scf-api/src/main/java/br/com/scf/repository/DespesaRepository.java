package br.com.scf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.scf.entity.Despesa;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {
	
}
