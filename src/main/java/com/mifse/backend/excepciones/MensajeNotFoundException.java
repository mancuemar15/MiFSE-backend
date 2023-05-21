package com.mifse.backend.excepciones;

public class MensajeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8473602964346556012L;

	public MensajeNotFoundException(String mensaje) {
		super(mensaje);
	}

}
