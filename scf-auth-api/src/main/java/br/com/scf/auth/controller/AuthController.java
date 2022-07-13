package br.com.scf.auth.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.scf.auth.controller.request.UserRequest;
import br.com.scf.auth.jwt.JwtTokenProvider;
import br.com.scf.auth.service.UserService;

@RestController
@RequestMapping("/login")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private UserService userService;

	@RequestMapping("/teste-security")
	public String teste() {
		return "Autenticado";
	}

	@PostMapping
	public ResponseEntity<?> login(@RequestBody UserRequest userRequest) {
		try {
			var username = userRequest.getUsername();
			var password = userRequest.getPassword();

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

			var user = userService.loadUserByUsername(username);

			var token = jwtTokenProvider.createToken(username, user.getRoles());

			Map<Object, Object> model = new HashMap<>();
			model.put("username", username);
			model.put("token", token);
			return ok(model);

		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Ivalid username/password");
		}
	}
}
