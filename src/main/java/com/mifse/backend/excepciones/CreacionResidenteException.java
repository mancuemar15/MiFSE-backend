package com.mifse.backend.excepciones;

public class CreacionResidenteException extends RuntimeException {

	private static final long serialVersionUID = 4502543535888638779L;

	public CreacionResidenteException() {
		super("Error al crear el residente");
	}

}
