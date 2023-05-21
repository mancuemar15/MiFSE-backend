package com.mifse.backend.excepciones;

public class ActualizacionResidenteException extends RuntimeException {

	private static final long serialVersionUID = 1906367490780803524L;

	public ActualizacionResidenteException() {
		super("No se pudo actualizar el residente.");
	}
}
