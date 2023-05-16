package com.mifse.backend.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mifse.backend.persistencia.modelos.Residente;
import com.mifse.backend.servicios.ServicioResidente;

@RestController
@RequestMapping("/residentes")
public class ControladorResidente {

	@Autowired
	private ServicioResidente servicioResidente;

	@PostMapping("/registro")
	public ResponseEntity<?> guardarResidente(@RequestBody Residente residente) {
		return ResponseEntity.ok(this.servicioResidente.guardar(residente));
	}
	
	@PutMapping
	public ResponseEntity<?> actualizarResidente(@RequestBody Residente residente) {
		return ResponseEntity.ok(this.servicioResidente.actualizar(residente));
	} 
}