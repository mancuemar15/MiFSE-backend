package com.mifse.backend.persistencia.modelos;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "preferencia")
public class Preferencia {

	@EmbeddedId
	private PreferenciaId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("idLista")
	@JoinColumn(name = "ID_lista")
	@JsonIgnore
	private Lista lista;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
			@JoinColumn(name = "ID_especialidad", referencedColumnName = "ID_especialidad", insertable = false, updatable = false),
			@JoinColumn(name = "ID_centro", referencedColumnName = "ID_centro", insertable = false, updatable = false) })
	private EspecialidadCentro especialidadCentro;

	@Column(name = "Numero")
	private Integer numero;
}