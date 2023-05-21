package com.mifse.backend.excepciones;

public class EmailException extends RuntimeException {

	private static final long serialVersionUID = -6969048672880591491L;

	public EmailException(String mensaje) {
		super(mensaje);
	}

}
