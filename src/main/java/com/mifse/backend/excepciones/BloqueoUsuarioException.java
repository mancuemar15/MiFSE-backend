package com.mifse.backend.excepciones;

public class BloqueoUsuarioException extends RuntimeException {

	private static final long serialVersionUID = 6551409905450233823L;

	public BloqueoUsuarioException(String mensaje) {
		super(mensaje);
	}

}
