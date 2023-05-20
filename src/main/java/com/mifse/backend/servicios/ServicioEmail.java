package com.mifse.backend.servicios;

public interface ServicioEmail {

	public void enviarEmailVerificacion(String nombreDestinatario, String emailDestinatario, Long idUsuario);
	
	public void enviarEmailContacto(String remitente, String asunto, String cuerpo);
}
