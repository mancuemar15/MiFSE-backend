package com.mifse.backend.excepciones;

public class ComentarioNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 501152395803500435L;

	public ComentarioNotFoundException(Long id) {
		super("No se encontró el comentario con ID: " + id);
	}
}
