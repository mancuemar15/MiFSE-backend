package com.mifse.backend.excepciones;

public class CreacionListaException extends RuntimeException {

	private static final long serialVersionUID = 4004624037323462581L;

	public CreacionListaException() {
		super("Error al crear la lista");
	}

}
