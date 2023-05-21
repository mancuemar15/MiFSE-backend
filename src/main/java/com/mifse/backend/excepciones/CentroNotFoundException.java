package com.mifse.backend.excepciones;

public class CentroNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3679622317174776944L;

	public CentroNotFoundException(String mensaje) {
		super(mensaje);
	}

}
