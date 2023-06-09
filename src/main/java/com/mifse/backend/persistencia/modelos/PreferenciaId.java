package com.mifse.backend.persistencia.modelos;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonView;
import com.mifse.backend.vistas.Vistas;

import lombok.Data;

@Data
@JsonView(Vistas.ListaPreferencias.class)
public class PreferenciaId implements Serializable {

	private static final long serialVersionUID = -8802597742036545209L;

	private Long lista;
	
	private EspecialidadCentroId especialidadCentro;

}
