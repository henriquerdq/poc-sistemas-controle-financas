package br.com.scf.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.scf.auth.entity.Permission;
import br.com.scf.auth.repository.PermissionRepository;

@Service
public class PermissionService {

	@Autowired
	private PermissionRepository permissionRepository;

	public Permission findByDescription(String description) throws UsernameNotFoundException {
		return permissionRepository.findByDescription(description).stream().findFirst().orElse(null);
	}

	public Permission save(Permission permission) {
		return permissionRepository.save(permission);
	}

}
