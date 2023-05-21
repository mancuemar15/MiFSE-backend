package com.mifse.backend.excepciones;

public class TitulacionNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2099053799710809613L;

	public TitulacionNotFoundException() {
		super("No se encontraron titulaciones");
	}

}
