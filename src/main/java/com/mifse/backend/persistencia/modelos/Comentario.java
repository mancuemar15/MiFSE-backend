package com.mifse.backend.persistencia.modelos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "comentario")
public class Comentario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	@JsonView({Vistas.Centro.class, Vistas.Comentario.class})
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_residente")
	@JsonView({Vistas.Centro.class, Vistas.Comentario.class})
	private Residente residente;

	@ManyToOne
	@JoinColumn(name = "ID_centro")
	private Centro centro;

	@Column(name = "Contenido")
	@JsonView({Vistas.Centro.class, Vistas.Comentario.class})
	private String contenido;

	@Column(name = "Valoracion")
	@JsonView({Vistas.Centro.class, Vistas.Comentario.class})
	private Double valoracion;

	@Column(name = "Fecha")
	@JsonView({Vistas.Centro.class, Vistas.Comentario.class})
	private Date fecha;

}