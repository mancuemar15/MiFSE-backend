package com.mifse.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import com.mifse.backend.persistencia.modelos.Usuario;
import com.mifse.backend.servicios.ServicioUsuario;

@Component
public class ConversorJwtAUsuario implements Converter<Jwt, UsernamePasswordAuthenticationToken> {

	@Autowired
	private ServicioUsuario servicioUsuario;

	@Override
	public UsernamePasswordAuthenticationToken convert(Jwt jwt) {
		Usuario usuario = this.servicioUsuario.obtenerPorId(Long.valueOf(jwt.getSubject()));
		return new UsernamePasswordAuthenticationToken(usuario, jwt, usuario.getAuthorities());
	}
}