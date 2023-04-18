package com.mifse.backend.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mifse.backend.persistencia.modelos.Provincia;
import com.mifse.backend.servicios.ServicioProvincia;

@RestController
@RequestMapping(path = "/provincias")
public class ControladorProvincias {

	@Autowired
	private ServicioProvincia servicioProvincia;

	@GetMapping
	public ResponseEntity<?> obtenerProvincias() {
		return ResponseEntity.ok(this.servicioProvincia.obtenerTodas());
	}

	@GetMapping("/autonomia/{idAutonomia}")
	public ResponseEntity<?> obtenerProvinciasPorIdAutonomia(@PathVariable Integer idAutonomia) {
		return ResponseEntity.ok(this.servicioProvincia.obtenerTodasPorIdAutonomia(idAutonomia));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerProvinciaPorId(@PathVariable Integer id) {
		Provincia provincia = servicioProvincia.obtenerPorId(id);
		if (provincia != null) {
			return ResponseEntity.ok(provincia);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
