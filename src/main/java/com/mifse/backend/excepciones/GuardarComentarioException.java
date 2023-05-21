package com.mifse.backend.excepciones;

public class GuardarComentarioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GuardarComentarioException(String mensaje) {
		super(mensaje);
	}
}
