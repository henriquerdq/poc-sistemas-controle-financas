package br.com.scf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.scf.entity.Receita;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {
	
}
