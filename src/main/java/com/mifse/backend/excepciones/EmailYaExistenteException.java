package com.mifse.backend.excepciones;

public class EmailYaExistenteException extends RuntimeException {

	private static final long serialVersionUID = 1622807232656675538L;

	public EmailYaExistenteException(String email) {
		super("El email " + email + " ya está registrado");
	}
}
