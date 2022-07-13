package br.com.scf.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.scf.auth.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	List<User> findByUsername(String username);

}
