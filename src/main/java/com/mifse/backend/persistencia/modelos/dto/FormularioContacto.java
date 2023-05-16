package com.mifse.backend.persistencia.modelos.dto;

import lombok.Data;

@Data
public class FormularioContacto {
	
	private String nombre;
	
	private String email;
	
	private String asunto;
	
	private String mensaje;

}
