package br.com.scf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.scf.entity.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {
	
}
