package com.mifse.backend.persistencia.modelos;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonView;
import com.mifse.backend.vistas.Vistas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "preferencia")
@IdClass(PreferenciaId.class)
@DynamicUpdate
public class Preferencia {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_lista")
	@Id
	private Lista lista;

	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "ID_especialidad", referencedColumnName = "ID_especialidad"),
			@JoinColumn(name = "ID_centro", referencedColumnName = "ID_centro") })
	@JsonView(Vistas.ListaPreferencias.class)
	private EspecialidadCentro especialidadCentro;

	@Column(name = "Numero")
	@JsonView(Vistas.ListaPreferencias.class)
	private Integer numero;
}