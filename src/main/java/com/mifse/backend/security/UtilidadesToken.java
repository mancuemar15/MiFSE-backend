package com.mifse.backend.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Component
public class UtilidadesToken {

	@Value("${token.secret}")
	private String tokenSecret;

	public SecretKey getTokenSecret() {
		return new SecretKeySpec(Base64.getDecoder().decode(tokenSecret), "HmacSHA256");
	}
}