package com.mifse.backend.persistencia.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.mifse.backend.vistas.Vistas;

import lombok.Data;

@Data
@Entity
@Table(name = "titulacion")
public class Titulacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(Vistas.Conversacion.class)
	private Long id;

	@Column(name = "Nombre")
	@JsonView({ Vistas.ListaPreferencias.class, Vistas.Conversacion.class, Vistas.Centro.class, Vistas.Comentario.class })
	private String nombre;
}