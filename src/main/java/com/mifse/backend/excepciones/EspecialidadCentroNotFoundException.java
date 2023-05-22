package com.mifse.backend.excepciones;

public class EspecialidadCentroNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3293658431625332640L;

	public EspecialidadCentroNotFoundException(String mensaje) {
		super(mensaje);
	}
}
