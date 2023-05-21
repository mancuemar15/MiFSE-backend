package com.mifse.backend.excepciones;

public class UltimaPosicionNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4197455861861907470L;

	public UltimaPosicionNotFoundException(String mensaje) {
		super(mensaje);
	}

}
