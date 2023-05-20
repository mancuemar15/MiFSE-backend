package com.mifse.backend.servicios.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.mifse.backend.servicios.ServicioEmail;

@Service
public class ServicioEmailImpl implements ServicioEmail {

	private final String URL_VERIFICACION = "http://localhost:8090/usuarios/verificar-correo-electronico?id=";

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void enviarEmailVerificacion(String nombreDestinatario, String emailDestinatario, Long idUsuario) {
		String asunto = nombreDestinatario + ", verifica tu correo electrónico";
		String cuerpo = "¡Bienvenid@ " + nombreDestinatario
				+ "!\n\nPor favor, haz clic en el siguiente enlace para verificar tu correo electrónico: "
				+ URL_VERIFICACION + idUsuario;
		SimpleMailMessage correo = new SimpleMailMessage();
		correo.setTo(emailDestinatario);
		correo.setSubject(asunto);
		correo.setText(cuerpo);
		this.mailSender.send(correo);
	}

	@Override
	public void enviarEmailContacto(String remitente, String asunto, String cuerpo) {
		try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo("mifse.noreply@gmail.com");
            message.setFrom(remitente);
            message.setSubject(asunto);
            message.setText(cuerpo);

            this.mailSender.send(message);
        } catch (Exception e) {
        }
	}

}
