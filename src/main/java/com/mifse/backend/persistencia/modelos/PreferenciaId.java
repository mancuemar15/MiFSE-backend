package com.mifse.backend.persistencia.modelos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Embeddable
public class PreferenciaId implements Serializable {

	private static final long serialVersionUID = -8802597742036545209L;

	@Column(name = "lista")
	private Integer idLista;

	@Column(name = "ID_especialidad")
	private Integer idEspecialidad;

	@Column(name = "ID_centro")
	private Integer idCentro;

}
