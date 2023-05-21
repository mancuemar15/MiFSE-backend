package com.mifse.backend.excepciones;

public class EspecialidadTitulacionNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3293658431625332640L;

	public EspecialidadTitulacionNotFoundException(String mensaje) {
		super(mensaje);
	}
}
