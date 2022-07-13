package br.com.scf.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.scf.auth.entity.User;
import br.com.scf.auth.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username).stream().findFirst()
				.orElseThrow(() -> new UsernameNotFoundException(" Username " + username + " not found"));
	}

	public void save(User user) {
		userRepository.save(user);
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username).stream().findFirst().orElse(null);
	}

}
