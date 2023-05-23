package com.mifse.backend.servicios.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.mifse.backend.excepciones.EmailException;
import com.mifse.backend.servicios.ServicioEmail;

@Service
public class ServicioEmailImpl implements ServicioEmail {

	private final String URL_VERIFICACION = "https://mifse.vercel.app/verificacion?id=";

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void enviarEmailVerificacion(String nombreDestinatario, String emailDestinatario, Long idUsuario) {
		try {
			String asunto = nombreDestinatario + ", verifica tu correo electrónico";
			String cuerpo = "¡Bienvenid@ " + nombreDestinatario
					+ "!\n\nPor favor, haz clic en el siguiente enlace para verificar tu correo electrónico: "
					+ URL_VERIFICACION + idUsuario;
			SimpleMailMessage correo = new SimpleMailMessage();
			correo.setTo(emailDestinatario);
			correo.setSubject(asunto);
			correo.setText(cuerpo);
			this.mailSender.send(correo);
		} catch (Exception e) {
			throw new EmailException("Error al enviar el correo de verificación: " + e.getMessage());
		}
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
			throw new EmailException("Error al enviar el correo de contacto: " + e.getMessage());
		}
	}

	@Override
	public void enviarEmailNuevoMensaje(String nombreDestinatario, String emailDestinatario, String nombreRemitente,
			String contenido) {
		try {
			String asunto = "¡" + nombreDestinatario + ", tienes un nuevo mensaje!";
			String cuerpo = "Hola " + nombreDestinatario + ",\n\nTienes un nuevo mensaje de " + nombreRemitente
					+ ":\n\n" + contenido + "\n";
			SimpleMailMessage correo = new SimpleMailMessage();
			correo.setTo(emailDestinatario);
			correo.setSubject(asunto);
			correo.setText(cuerpo);
			this.mailSender.send(correo);
		} catch (Exception e) {
			throw new EmailException("Error al enviar el correo de nuevo mensaje: " + e.getMessage());
		}
	}

}
