package br.com.scf.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.scf.auth.jwt.JwtConfigurer;
import br.com.scf.auth.jwt.JwtTokenProvider;

@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); 
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean()  throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
			http
			   .httpBasic().disable()
			   .csrf().disable()
			   .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			   .and()
			        .authorizeRequests()
			        .antMatchers("/login").permitAll()
			        .anyRequest().authenticated()
			   .and()
			   .apply(new JwtConfigurer(jwtTokenProvider));
	}
}





