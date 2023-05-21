package com.mifse.backend.excepciones;

public class TipoGuardiasFindesFestivosNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2530118607859534153L;

	public TipoGuardiasFindesFestivosNotFoundException() {
		super("No se encontraron tipos de guardias para fines de semana y festivos.");
	}
}
