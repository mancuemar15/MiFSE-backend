package com.mifse.backend.excepciones;

public class TipoResidenteNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5449194115121057303L;

	public TipoResidenteNotFoundException() {
		super("No se encontraron tipos de residente.");
	}

}
