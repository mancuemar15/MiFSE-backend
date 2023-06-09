package com.mifse.backend.persistencia.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.mifse.backend.vistas.Vistas;

import lombok.Data;

@Data
@Entity
@Table(name = "especialidad")
public class Especialidad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Vistas.ListaPreferencias.class, Vistas.Lista.class, Vistas.ListaExtendida.class })
	private Long id;

	@Column(name = "Nombre")
	@JsonView({ Vistas.ListaPreferencias.class, Vistas.Lista.class, Vistas.ListaExtendida.class })
	private String nombre;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_titulacion")
	@JsonView(Vistas.ListaPreferencias.class)
	private Titulacion titulacion;
}
