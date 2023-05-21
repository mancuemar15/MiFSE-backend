package com.mifse.backend.excepciones;

public class UsuarioNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3805445533182941206L;

	public UsuarioNotFoundException(String mensaje) {
		super(mensaje);
	}

}
