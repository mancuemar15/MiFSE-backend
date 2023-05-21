package com.mifse.backend.excepciones;

public class TipoUsuarioNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2495865817861541150L;

	public TipoUsuarioNotFoundException(String tipo) {
		super("Tipo de usuario no encontrado: " + tipo);
	}
}
