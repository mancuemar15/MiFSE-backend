package com.mifse.backend.excepciones;

public class CreacionAdministradorException extends RuntimeException {

	private static final long serialVersionUID = -3934543821682624528L;

	public CreacionAdministradorException() {
		super("Error al guardar el administrador");
	}
}
