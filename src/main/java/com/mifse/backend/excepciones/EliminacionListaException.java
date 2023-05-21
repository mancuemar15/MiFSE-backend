package com.mifse.backend.excepciones;

public class EliminacionListaException extends RuntimeException {

	private static final long serialVersionUID = -8760184313165295398L;

	public EliminacionListaException(Long id) {
		super("Error al eliminar la lista con ID: " + id);
	}
}
