package com.mifse.backend.excepciones;

public class TipoTrabajoFinResidenciaNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5633258558383216080L;

	public TipoTrabajoFinResidenciaNotFoundException() {
		super("No se encontraron tipos de trabajo de fin de residencia.");
	}

}
