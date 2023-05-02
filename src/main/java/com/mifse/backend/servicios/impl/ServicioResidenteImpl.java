package com.mifse.backend.servicios.impl;

import java.sql.SQLIntegrityConstraintViolationException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mifse.backend.persistencia.modelos.Residente;
import com.mifse.backend.persistencia.repositorios.RepositorioResidente;
import com.mifse.backend.servicios.ServicioResidente;

@Service
@Transactional
public class ServicioResidenteImpl implements ServicioResidente {

	private final String URL_VERIFICACION = "http://localhost:8090/usuarios/verificar-correo-electronico?id=";

	@Autowired
	private RepositorioResidente repositorioResidente;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public Residente guardar(Residente residente) throws Exception {
		try {
			residente.setContrasena(this.passwordEncoder.encode(residente.getContrasena()));
			Residente residenteGuardado = this.repositorioResidente.save(residente);

			this.enviarEmailVerificacion(residenteGuardado);

			return residenteGuardado;
		} catch (ConstraintViolationException e) {
			throw new Exception("Ya existe un residente con ese email");
		}
	}

	private void enviarEmailVerificacion(Residente residente) {
		String asunto = residente.getNombre() + ", verifica tu correo electrónico";
		String mensaje = "¡Bienvenid@ " + residente.getNombre()
				+ "!\n\nPor favor, haz clic en el siguiente enlace para verificar tu correo electrónico: "
				+ URL_VERIFICACION + residente.getId();
		SimpleMailMessage correo = new SimpleMailMessage();
		correo.setTo(residente.getEmail());
		correo.setSubject(asunto);
		correo.setText(mensaje);
		this.mailSender.send(correo);
	}
}
