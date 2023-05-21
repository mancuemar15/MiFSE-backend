package com.mifse.backend.excepciones;

public class ActualizacionListaException extends RuntimeException {

	private static final long serialVersionUID = 8758102027722619837L;

	public ActualizacionListaException(Long id) {
		super("No se ha podido actualizar la lista con ID: " + id);
	}

}
