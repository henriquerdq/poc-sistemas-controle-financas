package br.com.scf.auth;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.scf.auth.entity.Permission;
import br.com.scf.auth.entity.User;
import br.com.scf.auth.service.PermissionService;
import br.com.scf.auth.service.UserService;

@SpringBootApplication
public class AuthApplication {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init() {
		return args -> {
			initUsers();
		};

	}

	private void initUsers() {
		Permission permission = carregarPermissao();
		carregarUsuario(permission);
	}

	private void carregarUsuario(Permission permission) {
		User find = userService.findByUsername("henriquerdq");
		if (find == null) {
			User admin = new User();
			admin.setUsername("henriquerdq");
			admin.setAccountNonExpired(true);
			admin.setAccountNonLocked(true);
			admin.setCredentialsNonExpired(true);
			admin.setEnabled(true);
			admin.setPassword(passwordEncoder.encode("123456"));
			admin.setPermissions(Arrays.asList(permission));
			userService.save(admin);
		}
	}

	private Permission carregarPermissao() {
		Permission permission =  permissionService.findByDescription("Admin");
		if (permission == null) {
			permission = new Permission();
			permission.setDescription("Admin");
			permission = permissionService.save(permission);
		}
		return permission;
	}

}
