package com.mifse.backend.excepciones;

public class ListaNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4386120487771142191L;

	public ListaNotFoundException(String mensaje) {
		super(mensaje);
	}

}
