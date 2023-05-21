package com.mifse.backend.excepciones;

public class EliminacionUsuarioException extends RuntimeException {

	private static final long serialVersionUID = 3376934393870792105L;

	public EliminacionUsuarioException(String mensaje) {
		super(mensaje);
	}

}
