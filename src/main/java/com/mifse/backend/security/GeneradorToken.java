package com.mifse.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import com.mifse.backend.persistencia.modelos.Usuario;

@Component
public class GeneradorToken {

	@Autowired
	JwtEncoder accessTokenEncoder;

	public String generarToken(Authentication authentication) {
		if (!(authentication.getPrincipal() instanceof Usuario)) {
			throw new BadCredentialsException(
					"El principal " + authentication.getPrincipal().getClass() + " no es de tipo Usuario");
		}

		Usuario usuario = (Usuario) authentication.getPrincipal();

		JwtClaimsSet claimsSet = JwtClaimsSet.builder().subject(usuario.getId().toString()).build();

		return accessTokenEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
	}
}