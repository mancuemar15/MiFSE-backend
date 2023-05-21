package com.mifse.backend.excepciones;

public class DesbloqueoUsuarioException extends RuntimeException {

	private static final long serialVersionUID = 6551409905450233823L;

	public DesbloqueoUsuarioException(String mensaje) {
		super(mensaje);
	}

}
