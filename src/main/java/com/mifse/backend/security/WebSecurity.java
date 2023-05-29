package com.mifse.backend.security;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity {

	@Autowired
	private ConversorJwtAUsuario jwtToUserConverter;

	@Autowired
	private UtilidadesToken utilidadesKey;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDetailsManager userDetailsManager;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// @formatter:off
		http.csrf().disable().cors()
				.and()
				.httpBasic().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.exceptionHandling()
				.authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
				.accessDeniedHandler(new BearerTokenAccessDeniedHandler())
				.and()
				.authorizeHttpRequests()
				.antMatchers("/residentes/registro", "/usuarios/login", "/usuarios/verificar", "/tipos-guardias", 
						"/tipos-residentes", "/tipos-trabajos", "/titulaciones", "/ultimas-posiciones/*", 
						"/centros/**", "/especialidades/**", "/especialidades-centros/*", "/contacto").permitAll()
				.antMatchers("/usuarios", "/usuarios/bloquear/*", "/usuarios/desbloquear/*", "/administradores/**", 
						"/comentarios/*").hasRole("ADMINISTRADOR")
				.antMatchers("/listas/**", "/mensajes/**").hasRole("RESIDENTE")
				.anyRequest().authenticated()
				.and()
				.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtToUserConverter);
		return http.build();
		// @formatter:on
	}

	@Bean
	public JwtDecoder jwtTokenDecoder() {
		return NimbusJwtDecoder.withSecretKey(this.utilidadesKey.getTokenSecret()).build();
	}

	@Bean
	public JwtEncoder jwtTokenEncoder() {
		SecretKey tokenSecretKey = this.utilidadesKey.getTokenSecret();
		return new NimbusJwtEncoder(new ImmutableSecret<SecurityContext>(tokenSecretKey));
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(userDetailsManager);
		return provider;
	}

}