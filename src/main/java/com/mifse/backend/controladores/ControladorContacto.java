package com.mifse.backend.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mifse.backend.persistencia.modelos.dto.FormularioContacto;
import com.mifse.backend.servicios.ServicioEmail;

@RestController
@RequestMapping("/contacto")
public class ControladorContacto {

	@Autowired
	private ServicioEmail servicioEmail;

	@PostMapping
	public ResponseEntity<?> enviarFormularioContacto(@RequestBody FormularioContacto formulario) {
		String remitente = formulario.getEmail();
		String asunto = formulario.getAsunto();
		String cuerpo = "Nombre: " + formulario.getNombre() + "\n" + "Mensaje: " + formulario.getMensaje();

		this.servicioEmail.enviarEmailContacto(remitente, asunto, cuerpo);

		return ResponseEntity.ok().build();
	}
}
