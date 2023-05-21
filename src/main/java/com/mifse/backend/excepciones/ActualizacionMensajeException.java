package com.mifse.backend.excepciones;

public class ActualizacionMensajeException extends RuntimeException {

	private static final long serialVersionUID = 4484754940947438765L;

	public ActualizacionMensajeException(String mensaje) {
		super(mensaje);
	}

}
