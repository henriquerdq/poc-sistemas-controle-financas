package br.com.scf.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.scf.auth.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long>{

	List<Permission> findByDescription(String description);

}
