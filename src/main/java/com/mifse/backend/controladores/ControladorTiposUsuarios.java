package com.mifse.backend.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mifse.backend.servicios.ServicioTipoUsuario;

@RestController
@RequestMapping(path = "/tipos-usuarios")
public class ControladorTiposUsuarios {

	@Autowired
	private ServicioTipoUsuario servicioTipoUsuario;

	@GetMapping
	private ResponseEntity<?> obtenerTiposUsuarios() {
		return ResponseEntity.ok(this.servicioTipoUsuario.obtenerTodos());
	}
}