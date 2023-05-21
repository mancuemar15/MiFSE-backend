package com.mifse.backend.excepciones;

public class AdministradorNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6229377281028237829L;

	public AdministradorNotFoundException(Long id) {
		super("No se encontró el administrador con ID: " + id);
	}
}