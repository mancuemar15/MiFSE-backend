package com.mifse.backend.persistencia.modelos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CentroIdNombreDTO {

	private Integer id;
	
	private String nombre;
}