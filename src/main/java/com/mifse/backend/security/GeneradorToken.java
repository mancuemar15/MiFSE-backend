package com.mifse.backend.security;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import com.mifse.backend.persistencia.modelos.Usuario;

@Component
public class GeneradorToken {

	@Autowired
	private JwtEncoder tokenEncoder;

	public String generarToken(Authentication authentication) {
		if (!(authentication.getPrincipal() instanceof Usuario)) {
			throw new BadCredentialsException(
					"El principal " + authentication.getPrincipal().getClass() + " no es de tipo Usuario");
		}

		Usuario usuario = (Usuario) authentication.getPrincipal();
		JwtClaimsSet claimsSet = JwtClaimsSet.builder().issuedAt(Instant.now()).subject(usuario.getId().toString())
				.build();
		JwsHeader jwsHeader = JwsHeader.with(() -> "HS256").build();

		return this.tokenEncoder.encode(JwtEncoderParameters.from(jwsHeader, claimsSet)).getTokenValue();
	}
}