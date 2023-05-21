package com.mifse.backend.excepciones;

public class EspecialidadNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -977863600630069353L;

	public EspecialidadNotFoundException(String mensaje) {
		super(mensaje);
	}

}
