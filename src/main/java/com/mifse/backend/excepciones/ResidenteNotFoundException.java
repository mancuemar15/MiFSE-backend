package com.mifse.backend.excepciones;

public class ResidenteNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6133972438859956726L;

	public ResidenteNotFoundException(Long id) {
		super("Residente no encontrado con ID: " + id);
	}
}
