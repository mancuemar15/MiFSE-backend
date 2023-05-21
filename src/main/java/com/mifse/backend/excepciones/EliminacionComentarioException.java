package com.mifse.backend.excepciones;

public class EliminacionComentarioException extends RuntimeException {

	private static final long serialVersionUID = 7042964576499279962L;

	public EliminacionComentarioException(Long id) {
		super("Error al eliminar el comentario con ID: " + id);
	}
}
