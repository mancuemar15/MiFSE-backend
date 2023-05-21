package com.mifse.backend.excepciones;

public class VerificacionUsuarioException extends RuntimeException {

	private static final long serialVersionUID = -2144646702267452736L;

	public VerificacionUsuarioException(String mensaje) {
		super(mensaje);
	}

}
